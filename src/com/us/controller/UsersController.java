package com.us.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.us.po.ArticlesCustom;
import com.us.po.CommentsCustom;
import com.us.po.MailboxCustom;
import com.us.po.PageBean;
import com.us.po.Praise;
import com.us.po.SchoolLife;
import com.us.po.UsersCustom;
import com.us.service.UsersService;
import com.us.utils.MD5Utils;
import com.us.utils.UploadUtils;

@Controller
public class UsersController {

	ArticlesCustom articlesCustom = new ArticlesCustom();
	PageBean pageBean = new PageBean();
	ModelAndView modelAndView = new ModelAndView();
	@Autowired
	private UsersService usersService;

	// 用户注册
	@RequestMapping("/users/insertUsers")
	public ModelAndView insertUsers(UsersCustom usersCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1，判断验证码是否正确
		// 获得客户端验证码
		
			// 获得当前用户的客户端系统信息
			String agent = request.getHeader("User-Agent");
			usersCustom.setAgent(agent);
			// 获得当前用户的访问ip
			String ip = request.getRemoteAddr();
			usersCustom.setIp(ip);

			// 获得当前用户的注册时间
			long created = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(created);
			request.getSession().setAttribute("dateStr", dateStr);
			// 获得当前用户的登陆时间
			usersCustom.setLoged(created);
			System.out.println("当前时间" + new Date(System.currentTimeMillis())
					+ "\n");
			usersCustom.setCreated(created);
			usersCustom.setUsername(usersCustom.getPhone());
			usersCustom.setNickname(usersCustom.getPhone());
			try {
                  System.out.println(usersCustom.getUsername());
				int n = usersService.insertUsers(usersCustom);
				if (n == 0) {
					request.setAttribute("msg", "手机号已存在，无法进行注册");
					request.getRequestDispatcher("/login.jsp").forward(
							request, response);
				} else {

					UsersCustom usersCustom1 = usersService
							.findUserById(usersCustom.getUid());

					if (usersCustom1 == null) {
						// 逻辑
					} else {
						request.getSession().setAttribute("usersCustom",
								usersCustom1);
						modelAndView.addObject("usersCustom", usersCustom1);
						modelAndView.setViewName("/users/usersLogin");
						return modelAndView;
					}
				}
			} catch (Exception e) {
				request.setAttribute("msg", "用户注册失败");

				request.getRequestDispatcher("/login.jsp").forward(request,
						response);

			}
		

		return null;

	}

	// 用户登陆
	@RequestMapping("/users/loginUsers")
	public ModelAndView loginUsers(UsersCustom usersCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			try {
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			} catch (ServletException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			try {

				UsersCustom usersCustom1 = usersService
						.findUsernameAndPassword(usersCustom);

				int seconds = 2 * 60 * 60;
				Cookie cookie = new Cookie("usersCustom",
						usersCustom1.getUsername() + "=="
								+ usersCustom1.getPassword());
				cookie.setMaxAge(seconds);
				response.addCookie(cookie);
				long loged = usersCustom1.getLoged();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dateStr = sdf.format(loged);

				request.getSession().setAttribute("dateStr", dateStr);
				// 更新用户的登录时间，和用户的客户端系统
				String agent = request.getHeader("User-Agent");
				usersCustom1.setAgent(agent);
				long loged1 = System.currentTimeMillis();
				usersCustom1.setLoged(loged1);
				String ip = request.getRemoteAddr();
				usersCustom1.setIp(ip);
				usersService.updateUserLoged(usersCustom1);

				request.getSession().setAttribute("usersCustom", usersCustom1);
				modelAndView.addObject("usersCustom", usersCustom1);
				modelAndView.setViewName("/users/usersLogin");

				return modelAndView;

			} catch (Exception e) {
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}

		}
		return null;

	}

	// 个人中心
	@RequestMapping("/users/personCenter")
	public ModelAndView personCenter(HttpServletRequest request) {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		modelAndView.addObject("usersCustom", usersCustom);
		modelAndView.setViewName("/users/personCenter");

		return modelAndView;

	}

	// 通过用户id来查询用户信息
	@RequestMapping("/users/findUserById")
	public ModelAndView findUserById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			UsersCustom usersCustom = (UsersCustom) request.getSession()
					.getAttribute("usersCustom");
           if(usersCustom==null){
        	   request.setAttribute("msg", "你还没有登陆");
   			request.getRequestDispatcher("/login.jsp").forward(request,
   					response);
           }else{
			UsersCustom usersCustom1 = usersService.findUserById(usersCustom
					.getUid());
			
			modelAndView.addObject("usersCustom", usersCustom1);
			modelAndView.setViewName("/users/CompletePerInfo");
			return modelAndView;
           }
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		
		return null;

	}
	

	// 更新用户的信息
	@RequestMapping("/users/updateUserInfo")
	public ModelAndView updateUserInfo(HttpServletRequest request,
			HttpServletResponse response, UsersCustom usersCustom,
			@RequestParam MultipartFile avator_img) throws ServletException,
			IOException {
		
		// 原始名称
		String originalFilename = avator_img.getOriginalFilename();

		// 上传图片
		if (avator_img != null && originalFilename != null
				&& originalFilename.length() > 0) {

			// 唯一UUID 随机文件名,MultipartFile avator
			// 新的图片名称
			String uuidname = UUID.randomUUID()
					+ originalFilename.substring(originalFilename
							.lastIndexOf("."));

			// 分散目录生成
			String randomDir = UploadUtils.generateRandomDir(uuidname);
			// 创建随机目录
			File dir = new File(request.getSession().getServletContext()
					.getRealPath("/uploadAvator" + randomDir));
			dir.mkdirs();
			// 文件上传
			InputStream in;

			try {
				in = new BufferedInputStream(avator_img.getInputStream());
				OutputStream out = new BufferedOutputStream(
						new FileOutputStream(new File(dir, uuidname)));
				int b;
				while ((b = in.read()) != -1) {
					out.write(b);
				}
				out.close();
				in.close();
				usersCustom.setAvator("/uploadAvator" + randomDir + "/"
						+ uuidname);
			} catch (IOException e1) {
				throw new RuntimeException(" Controller-----个人头像按流写入错误");
			}
		}
        
        	
        
		try {
			UsersCustom usersCustom1 = usersService.updateUserById(usersCustom);
			if(usersCustom1==null){
				request.setAttribute("msg", "邮箱已经存在");
				request.getRequestDispatcher("/Notice.jsp").forward(request, response);
			}
			request.getSession().setAttribute("usersCustom", usersCustom1);
			modelAndView.addObject("usersCustom", usersCustom1);
			modelAndView.setViewName("/users/personCenter");

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// 查询用户密码
	@RequestMapping("/users/findUserPassword")
	public ModelAndView findUserPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		
       if(usersCustom==null){
    	   request.setAttribute("msg", "你还没有登陆");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
       }else{
		UsersCustom usersCustom1;
		try {
			usersCustom1 = usersService.findUserById(usersCustom.getUid());
			modelAndView.addObject("usersCustom", usersCustom1);
			modelAndView.setViewName("/users/ChangePassword");
			return modelAndView;
		} catch (Exception e) {

			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
       }
		return null;

	}

	// 根据用户id来更新用户的密码
	@RequestMapping("/users/updateUserPasswordById")
	public ModelAndView updateUserPasswordById(UsersCustom usersCustom,
			HttpServletRequest request) {
		// 从Session中获取用户信息
		UsersCustom usersCustom1 = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");

		if (usersCustom1.getPassword().equals(
				MD5Utils.md5(usersCustom.getPassword()))) {
			usersCustom1
					.setPassword(MD5Utils.md5(usersCustom.getNewpassword()));

			try {
				usersService.updateUserPasswordById(usersCustom1);
				request.getSession().setAttribute("usersCustom", usersCustom1);
				modelAndView.addObject("usersCustom", usersCustom1);
				modelAndView.setViewName("/users/personCenter");

				return modelAndView;

			} catch (Exception e) {
				throw new RuntimeException("Controller---更新用户密码失败");
			}
		} else {
			modelAndView.addObject("msg", "原密码输入错误");
			modelAndView.addObject("usersCustom", usersCustom1);
			modelAndView.setViewName("/users/ChangePassword");
			return modelAndView;

		}

	}

	// 通过用户ID来查询用户手机号
	@RequestMapping("/users/findPhoneById")
	public ModelAndView updatePhoneById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		
       if(usersCustom==null){
    	   request.setAttribute("msg", "你还没有登陆");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
       }else{
		UsersCustom usersCustom1;
		try {
			usersCustom1 = usersService.findUserById(usersCustom.getUid());
			modelAndView.addObject("usersCustom", usersCustom1);
			modelAndView.setViewName("/users/ChangePhone");
			return modelAndView;
		} catch (Exception e) {

			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
       }
		return null;
	}

	// 通过用户ID来更改用户手机号
	@RequestMapping("/users/updateUserPhoneById")
	public ModelAndView updateUserPhoneById(UsersCustom usersCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从Session中获取用户信息
		UsersCustom usersCustom1 = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		if (usersCustom1.getPhone().equals(usersCustom.getPhone())) {
			usersCustom1.setPhone(usersCustom.getNewphone());
			try {
				usersService.updateUserPhoneById(usersCustom1);
				request.getSession().setAttribute("usersCustom", usersCustom1);
				modelAndView.addObject("usersCustom", usersCustom1);
				modelAndView.setViewName("/users/personCenter");
				return modelAndView;
			} catch (Exception e) {
				request.setAttribute("msg", "获取信息错误");
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
			}

		}
		return null;
	}

	// 根据用户ID来查询用户的个人信箱
	@RequestMapping("/users/findMailboxByUid")
	public ModelAndView findMailboxByUid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 从Session中获取用户信息
		UsersCustom usersCustom1 = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		// 获得客户端提交的页码
		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
		pageBean.setUid(usersCustom1.getUid());

		try {
			PageBean pageBean1 = usersService.findMailboxByUid(pageBean);

			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/users/personMailbox");

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 根据信箱的mailid来查找信箱中的信息
	@RequestMapping("/users/findMailboxById")
	public ModelAndView findMailboxById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int mailid = Integer.parseInt(request.getParameter("mailid"));
		try {
			MailboxCustom mailboxCustom = usersService.findMailboxById(mailid);
			modelAndView.addObject("mailboxCustom", mailboxCustom);
			modelAndView.setViewName("/users/mailbox_detail");
			return modelAndView;

		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 个人信箱回复
	@RequestMapping("/users/replyMailbox")
	public ModelAndView replyMailbox(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int sendid = Integer.parseInt(request.getParameter("sendid"));

		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");

		if (sendid == 1) {

			request.setAttribute("msg", "系统信息不需要回复");
			request.getRequestDispatcher("/Notice.jsp").forward(request,
					response);
		} else {
			String nickname1 = new String(nickname.getBytes("iso-8859-1"),
					"utf-8");
			MailboxCustom mailboxCustom = new MailboxCustom();
			mailboxCustom.setSendid(sendid);
			mailboxCustom.setUsername(username);
			mailboxCustom.setNickname(nickname1);

			modelAndView.addObject("mailboxCustom", mailboxCustom);
			modelAndView.setViewName("/users/replyMailbox");
		}
		return modelAndView;

	}

	// 将回复信息插入到数据库
	@RequestMapping("/users/insertReplyMailbox")
	public ModelAndView insertReplyMailbox(MailboxCustom mailboxCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersCustom usersCustom1 = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		mailboxCustom.setSendid(usersCustom1.getUid());
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
			int n = usersService.insertReplyMailbox(mailboxCustom);
			if (n != 1) {
				// sssssssssssssssssssssssssssssssssssss
			}
			modelAndView.addObject("usersCustom", usersCustom1);
			modelAndView.setViewName("/users/personCenter");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 通过mailid来删除邮件信息
	@RequestMapping("/users/delMailboxById")
	public String delMailboxById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom1 = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		int mailid = Integer.parseInt(request.getParameter("mailid"));
		try {
			usersService.delMailboxById(mailid);

			return "redirect:/users/findMailboxByUid.action";
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 发送信箱给用户
	@RequestMapping("/users/sendUsersMailbox")
	public ModelAndView sendUsersMailbox(MailboxCustom mailboxCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UsersCustom usersCustom = usersService
					.findUserByUserName(mailboxCustom.getUsername());

			UsersCustom usersCustom1 = (UsersCustom) request.getSession()
					.getAttribute("usersCustom");
			if (usersCustom.getUid() == usersCustom1.getUid()) {
				request.setAttribute("msg2", "不能给自己发信息");
				request.getRequestDispatcher("/send_mailbox.jsp").forward(
						request, response);
			}
			mailboxCustom.setUid(usersCustom.getUid());
			mailboxCustom.setSendid(usersCustom1.getUid());
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
				int n = usersService.insertReplyMailbox(mailboxCustom);
				if (n != 1) {
					request.setAttribute("msg1", "发送邮件失败，请重新发送");
					request.getRequestDispatcher("/send_mailbox.jsp").forward(
							request, response);
				}
				modelAndView.addObject("usersCustom", usersCustom1);
				modelAndView.setViewName("/users/personCenter");
				return modelAndView;
			} catch (Exception e) {
				throw new RuntimeException("Controller--将信息插入到数据库失败");
			}
		} catch (Exception e) {
			request.setAttribute("msg", "用户名输入错误");

			request.getRequestDispatcher("/send_mailbox.jsp").forward(request,
					response);

		}

		return null;

	}

	// 用户点赞功能
	@RequestMapping("/users/addPraise")
	public String addPraise(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom1 = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		if (usersCustom1 == null) {
			request.setAttribute("msg", "你未登录，无法进行点赞");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			Praise praise = new Praise();
			String category1 = request.getParameter("category");
			String category = new String(category1.getBytes("iso-8859-1"),
					"utf-8");
			int aid = Integer.parseInt(request.getParameter("aid"));
			int count = Integer.parseInt(request.getParameter("count")) + 1;
			praise.setAid(aid);
			praise.setUid(usersCustom1.getUid());
			articlesCustom.setAid(aid);
			articlesCustom.setCount(count);
			try {
				ArticlesCustom articlesCustom1 = usersService
						.updateArticlesCountByAid(articlesCustom, praise);
				String s = null;
				if (category.equals("校园风云")) {
					s = "redirect:/users/findSchoolSituation.action";
				} else {
					if (category.equals("校园八卦")) {
						s = "redirect:/users/findSchoolGossip.action";
					} else {
						if (category.equals("校园资讯")) {
							s = "redirect:/users/findCampusConsultation.action";
						} else {
							if (category.equals("校园活动")) {
								s = "redirect:/users/findSchoolActivities.action";
							} else {
								if (category.equals("口碑商")) {
									s = "redirect:/users/findReputationBusinessman.action";
								} else {
									if (category.equals("有活动")) {
										s = "redirect:/users/findActivity.action";
									} else {
										if (category.equals("便生活")) {
											s = "redirect:/users/findThenlife.action";
										}
									}
								}
							}
						}
					}
				}
				return s;
			} catch (Exception e) {
				throw new RuntimeException("Controller--点赞功能失败");
			}

		}
		return null;

	}

	// 添加文章
	@RequestMapping("/users/addarticles")
	public ModelAndView addarticles(ArticlesCustom articlesCustom,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
        String category1= articlesCustom.getCategory();
		String[] authorities = usersCustom.getAuthority().split(",");
		System.out.println(category1.equals("有活动"));
		System.out.println(authorities[7]=="0");
		if (category1.equals("校园八卦")&& authorities[2].equals("0")) {
			request.setAttribute("msg", "权限不足");
			modelAndView.addObject("usersCustom", usersCustom);
			modelAndView.setViewName("/users/personCenter");
			return modelAndView;
		} else {
			if (category1.equals( "校园风云")&&authorities[3].equals("0")) {
				request.setAttribute("msg", "权限不足");
				modelAndView.addObject("usersCustom", usersCustom);
				modelAndView.setViewName("/users/personCenter");
				return modelAndView;
			} else {
				if (category1.equals("校园活动")&&authorities[4].equals("0")) {
					request.setAttribute("msg", "权限不足");
					modelAndView.addObject("usersCustom", usersCustom);
					modelAndView.setViewName("/users/personCenter");
					return modelAndView;
				} else {
					if (category1.equals("校园资讯")&&authorities[5].equals("0")) {
						request.setAttribute("msg", "权限不足");
						modelAndView.addObject("usersCustom", usersCustom);
						modelAndView.setViewName("/users/personCenter");
						return modelAndView;
					} else {
						if (category1.equals("口碑商")&&authorities[6].equals("0")) {
							request.setAttribute("msg", "权限不足");
							modelAndView.addObject("usersCustom", usersCustom);
							modelAndView.setViewName("/users/personCenter");
							return modelAndView;
						} else {
							if (category1.equals("有活动")&&authorities[7].equals("0")) {
								request.setAttribute("msg", "权限不足");
								modelAndView.addObject("usersCustom", usersCustom);
								modelAndView.setViewName("/users/personCenter");
								return modelAndView;
							} else {
								if (category1.equals("便生活")&&authorities[8].equals("0")) {
									request.setAttribute("msg", "权限不足");
									modelAndView.addObject("usersCustom",usersCustom);
									modelAndView
											.setViewName("/users/personCenter");
									return modelAndView;
								}else{
									String ip = request.getRemoteAddr();
									articlesCustom.setIp(ip);
									String agent = request.getHeader("User-Agent");
									articlesCustom.setAgent(agent);
									long created = System.currentTimeMillis();
									articlesCustom.setCreated(created);
									articlesCustom.setModified(created);
									articlesCustom.setAuthorid(usersCustom.getUid());
									try {
										int n = usersService.addArticles(articlesCustom);
										modelAndView.addObject("msg", "文章发表成功");
										modelAndView.addObject("usersCustom", usersCustom);
										modelAndView.setViewName("/users/personCenter");
										return modelAndView;
									} catch (Exception e) {
										throw new RuntimeException("Controller--添加文章失败");
									}
								}
							}
						}
					}
				}
			}
		}
		
	}

	// 根据文章的aid来查询文章的信息
	@RequestMapping("usersfindArticlesCustomByAid")
	public ModelAndView usersfindArticlesCustomByAid(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		
		int aid = Integer.parseInt(request.getParameter("aid"));
		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null && Integer.parseInt(pNumStr) != 0) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();
		pageBean.setpNum(pNum);
		pageBean.setAid(aid);
		try {
			ArticlesCustom articlesCustom = usersService.findArticlesByAid(aid);
			PageBean pageBean1 = usersService.findCommentsCustomByAid(pageBean);
			modelAndView.addObject("usersCustom", usersCustom);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.addObject("articlesCustom", articlesCustom);
			
			modelAndView.setViewName("/cms/articles_detail");
			

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// 添加评论
	@RequestMapping("/users/addCommentsByAid")
	public String addCommentsByAid(CommentsCustom commentsCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		if (usersCustom == null) {
			request.setAttribute("msg", "你未登录，无法进行评论");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			int aid = Integer.parseInt(request.getParameter("aid"));

			commentsCustom.setAid(aid);
			commentsCustom.setAuthorid(usersCustom.getUid());
			String ip = request.getRemoteAddr();
			commentsCustom.setIp(ip);
			String agent = request.getHeader("User-Agent");
			commentsCustom.setAgent(agent);
			long created = System.currentTimeMillis();
			commentsCustom.setCreated(created);
			try {
				usersService.insertCommentsCustom(commentsCustom);
			} catch (Exception e) {
				throw new RuntimeException("Controller--添加评论失败");
			}
			return "redirect:/usersfindArticlesCustomByAid.action?aid=" + aid;
		}
		return null;
	}

	// 查询校园风云
	@RequestMapping("/users/findSchoolSituation")
	public ModelAndView findSchoolSituation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String category = "校园风云";
		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = usersService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);

			modelAndView.setViewName("/cms/SchoolSituation");

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 按条件来查询文章
	@RequestMapping("/users/findArticlesByCondition")
	public ModelAndView findArticlesByCondition(HttpServletRequest request)
			throws UnsupportedEncodingException {

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
		System.out.println(category1.equals("校园风云"));
		try {
			pageBean1 = usersService.findArticlesByCondition(pageBean,
					category1);
			modelAndView.addObject("pageBean", pageBean1);
			if (category1.equals("校园风云")) {
				modelAndView.setViewName("/cms/SchoolSituation");
			} else {
				if (category1.equals("校园八卦")) {
					modelAndView.setViewName("/cms/SchoolGossip");
				} else {
					if (category1.equals("校园资讯")) {
						modelAndView.setViewName("/cms/CampusConsultation");
					} else {
						if (category1.equals("校园活动")) {
							modelAndView.setViewName("/cms/SchoolActivities");
						} else {
							if (category1.equals("口碑商")) {
								modelAndView
										.setViewName("/cms/ReputationBusinessman");
							} else {
								if (category1.equals("便生活")) {
									modelAndView.setViewName("/cms/Thenlife");
								} else {
									if (category1.equals("有活动")) {
										modelAndView
												.setViewName("/cms/Activity");
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
	@RequestMapping("/users/findSchoolGossip")
	public ModelAndView findSchoolGossip(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "校园八卦";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = usersService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/cms/SchoolGossip");

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 查询校园咨讯
	@RequestMapping("/users/findCampusConsultation")
	public ModelAndView findCampusConsultation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "校园资讯";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = usersService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/cms/CampusConsultation");

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 查询校园活动
	@RequestMapping("/users/findSchoolActivities")
	public ModelAndView findSchoolActivities(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "校园活动";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = usersService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/cms/SchoolActivities");

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 查询口碑商
	@RequestMapping("/users/findReputationBusinessman")
	public ModelAndView findReputationBusinessman(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "口碑商";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = usersService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/cms/ReputationBusinessman");

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 查询便生活
	@RequestMapping("/users/findThenlife")
	public ModelAndView findThenlife(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "便生活";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = usersService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/cms/Thenlife");

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 查询有活动
	@RequestMapping("/users/findActivity")
	public ModelAndView findActivity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "有活动";

		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}

		pageBean.setpNum(pNum);
		try {
			PageBean pageBean1 = usersService.findArticlesByName(pageBean,
					category);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/cms/Activity");

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 校园生活的首页
	@RequestMapping("/users/schoolLife")
	public String schoolLife(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			SchoolLife schoolLife = usersService.findSchoolLife();
			request.setAttribute("schoolLife", schoolLife);
			request.getRequestDispatcher("/schoolLife.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 校园周边首页
	@RequestMapping("/users/schoolSide")
	public String schoolSide(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			SchoolLife schoolLife = usersService.findSchoolSide();
			request.setAttribute("schoolLife", schoolLife);
			request.getRequestDispatcher("/schoolSide.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息错误");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 用户添加文章
	@RequestMapping("/usersAddArticles")
	public String usersAddArticles(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		if (usersCustom == null) {
			request.setAttribute("msg", "你未登录，无法进行发布文章");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			request.setAttribute("usersCustom", usersCustom);
			request.getRequestDispatcher("/Users_addarticles.jsp").forward(
					request, response);
		}
		return null;
	}

	// 用户按照邮箱找回密码
	@RequestMapping("/users/findPasswordByEmailNotice")
	public String findPasswordByEmailNotice(String email, String username,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UsersCustom usersCustom = usersService.findUsersCustomByEmail(
					email, username);
			if (usersCustom != null) {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("请求已经收到！地址已发送您注册邮箱，请于2小时内登录页面修改密码！");
			} else {
				request.setAttribute("msg", "邮箱不存在");
				request.getRequestDispatcher("/Forgetpassword.jsp").forward(
						request, response);
			}
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息失败");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// 根据用户uid来更改
	@RequestMapping("/users/findPasswordByEmail")
	public String findPasswordByEmail(UsersCustom usersCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		usersCustom.setPassword(MD5Utils.md5(usersCustom.getNewpassword()));
		try {
			usersService.updateUserPasswordById(usersCustom);
			request.setAttribute("msg", "密码找回成功，请重新登陆");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("msg", "密码修改失败，请重新操作");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}
    //根据用户Uid来查询用户发的文章
	@RequestMapping("/users/findArticlesByUid")
    public ModelAndView findArticlesByUid(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		if(usersCustom==null){
			request.setAttribute("msg","你还没有登陆请登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		int pNum = 1;
		String pNumStr = request.getParameter("pNum");

		if (pNumStr != null) {
			pNum = Integer.parseInt(pNumStr);
		}
		PageBean pageBean = new PageBean();

		pageBean.setpNum(pNum);
		pageBean.setAuthorid(usersCustom.getUid());
		
		try {
			PageBean pageBean1 = usersService.findArticlesByAuthorid(pageBean);
			modelAndView.addObject("pageBean", pageBean1);
			modelAndView.setViewName("/cms/UsersCustomSendArticles");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "获取信息失败");
			request.getRequestDispatcher("/Notice.jsp").forward(request, response);
			}
		return null;
		
    	
    }
	// 根据文章的aid来查询文章的信息
		@RequestMapping("usersupdatefindArticlesCustomByAid")
		public ModelAndView usersupdatefindArticlesCustomByAid(
				HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			UsersCustom usersCustom = (UsersCustom) request.getSession()
					.getAttribute("usersCustom");
			
			int aid = Integer.parseInt(request.getParameter("aid"));
			int pNum = 1;
			String pNumStr = request.getParameter("pNum");

			if (pNumStr != null && Integer.parseInt(pNumStr) != 0) {
				pNum = Integer.parseInt(pNumStr);
			}
			PageBean pageBean = new PageBean();
			pageBean.setpNum(pNum);
			pageBean.setAid(aid);
			try {
				ArticlesCustom articlesCustom = usersService.findArticlesByAid(aid);
				modelAndView.addObject("usersCustom", usersCustom);
				modelAndView.addObject("articlesCustom", articlesCustom);	
				modelAndView.setViewName("/cms/update_articles_detail");
				

				return modelAndView;
			} catch (Exception e) {
				request.setAttribute("msg", "获取信息错误");
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
			}
			return null;

		}
		@RequestMapping("/users/UpdateUserArticlesCustom")
		public String UpdateUserArticlesCustom(ArticlesCustom articlesCustom,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			try {
				
				
				usersService.updateArticlesCustom(articlesCustom);
				
				return "redirect:/users/findArticlesByUid.action";
				
			} catch (Exception e) {
				request.setAttribute("msg", "操作失败");
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
			}
			
			return null;
			
		}
    
}
