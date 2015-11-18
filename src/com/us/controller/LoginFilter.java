package com.us.controller;  
  
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.us.po.UsersCustom;
import com.us.service.UsersService;
@Controller
public class LoginFilter implements Filter {  
  
	@Autowired
	UsersService usersService;
    public void destroy() {  
        // TODO Auto-generated method stub  
  
    }  
  
    public void doFilter(ServletRequest arg0, ServletResponse arg1,  
            FilterChain arg2) throws IOException, ServletException {  
        HttpServletRequest request = (HttpServletRequest) arg0;  
        HttpServletResponse response = (HttpServletResponse) arg1;  
        Cookie[] cookies = request.getCookies();  
        String[] cooks = null;  
        String username = null;  
        String password = null;  
        if (cookies != null) {  
            for (Cookie coo : cookies) {  
                String aa = coo.getValue();  
                cooks = aa.split("==");  
                if (cooks.length == 2) {  
                    username = cooks[0];  
                    password = cooks[1];  
                }  
            }  
        }  
        UsersCustom usersCustom = new UsersCustom();
        usersCustom.setUsername(username);
        usersCustom.setPassword(password);
        try {
        	if(usersCustom!=null){
			UsersCustom	usersCustom1 = usersService.findUsernameAndPassword(usersCustom);
			request.getSession().setAttribute("usersCustom",
					usersCustom1);
			request.setAttribute("usersCustom", usersCustom1);
			 request.getRequestDispatcher("/jsp/users/usersLogin.jsp").forward(request,
						response);
        	}
        		  arg2.doFilter(request,response );  
        		 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
          
      
  
    }  
  
    public void init(FilterConfig arg0) throws ServletException {  
        // TODO Auto-generated method stub  
  
    }  
  
}  
