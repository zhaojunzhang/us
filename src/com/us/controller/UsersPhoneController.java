package com.us.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.us.po.ArticlesCustom;
import com.us.po.PageBean;
import com.us.po.UsersCustom;
import com.us.service.UsersService;
import com.us.utils.JSNOUtil;

@Controller
public class UsersPhoneController {
	ArticlesCustom articlesCustom = new ArticlesCustom();
	PageBean pageBean = new PageBean();
	ModelAndView modelAndView = new ModelAndView();
	@Autowired
	private UsersService usersService;
	// 用户登陆
		@RequestMapping("Phone_loginUsers")
		public void Phone_loginUsers(UsersCustom usersCustom,
				HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		System.out.println("--------------------------");
				try {
					
                    String username=request.getParameter("username");
                    String password = request.getParameter("password");
                    System.out.println(username+password+"----------------------------");
                    usersCustom.setUsername(username);
                    usersCustom.setPassword(password);
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

					String json = JSNOUtil.getJsonFromBean(usersCustom1);
					response.setContentType("application/json;charset=UTF-8");
		             
					response.getWriter().write(json);

				} catch (Exception e) {
					response.getWriter().write(0);
				}

			
			

		}
}
