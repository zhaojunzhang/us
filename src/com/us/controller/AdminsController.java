package com.us.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.us.po.AdminsCustom;
import com.us.po.ArticlesCustom;
import com.us.po.Authorities;
import com.us.po.MailboxCustom;
import com.us.po.PageBean;
import com.us.po.UsersCustom;
import com.us.service.AdminsService;
import com.us.service.UsersService;


@Controller

public class AdminsController {
	PageBean pageBean = new PageBean();
	ModelAndView modelAndView = new ModelAndView();
	ArticlesCustom articlesCustom = new ArticlesCustom();
	@Autowired
	AdminsService adminsService;
    @Autowired
    UsersService usersService;
	// 管理员登陆
	@RequestMapping("/admins/loginAdmins")
	public ModelAndView loginAdmins(AdminsCustom adminsCustom,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1，判断验证码是否正确
		// 获得客户端验证码
		String checkcode = request.getParameter("checkcode").toUpperCase();
		// 获得sesson 验证码
		String checkcode_session = (String) request.getSession().getAttribute(
				"checkcode_session");
		request.getSession().removeAttribute("checkcode_session");
		if (checkcode == null || !checkcode.equals(checkcode_session)) {
			// 验证码错误
			request.setAttribute("msg", "验证码输入错误");
				request.getRequestDispatcher("/adminLogin.jsp").forward(
						request, response);		
		} else {
			try {
				AdminsCustom adminsCustom1 = adminsService
						.findAdminsPasswordAndUsername(adminsCustom);
				if(adminsCustom1==null){
					request.setAttribute("msg", "用户名密码错误");
					request.getRequestDispatcher("/adminLogin.jsp").forward(
							request, response);	
				}
				String ip = request.getRemoteAddr();
				adminsCustom1.setIp(ip);
				String agent = request.getHeader("User-Agent");
				adminsCustom1.setAgent(agent);
				long loged1 = System.currentTimeMillis();
				adminsCustom1.setLoged(loged1);
				adminsService.updateadminsCustomLoged(adminsCustom1);
				request.getSession()
						.setAttribute("adminsCustom", adminsCustom1);
				modelAndView.addObject("adminsCustom", adminsCustom1);
				modelAndView.setViewName("/manage/manageSystem");
				return modelAndView;
			} catch (Exception e) {
				request.setAttribute("msg", "获取信息失败");
				request.getRequestDispatcher("/error.jsp").forward(
						request, response);	
			}
		}
		return null;
	}

	// 添加文章
	@RequestMapping("/admins/addarticles")
	public ModelAndView addarticles(ArticlesCustom articlesCustom,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminsCustom adminsCustom = (AdminsCustom) request.getSession()
				.getAttribute("adminsCustom");		
		String[] authorities = adminsCustom.getAuthority().split(",");
		if(authorities[0].equals("0")){
			request.setAttribute("msg", "权限不足");
			modelAndView.addObject("adminsCustom", adminsCustom);
			modelAndView.setViewName("/manage/manageSystem");
			return modelAndView;
		}
		if(articlesCustom.getAuthor()==null||articlesCustom.getContent()==null||articlesCustom.getSlug()==null){
			request.setAttribute("msg","信息输入错误不能为空");
			request.getRequestDispatcher("/addarticles.jsp").forward(request, response);	
		}
		
		
		String ip = request.getRemoteAddr();
		articlesCustom.setIp(ip);
		String agent = request.getHeader("User-Agent");
		articlesCustom.setAgent(agent);
		long created = System.currentTimeMillis();
		articlesCustom.setCreated(created);
		articlesCustom.setModified(created);		
		articlesCustom.setAuthorid(adminsCustom.getId());		
		try {
			int n = adminsService.addArticles(articlesCustom);
			modelAndView.addObject("adminsCustom", adminsCustom);
			modelAndView.setViewName("/manage/manageSystem");
			return modelAndView;				
		} catch (Exception e) {
			throw new RuntimeException("Controller--添加文章失败");
		}

	}

	// 查询校园风云
	@RequestMapping("/admins/findSchoolSituation")
	public ModelAndView findSchoolSituation(HttpServletRequest request) {
		
		String category = "校园风云";
		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = adminsService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			
			modelAndView.setViewName("/manage/SchoolSituation");
			
			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller--根据用户cid来查询文章失败");
		}
	}

	// 根据aid来查询文章的详细信息
	@RequestMapping("/findArticlesCustomByAid")
	public ModelAndView findArticlesCustomByAid(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			ArticlesCustom articlesCustom = adminsService
					.findArticlesCustomByAid(aid);
			modelAndView.addObject("articlesCustom", articlesCustom);
			modelAndView.setViewName("/manage/ArticlesCustom_detail");

			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller---根据aid来查询文章失败");
		}
	}

	// 按条件来查询文章
	@RequestMapping("/admins/findArticlesByCondition")
	public ModelAndView findArticlesByCondition(HttpServletRequest request) throws UnsupportedEncodingException{

		String category = request.getParameter("category");
		String category1 = new String(category.getBytes("iso-8859-1"), "utf-8");
		String conditionName = request.getParameter("conditionName");
		String conditionValue = request.getParameter("conditionValue");
		int pNum = 1;
		String pNumStr = request.getParameter("pNum");
		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		pageBean.setConditionName(conditionName);
		pageBean.setConditionValue(conditionValue);
		pageBean.setpNum(pNum);
		PageBean pageBean1;
		try {
			pageBean1 = adminsService.findArticlesByCondition(pageBean,
					category1);
			modelAndView.addObject("pageBean", pageBean1);
			if (category1.equals("校园风云")) {
				modelAndView.setViewName("/manage/SchoolSituation");
			} else {
				if (category1.equals("校园八卦")) {
					modelAndView.setViewName("/manage/SchoolGossip");
				} else {
					if (category1.equals("校园资讯") ) {
						modelAndView.setViewName("/manage/CampusConsultation");
					} else {
						if (category1.equals("校园活动")) {
							modelAndView
									.setViewName("/manage/SchoolActivities");
						} else {
							if (category1.equals("口碑商")) {
								modelAndView
										.setViewName("/manage/ReputationBusinessman");
							} else {
								if (category1.equals("便生活")) {
									modelAndView
											.setViewName("/manage/Thenlife");
								} else {
									if (category1.equals("有活动")) {
										modelAndView
												.setViewName("/manage/Activity");
									}
								}
							}
						}
					}
				}
			}

			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller--按条件查询文章失败");
		}

	}

	// 查询校园八卦
	@RequestMapping("/admins/findSchoolGossip")
	public ModelAndView findSchoolGossip(HttpServletRequest request) {
		String category = "校园八卦";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = adminsService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/manage/SchoolGossip");

			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller--根据用户cid来查询文章失败");
		}
	}

	// 根据aid来删除文章信息
	@RequestMapping("/admins/delArticlesCustomByAid")
	public String delArticlesCustomByAid(HttpServletRequest request) throws UnsupportedEncodingException {
		String category1 = request.getParameter("category");
		String category = new String(category1.getBytes("iso-8859-1"), "utf-8");
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			adminsService.delArticlesCustomByAid(aid);
			String s=null;
			if(category.equals("校园风云")){
				s="redirect:/admins/findSchoolSituation.action";
			}else{
				if(category.equals("校园八卦")){
					s="redirect:/admins/findSchoolGossip.action";
				}else{
					if(category.equals("校园资讯")){
						s="redirect:/admins/findCampusConsultation.action";
					}else{
						if(category.equals("校园活动")){
							s="redirect:/admins/findSchoolActivities.action";
						}else{
							if(category.equals("口碑商")){
								s="redirect:/admins/findReputationBusinessman.action";
							}else{
								if(category.equals("有活动")){
									s="redirect:/admins/findActivity.action";
								}else{
									if(category.equals("便生活")){
										s="redirect:/admins/findThenlife.action";
									}																			
								}
							}
						}
					}
				}
			}
			return s;
		} catch (Exception e) {
			throw new RuntimeException("Controller--根据aid删除文章信息失败");
		}
	}

	// 查询校园咨讯
	@RequestMapping("/admins/findCampusConsultation")
	public ModelAndView findCampusConsultation(HttpServletRequest request) {
		String category = "校园资讯";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = adminsService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/manage/CampusConsultation");

			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller--根据用户cid来查询文章失败");
		}
	}

	// 查询校园活动
	@RequestMapping("/admins/findSchoolActivities")
	public ModelAndView findSchoolActivities(HttpServletRequest request) {
		String category = "校园活动";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = adminsService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/manage/SchoolActivities");

			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller--根据用户cid来查询文章失败");
		}
	}

	// 查询口碑商
	@RequestMapping("/admins/findReputationBusinessman")
	public ModelAndView findReputationBusinessman(HttpServletRequest request) {
		String category = "口碑商";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = adminsService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/manage/ReputationBusinessman");

			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller--根据用户cid来查询文章失败");
		}
	}

	// 查询便生活
	@RequestMapping("/admins/findThenlife")
	public ModelAndView findThenlife(HttpServletRequest request) {
		String category = "便生活";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = adminsService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/manage/Thenlife");

			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller--根据用户cid来查询文章失败");
		}
	}

	// 查询有活动
	@RequestMapping("/admins/findActivity")
	public ModelAndView findActivity(HttpServletRequest request) {
		String category = "有活动";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = adminsService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/manage/Activity");

			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller--根据用户cid来查询文章失败");
		}
	}

	//查询所有用户
	@RequestMapping("/admins/findAllUsersCustom")
	public ModelAndView findAllUsersCustom(HttpServletRequest request){
		AdminsCustom adminsCustom = (AdminsCustom) request.getSession()
				.getAttribute("adminsCustom");
		
		String[] authorities = adminsCustom.getAuthority().split(",");
		if(authorities[2].equals("0")){
			request.setAttribute("msg", "权限不足");
			modelAndView.addObject("adminsCustom", adminsCustom);
			modelAndView.setViewName("/manage/manageSystem");
			return modelAndView;
		}
		
		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = adminsService.findAllUsersCustom(pageBean);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/manage/AllUsers");
			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller----查询所有的用户失败");
		} 		
	}
	//根据条件来模糊查询用户
	@RequestMapping("/admins/findUsersCustomByCondition")
	public ModelAndView findUsersCustomByCondition(HttpServletRequest request){
		String conditionName = request.getParameter("conditionName");
		String conditionValue = request.getParameter("conditionValue");
		int pNum = 1;
		String pNumStr = request.getParameter("pNum");
		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		pageBean.setConditionName(conditionName);
		pageBean.setConditionValue(conditionValue);
		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = adminsService.findAUsersByCondition(pageBean);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/manage/AllUsers");
			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("controller---根据条件去查询用户失败");
		}
	}
	//根据用户的Id来删除用户
	@RequestMapping("/admins/delUsersCustomByUid")
	public ModelAndView delUsersCustomByUid(HttpServletRequest request){
		AdminsCustom adminsCustom = (AdminsCustom) request.getSession()
				.getAttribute("adminsCustom");
		String[] authorities = adminsCustom.getAuthority().split(",");
		if(authorities[4].equals("0")){
			request.setAttribute("msg", "权限不足");
			modelAndView.addObject("adminsCustom", adminsCustom);
			modelAndView.setViewName("/manage/manageSystem");
			return modelAndView;
		}
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			adminsService.delUsersCustomByUid(uid);
			modelAndView.addObject("adminsCustom", adminsCustom);
			modelAndView.setViewName("/manage/manageSystem");
			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller---根据uid来删除用户失败");
		}
		
	}

	//查询用户的信息通过用户的id
	@RequestMapping("/admins/findUsersCustomauthorityByUid")
	public ModelAndView findUsersCustomauthorityByUid(HttpServletRequest request){
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			UsersCustom usersCustom =  adminsService.findUsersCustomByUid(uid);
			modelAndView.addObject("usersCustom", usersCustom);
			modelAndView.setViewName("/manage/usersCustom_detail");
			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller---查询用户失败");
		}
		
	}

    //通过Gid来更改用户的权限
	@RequestMapping("/admins/updateUsersAuthorityByGid")
	public String updateUsersAuthorityByGid(Authorities authorities,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		try {
			
			adminsService.updateUsersAuthorityByGid(authorities);
			return "redirect:/admins/findAllUsersCustom.action";
		} catch (Exception e) {
			throw new RuntimeException("Controlller--更改用户权限失败");
		}	
	}
	//查询用户信息通过Uid
	@RequestMapping("/admins/findUsersCustomMailboxByUid")
	public ModelAndView findUsersCustomMailboxByUid(HttpServletRequest request){
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			UsersCustom usersCustom =  adminsService.findUsersCustomByUid(uid);
			modelAndView.addObject("usersCustom", usersCustom);
			modelAndView.setViewName("/manage/addNotice");
			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller---查询用户失败");
		}
	}
	//发送通知给用户
	@RequestMapping("/admins/sendNoticeToUsers")
	public String sendNoticeToUsers(MailboxCustom mailboxCustom,int uid,HttpServletRequest request){
		AdminsCustom adminsCustom = (AdminsCustom) request.getSession()
				.getAttribute("adminsCustom");
		 mailboxCustom.setUid(uid);
		 mailboxCustom.setSendid(adminsCustom.getId());
		   // 查询用户发送时间
			long created = System.currentTimeMillis();
			mailboxCustom.setSendtime(created);
			// 获得当前用户客户端系统
			String agent = request.getHeader("User-Agent");
			mailboxCustom.setAgent(agent);
			// 获得当前用户的访问ip
			String ip = request.getRemoteAddr();
			mailboxCustom.setIp(ip);
			
			try {
				adminsService.sendNoticeByUsers(mailboxCustom);
				return "redirect:/admins/findAllUsersCustom.action";
			} catch (Exception e) {
				throw new RuntimeException("Controller--发通知给用户失败");
			}
		
		
	}

	// 根据aid来查询文章的详细信息
	@RequestMapping("/adminFindUpdateArticles")
	public ModelAndView adminFindUpdateArticles(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			ArticlesCustom articlesCustom = adminsService
					.findArticlesCustomByAid(aid);
			modelAndView.addObject("articlesCustom", articlesCustom);
			modelAndView.setViewName("/manage/Update_ArticlesCustom_detail");

			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller---根据aid来查询文章失败");
		}
	}
	@RequestMapping("/admins/UpdateUserArticlesCustom")
	public ModelAndView UpdateUserArticlesCustom(ArticlesCustom articlesCustom,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			AdminsCustom adminsCustom = (AdminsCustom) request.getSession()
					.getAttribute("adminsCustom");	
			
			usersService.updateArticlesCustom(articlesCustom);
			modelAndView.addObject("adminsCustom", adminsCustom);
			modelAndView.setViewName("/manage/manageSystem");
			return modelAndView;		
		} catch (Exception e) {
			request.setAttribute("msg", "操作失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		
		return null;
		
	}
}
