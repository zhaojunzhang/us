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

	// �û�ע��
	@RequestMapping("/users/insertUsers")
	public ModelAndView insertUsers(UsersCustom usersCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1���ж���֤���Ƿ���ȷ
		// ��ÿͻ�����֤��
		
			// ��õ�ǰ�û��Ŀͻ���ϵͳ��Ϣ
			String agent = request.getHeader("User-Agent");
			usersCustom.setAgent(agent);
			// ��õ�ǰ�û��ķ���ip
			String ip = request.getRemoteAddr();
			usersCustom.setIp(ip);

			// ��õ�ǰ�û���ע��ʱ��
			long created = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(created);
			request.getSession().setAttribute("dateStr", dateStr);
			// ��õ�ǰ�û��ĵ�½ʱ��
			usersCustom.setLoged(created);
			System.out.println("��ǰʱ��" + new Date(System.currentTimeMillis())
					+ "\n");
			usersCustom.setCreated(created);
			usersCustom.setUsername(usersCustom.getPhone());
			usersCustom.setNickname(usersCustom.getPhone());
			try {
                  System.out.println(usersCustom.getUsername());
				int n = usersService.insertUsers(usersCustom);
				if (n == 0) {
					request.setAttribute("msg", "�ֻ����Ѵ��ڣ��޷�����ע��");
					request.getRequestDispatcher("/login.jsp").forward(
							request, response);
				} else {

					UsersCustom usersCustom1 = usersService
							.findUserById(usersCustom.getUid());

					if (usersCustom1 == null) {
						// �߼�
					} else {
						request.getSession().setAttribute("usersCustom",
								usersCustom1);
						modelAndView.addObject("usersCustom", usersCustom1);
						modelAndView.setViewName("/users/usersLogin");
						return modelAndView;
					}
				}
			} catch (Exception e) {
				request.setAttribute("msg", "�û�ע��ʧ��");

				request.getRequestDispatcher("/login.jsp").forward(request,
						response);

			}
		

		return null;

	}

	// �û���½
	@RequestMapping("/users/loginUsers")
	public ModelAndView loginUsers(UsersCustom usersCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				// �����û��ĵ�¼ʱ�䣬���û��Ŀͻ���ϵͳ
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
				request.setAttribute("msg", "�û������������");
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}

		}
		return null;

	}

	// ��������
	@RequestMapping("/users/personCenter")
	public ModelAndView personCenter(HttpServletRequest request) {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		modelAndView.addObject("usersCustom", usersCustom);
		modelAndView.setViewName("/users/personCenter");

		return modelAndView;

	}

	// ͨ���û�id����ѯ�û���Ϣ
	@RequestMapping("/users/findUserById")
	public ModelAndView findUserById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			UsersCustom usersCustom = (UsersCustom) request.getSession()
					.getAttribute("usersCustom");
           if(usersCustom==null){
        	   request.setAttribute("msg", "�㻹û�е�½");
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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		
		return null;

	}
	

	// �����û�����Ϣ
	@RequestMapping("/users/updateUserInfo")
	public ModelAndView updateUserInfo(HttpServletRequest request,
			HttpServletResponse response, UsersCustom usersCustom,
			@RequestParam MultipartFile avator_img) throws ServletException,
			IOException {
		
		// ԭʼ����
		String originalFilename = avator_img.getOriginalFilename();

		// �ϴ�ͼƬ
		if (avator_img != null && originalFilename != null
				&& originalFilename.length() > 0) {

			// ΨһUUID ����ļ���,MultipartFile avator
			// �µ�ͼƬ����
			String uuidname = UUID.randomUUID()
					+ originalFilename.substring(originalFilename
							.lastIndexOf("."));

			// ��ɢĿ¼����
			String randomDir = UploadUtils.generateRandomDir(uuidname);
			// �������Ŀ¼
			File dir = new File(request.getSession().getServletContext()
					.getRealPath("/uploadAvator" + randomDir));
			dir.mkdirs();
			// �ļ��ϴ�
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
				throw new RuntimeException(" Controller-----����ͷ����д�����");
			}
		}
        
        	
        
		try {
			UsersCustom usersCustom1 = usersService.updateUserById(usersCustom);
			if(usersCustom1==null){
				request.setAttribute("msg", "�����Ѿ�����");
				request.getRequestDispatcher("/Notice.jsp").forward(request, response);
			}
			request.getSession().setAttribute("usersCustom", usersCustom1);
			modelAndView.addObject("usersCustom", usersCustom1);
			modelAndView.setViewName("/users/personCenter");

			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// ��ѯ�û�����
	@RequestMapping("/users/findUserPassword")
	public ModelAndView findUserPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		
       if(usersCustom==null){
    	   request.setAttribute("msg", "�㻹û�е�½");
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

			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
       }
		return null;

	}

	// �����û�id�������û�������
	@RequestMapping("/users/updateUserPasswordById")
	public ModelAndView updateUserPasswordById(UsersCustom usersCustom,
			HttpServletRequest request) {
		// ��Session�л�ȡ�û���Ϣ
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
				throw new RuntimeException("Controller---�����û�����ʧ��");
			}
		} else {
			modelAndView.addObject("msg", "ԭ�����������");
			modelAndView.addObject("usersCustom", usersCustom1);
			modelAndView.setViewName("/users/ChangePassword");
			return modelAndView;

		}

	}

	// ͨ���û�ID����ѯ�û��ֻ���
	@RequestMapping("/users/findPhoneById")
	public ModelAndView updatePhoneById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		
       if(usersCustom==null){
    	   request.setAttribute("msg", "�㻹û�е�½");
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

			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
       }
		return null;
	}

	// ͨ���û�ID�������û��ֻ���
	@RequestMapping("/users/updateUserPhoneById")
	public ModelAndView updateUserPhoneById(UsersCustom usersCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��Session�л�ȡ�û���Ϣ
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
				request.setAttribute("msg", "��ȡ��Ϣ����");
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
			}

		}
		return null;
	}

	// �����û�ID����ѯ�û��ĸ�������
	@RequestMapping("/users/findMailboxByUid")
	public ModelAndView findMailboxByUid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ��Session�л�ȡ�û���Ϣ
		UsersCustom usersCustom1 = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		// ��ÿͻ����ύ��ҳ��
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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ���������mailid�����������е���Ϣ
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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ��������ظ�
	@RequestMapping("/users/replyMailbox")
	public ModelAndView replyMailbox(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int sendid = Integer.parseInt(request.getParameter("sendid"));

		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");

		if (sendid == 1) {

			request.setAttribute("msg", "ϵͳ��Ϣ����Ҫ�ظ�");
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

	// ���ظ���Ϣ���뵽���ݿ�
	@RequestMapping("/users/insertReplyMailbox")
	public ModelAndView insertReplyMailbox(MailboxCustom mailboxCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersCustom usersCustom1 = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		mailboxCustom.setSendid(usersCustom1.getUid());
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
			int n = usersService.insertReplyMailbox(mailboxCustom);
			if (n != 1) {
				// sssssssssssssssssssssssssssssssssssss
			}
			modelAndView.addObject("usersCustom", usersCustom1);
			modelAndView.setViewName("/users/personCenter");
			return modelAndView;
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ͨ��mailid��ɾ���ʼ���Ϣ
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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ����������û�
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
				request.setAttribute("msg2", "���ܸ��Լ�����Ϣ");
				request.getRequestDispatcher("/send_mailbox.jsp").forward(
						request, response);
			}
			mailboxCustom.setUid(usersCustom.getUid());
			mailboxCustom.setSendid(usersCustom1.getUid());
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
				int n = usersService.insertReplyMailbox(mailboxCustom);
				if (n != 1) {
					request.setAttribute("msg1", "�����ʼ�ʧ�ܣ������·���");
					request.getRequestDispatcher("/send_mailbox.jsp").forward(
							request, response);
				}
				modelAndView.addObject("usersCustom", usersCustom1);
				modelAndView.setViewName("/users/personCenter");
				return modelAndView;
			} catch (Exception e) {
				throw new RuntimeException("Controller--����Ϣ���뵽���ݿ�ʧ��");
			}
		} catch (Exception e) {
			request.setAttribute("msg", "�û����������");

			request.getRequestDispatcher("/send_mailbox.jsp").forward(request,
					response);

		}

		return null;

	}

	// �û����޹���
	@RequestMapping("/users/addPraise")
	public String addPraise(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom1 = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		if (usersCustom1 == null) {
			request.setAttribute("msg", "��δ��¼���޷����е���");
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
				if (category.equals("У԰����")) {
					s = "redirect:/users/findSchoolSituation.action";
				} else {
					if (category.equals("У԰����")) {
						s = "redirect:/users/findSchoolGossip.action";
					} else {
						if (category.equals("У԰��Ѷ")) {
							s = "redirect:/users/findCampusConsultation.action";
						} else {
							if (category.equals("У԰�")) {
								s = "redirect:/users/findSchoolActivities.action";
							} else {
								if (category.equals("�ڱ���")) {
									s = "redirect:/users/findReputationBusinessman.action";
								} else {
									if (category.equals("�л")) {
										s = "redirect:/users/findActivity.action";
									} else {
										if (category.equals("������")) {
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
				throw new RuntimeException("Controller--���޹���ʧ��");
			}

		}
		return null;

	}

	// �������
	@RequestMapping("/users/addarticles")
	public ModelAndView addarticles(ArticlesCustom articlesCustom,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
        String category1= articlesCustom.getCategory();
		String[] authorities = usersCustom.getAuthority().split(",");
		System.out.println(category1.equals("�л"));
		System.out.println(authorities[7]=="0");
		if (category1.equals("У԰����")&& authorities[2].equals("0")) {
			request.setAttribute("msg", "Ȩ�޲���");
			modelAndView.addObject("usersCustom", usersCustom);
			modelAndView.setViewName("/users/personCenter");
			return modelAndView;
		} else {
			if (category1.equals( "У԰����")&&authorities[3].equals("0")) {
				request.setAttribute("msg", "Ȩ�޲���");
				modelAndView.addObject("usersCustom", usersCustom);
				modelAndView.setViewName("/users/personCenter");
				return modelAndView;
			} else {
				if (category1.equals("У԰�")&&authorities[4].equals("0")) {
					request.setAttribute("msg", "Ȩ�޲���");
					modelAndView.addObject("usersCustom", usersCustom);
					modelAndView.setViewName("/users/personCenter");
					return modelAndView;
				} else {
					if (category1.equals("У԰��Ѷ")&&authorities[5].equals("0")) {
						request.setAttribute("msg", "Ȩ�޲���");
						modelAndView.addObject("usersCustom", usersCustom);
						modelAndView.setViewName("/users/personCenter");
						return modelAndView;
					} else {
						if (category1.equals("�ڱ���")&&authorities[6].equals("0")) {
							request.setAttribute("msg", "Ȩ�޲���");
							modelAndView.addObject("usersCustom", usersCustom);
							modelAndView.setViewName("/users/personCenter");
							return modelAndView;
						} else {
							if (category1.equals("�л")&&authorities[7].equals("0")) {
								request.setAttribute("msg", "Ȩ�޲���");
								modelAndView.addObject("usersCustom", usersCustom);
								modelAndView.setViewName("/users/personCenter");
								return modelAndView;
							} else {
								if (category1.equals("������")&&authorities[8].equals("0")) {
									request.setAttribute("msg", "Ȩ�޲���");
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
										modelAndView.addObject("msg", "���·���ɹ�");
										modelAndView.addObject("usersCustom", usersCustom);
										modelAndView.setViewName("/users/personCenter");
										return modelAndView;
									} catch (Exception e) {
										throw new RuntimeException("Controller--�������ʧ��");
									}
								}
							}
						}
					}
				}
			}
		}
		
	}

	// �������µ�aid����ѯ���µ���Ϣ
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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}

	// �������
	@RequestMapping("/users/addCommentsByAid")
	public String addCommentsByAid(CommentsCustom commentsCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		if (usersCustom == null) {
			request.setAttribute("msg", "��δ��¼���޷���������");
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
				throw new RuntimeException("Controller--�������ʧ��");
			}
			return "redirect:/usersfindArticlesCustomByAid.action?aid=" + aid;
		}
		return null;
	}

	// ��ѯУ԰����
	@RequestMapping("/users/findSchoolSituation")
	public ModelAndView findSchoolSituation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String category = "У԰����";
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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ����������ѯ����
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
		System.out.println(category1.equals("У԰����"));
		try {
			pageBean1 = usersService.findArticlesByCondition(pageBean,
					category1);
			modelAndView.addObject("pageBean", pageBean1);
			if (category1.equals("У԰����")) {
				modelAndView.setViewName("/cms/SchoolSituation");
			} else {
				if (category1.equals("У԰����")) {
					modelAndView.setViewName("/cms/SchoolGossip");
				} else {
					if (category1.equals("У԰��Ѷ")) {
						modelAndView.setViewName("/cms/CampusConsultation");
					} else {
						if (category1.equals("У԰�")) {
							modelAndView.setViewName("/cms/SchoolActivities");
						} else {
							if (category1.equals("�ڱ���")) {
								modelAndView
										.setViewName("/cms/ReputationBusinessman");
							} else {
								if (category1.equals("������")) {
									modelAndView.setViewName("/cms/Thenlife");
								} else {
									if (category1.equals("�л")) {
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
			throw new RuntimeException("Controller--��������ѯ����ʧ��");
		}

	}

	// ��ѯУ԰����
	@RequestMapping("/users/findSchoolGossip")
	public ModelAndView findSchoolGossip(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "У԰����";

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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ��ѯУ԰��Ѷ
	@RequestMapping("/users/findCampusConsultation")
	public ModelAndView findCampusConsultation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "У԰��Ѷ";

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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ��ѯУ԰�
	@RequestMapping("/users/findSchoolActivities")
	public ModelAndView findSchoolActivities(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "У԰�";

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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ��ѯ�ڱ���
	@RequestMapping("/users/findReputationBusinessman")
	public ModelAndView findReputationBusinessman(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "�ڱ���";

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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ��ѯ������
	@RequestMapping("/users/findThenlife")
	public ModelAndView findThenlife(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "������";

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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// ��ѯ�л
	@RequestMapping("/users/findActivity")
	public ModelAndView findActivity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = "�л";

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
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// У԰�������ҳ
	@RequestMapping("/users/schoolLife")
	public String schoolLife(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			SchoolLife schoolLife = usersService.findSchoolLife();
			request.setAttribute("schoolLife", schoolLife);
			request.getRequestDispatcher("/schoolLife.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// У԰�ܱ���ҳ
	@RequestMapping("/users/schoolSide")
	public String schoolSide(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			SchoolLife schoolLife = usersService.findSchoolSide();
			request.setAttribute("schoolLife", schoolLife);
			request.getRequestDispatcher("/schoolSide.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣ����");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// �û��������
	@RequestMapping("/usersAddArticles")
	public String usersAddArticles(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		if (usersCustom == null) {
			request.setAttribute("msg", "��δ��¼���޷����з�������");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			request.setAttribute("usersCustom", usersCustom);
			request.getRequestDispatcher("/Users_addarticles.jsp").forward(
					request, response);
		}
		return null;
	}

	// �û����������һ�����
	@RequestMapping("/users/findPasswordByEmailNotice")
	public String findPasswordByEmailNotice(String email, String username,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UsersCustom usersCustom = usersService.findUsersCustomByEmail(
					email, username);
			if (usersCustom != null) {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("�����Ѿ��յ�����ַ�ѷ�����ע�����䣬����2Сʱ�ڵ�¼ҳ���޸����룡");
			} else {
				request.setAttribute("msg", "���䲻����");
				request.getRequestDispatcher("/Forgetpassword.jsp").forward(
						request, response);
			}
		} catch (Exception e) {
			request.setAttribute("msg", "��ȡ��Ϣʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	// �����û�uid������
	@RequestMapping("/users/findPasswordByEmail")
	public String findPasswordByEmail(UsersCustom usersCustom,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		usersCustom.setPassword(MD5Utils.md5(usersCustom.getNewpassword()));
		try {
			usersService.updateUserPasswordById(usersCustom);
			request.setAttribute("msg", "�����һسɹ��������µ�½");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} catch (Exception e) {
			request.setAttribute("msg", "�����޸�ʧ�ܣ������²���");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;

	}
    //�����û�Uid����ѯ�û���������
	@RequestMapping("/users/findArticlesByUid")
    public ModelAndView findArticlesByUid(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		UsersCustom usersCustom = (UsersCustom) request.getSession()
				.getAttribute("usersCustom");
		if(usersCustom==null){
			request.setAttribute("msg","�㻹û�е�½���¼");
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
			request.setAttribute("msg", "��ȡ��Ϣʧ��");
			request.getRequestDispatcher("/Notice.jsp").forward(request, response);
			}
		return null;
		
    	
    }
	// �������µ�aid����ѯ���µ���Ϣ
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
				request.setAttribute("msg", "��ȡ��Ϣ����");
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
				request.setAttribute("msg", "����ʧ��");
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
			}
			
			return null;
			
		}
    
}
