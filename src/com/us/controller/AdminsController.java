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
	// ����Ա��½
	@RequestMapping("/admins/loginAdmins")
	public ModelAndView loginAdmins(AdminsCustom adminsCustom,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1���ж���֤���Ƿ���ȷ
		// ��ÿͻ�����֤��
		String checkcode = request.getParameter("checkcode").toUpperCase();
		// ���sesson ��֤��
		String checkcode_session = (String) request.getSession().getAttribute(
				"checkcode_session");
		request.getSession().removeAttribute("checkcode_session");
		if (checkcode == null || !checkcode.equals(checkcode_session)) {
			// ��֤�����
			request.setAttribute("msg", "��֤���������");
				request.getRequestDispatcher("/adminLogin.jsp").forward(
						request, response);		
		} else {
			try {
				AdminsCustom adminsCustom1 = adminsService
						.findAdminsPasswordAndUsername(adminsCustom);
				if(adminsCustom1==null){
					request.setAttribute("msg", "�û����������");
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
				request.setAttribute("msg", "��ȡ��Ϣʧ��");
				request.getRequestDispatcher("/error.jsp").forward(
						request, response);	
			}
		}
		return null;
	}

	// �������
	@RequestMapping("/admins/addarticles")
	public ModelAndView addarticles(ArticlesCustom articlesCustom,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminsCustom adminsCustom = (AdminsCustom) request.getSession()
				.getAttribute("adminsCustom");		
		String[] authorities = adminsCustom.getAuthority().split(",");
		if(authorities[0].equals("0")){
			request.setAttribute("msg", "Ȩ�޲���");
			modelAndView.addObject("adminsCustom", adminsCustom);
			modelAndView.setViewName("/manage/manageSystem");
			return modelAndView;
		}
		if(articlesCustom.getAuthor()==null||articlesCustom.getContent()==null||articlesCustom.getSlug()==null){
			request.setAttribute("msg","��Ϣ���������Ϊ��");
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
			throw new RuntimeException("Controller--�������ʧ��");
		}

	}

	// ��ѯУ԰����
	@RequestMapping("/admins/findSchoolSituation")
	public ModelAndView findSchoolSituation(HttpServletRequest request) {
		
		String category = "У԰����";
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
			throw new RuntimeException("Controller--�����û�cid����ѯ����ʧ��");
		}
	}

	// ����aid����ѯ���µ���ϸ��Ϣ
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
			throw new RuntimeException("Controller---����aid����ѯ����ʧ��");
		}
	}

	// ����������ѯ����
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
			if (category1.equals("У԰����")) {
				modelAndView.setViewName("/manage/SchoolSituation");
			} else {
				if (category1.equals("У԰����")) {
					modelAndView.setViewName("/manage/SchoolGossip");
				} else {
					if (category1.equals("У԰��Ѷ") ) {
						modelAndView.setViewName("/manage/CampusConsultation");
					} else {
						if (category1.equals("У԰�")) {
							modelAndView
									.setViewName("/manage/SchoolActivities");
						} else {
							if (category1.equals("�ڱ���")) {
								modelAndView
										.setViewName("/manage/ReputationBusinessman");
							} else {
								if (category1.equals("������")) {
									modelAndView
											.setViewName("/manage/Thenlife");
								} else {
									if (category1.equals("�л")) {
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
			throw new RuntimeException("Controller--��������ѯ����ʧ��");
		}

	}

	// ��ѯУ԰����
	@RequestMapping("/admins/findSchoolGossip")
	public ModelAndView findSchoolGossip(HttpServletRequest request) {
		String category = "У԰����";

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
			throw new RuntimeException("Controller--�����û�cid����ѯ����ʧ��");
		}
	}

	// ����aid��ɾ��������Ϣ
	@RequestMapping("/admins/delArticlesCustomByAid")
	public String delArticlesCustomByAid(HttpServletRequest request) throws UnsupportedEncodingException {
		String category1 = request.getParameter("category");
		String category = new String(category1.getBytes("iso-8859-1"), "utf-8");
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			adminsService.delArticlesCustomByAid(aid);
			String s=null;
			if(category.equals("У԰����")){
				s="redirect:/admins/findSchoolSituation.action";
			}else{
				if(category.equals("У԰����")){
					s="redirect:/admins/findSchoolGossip.action";
				}else{
					if(category.equals("У԰��Ѷ")){
						s="redirect:/admins/findCampusConsultation.action";
					}else{
						if(category.equals("У԰�")){
							s="redirect:/admins/findSchoolActivities.action";
						}else{
							if(category.equals("�ڱ���")){
								s="redirect:/admins/findReputationBusinessman.action";
							}else{
								if(category.equals("�л")){
									s="redirect:/admins/findActivity.action";
								}else{
									if(category.equals("������")){
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
			throw new RuntimeException("Controller--����aidɾ��������Ϣʧ��");
		}
	}

	// ��ѯУ԰��Ѷ
	@RequestMapping("/admins/findCampusConsultation")
	public ModelAndView findCampusConsultation(HttpServletRequest request) {
		String category = "У԰��Ѷ";

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
			throw new RuntimeException("Controller--�����û�cid����ѯ����ʧ��");
		}
	}

	// ��ѯУ԰�
	@RequestMapping("/admins/findSchoolActivities")
	public ModelAndView findSchoolActivities(HttpServletRequest request) {
		String category = "У԰�";

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
			throw new RuntimeException("Controller--�����û�cid����ѯ����ʧ��");
		}
	}

	// ��ѯ�ڱ���
	@RequestMapping("/admins/findReputationBusinessman")
	public ModelAndView findReputationBusinessman(HttpServletRequest request) {
		String category = "�ڱ���";

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
			throw new RuntimeException("Controller--�����û�cid����ѯ����ʧ��");
		}
	}

	// ��ѯ������
	@RequestMapping("/admins/findThenlife")
	public ModelAndView findThenlife(HttpServletRequest request) {
		String category = "������";

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
			throw new RuntimeException("Controller--�����û�cid����ѯ����ʧ��");
		}
	}

	// ��ѯ�л
	@RequestMapping("/admins/findActivity")
	public ModelAndView findActivity(HttpServletRequest request) {
		String category = "�л";

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
			throw new RuntimeException("Controller--�����û�cid����ѯ����ʧ��");
		}
	}

	//��ѯ�����û�
	@RequestMapping("/admins/findAllUsersCustom")
	public ModelAndView findAllUsersCustom(HttpServletRequest request){
		AdminsCustom adminsCustom = (AdminsCustom) request.getSession()
				.getAttribute("adminsCustom");
		
		String[] authorities = adminsCustom.getAuthority().split(",");
		if(authorities[2].equals("0")){
			request.setAttribute("msg", "Ȩ�޲���");
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
			throw new RuntimeException("Controller----��ѯ���е��û�ʧ��");
		} 		
	}
	//����������ģ����ѯ�û�
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
			throw new RuntimeException("controller---��������ȥ��ѯ�û�ʧ��");
		}
	}
	//�����û���Id��ɾ���û�
	@RequestMapping("/admins/delUsersCustomByUid")
	public ModelAndView delUsersCustomByUid(HttpServletRequest request){
		AdminsCustom adminsCustom = (AdminsCustom) request.getSession()
				.getAttribute("adminsCustom");
		String[] authorities = adminsCustom.getAuthority().split(",");
		if(authorities[4].equals("0")){
			request.setAttribute("msg", "Ȩ�޲���");
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
			throw new RuntimeException("Controller---����uid��ɾ���û�ʧ��");
		}
		
	}

	//��ѯ�û�����Ϣͨ���û���id
	@RequestMapping("/admins/findUsersCustomauthorityByUid")
	public ModelAndView findUsersCustomauthorityByUid(HttpServletRequest request){
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			UsersCustom usersCustom =  adminsService.findUsersCustomByUid(uid);
			modelAndView.addObject("usersCustom", usersCustom);
			modelAndView.setViewName("/manage/usersCustom_detail");
			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller---��ѯ�û�ʧ��");
		}
		
	}

    //ͨ��Gid�������û���Ȩ��
	@RequestMapping("/admins/updateUsersAuthorityByGid")
	public String updateUsersAuthorityByGid(Authorities authorities,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		try {
			
			adminsService.updateUsersAuthorityByGid(authorities);
			return "redirect:/admins/findAllUsersCustom.action";
		} catch (Exception e) {
			throw new RuntimeException("Controlller--�����û�Ȩ��ʧ��");
		}	
	}
	//��ѯ�û���Ϣͨ��Uid
	@RequestMapping("/admins/findUsersCustomMailboxByUid")
	public ModelAndView findUsersCustomMailboxByUid(HttpServletRequest request){
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			UsersCustom usersCustom =  adminsService.findUsersCustomByUid(uid);
			modelAndView.addObject("usersCustom", usersCustom);
			modelAndView.setViewName("/manage/addNotice");
			return modelAndView;
		} catch (Exception e) {
			throw new RuntimeException("Controller---��ѯ�û�ʧ��");
		}
	}
	//����֪ͨ���û�
	@RequestMapping("/admins/sendNoticeToUsers")
	public String sendNoticeToUsers(MailboxCustom mailboxCustom,int uid,HttpServletRequest request){
		AdminsCustom adminsCustom = (AdminsCustom) request.getSession()
				.getAttribute("adminsCustom");
		 mailboxCustom.setUid(uid);
		 mailboxCustom.setSendid(adminsCustom.getId());
		   // ��ѯ�û�����ʱ��
			long created = System.currentTimeMillis();
			mailboxCustom.setSendtime(created);
			// ��õ�ǰ�û��ͻ���ϵͳ
			String agent = request.getHeader("User-Agent");
			mailboxCustom.setAgent(agent);
			// ��õ�ǰ�û��ķ���ip
			String ip = request.getRemoteAddr();
			mailboxCustom.setIp(ip);
			
			try {
				adminsService.sendNoticeByUsers(mailboxCustom);
				return "redirect:/admins/findAllUsersCustom.action";
			} catch (Exception e) {
				throw new RuntimeException("Controller--��֪ͨ���û�ʧ��");
			}
		
		
	}

	// ����aid����ѯ���µ���ϸ��Ϣ
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
			throw new RuntimeException("Controller---����aid����ѯ����ʧ��");
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
			request.setAttribute("msg", "����ʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		
		return null;
		
	}
}
