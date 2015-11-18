package com.us.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.us.po.UsersCustom;
public class SingleFilter implements Filter {
    private static Logger log = Logger.getLogger(SingleFilter.class);
    private FilterConfig filterConfig;
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        /**
         * 如果用户没有登陆，则登陆，并将登陆信息放到application，
         * <P>
         * 信息为用户id，用户sessionid，用户登陆时间，登陆IP
         * <P>
         * 如果用户已登陆，则每次请求要检查application，
         * <P>
         * 一旦用户id相同而sessionid不同，即表明该用户在其它地方登陆，
         * <P>
         * 当前登陆无条件注销
         * <P>
         * 注销过程为：将当前session失效，转到登陆页面，
         * <P>
         * 提示用户该用户id已在哪台机器什么时间登陆了，当前登陆已注销
         */
        boolean isLogin = session.getAttribute("usersCustom") != null;
        log.info("----SingleFilter.isLogin = "+isLogin);
        if (isLogin) {
           UsersCustom usersCustom =  (UsersCustom) session.getAttribute("usersCustom");
           String username = usersCustom.getUsername();
            String curSessionid = session.getId();
            Map single = (Map) session.getAttribute(
                    "single");
            if (single == null) {
                log.info("----SingleFilter single == null : "+single == null);
                return;
            }
            Map userMap = (Map) single.get(username);
            // 如果已经有登陆信息
            if (userMap != null) {
                log.info("----SingleFilter.userMap != null");
                String hisSesssionid = (String) userMap.get("sessionid");
                String ip = (String) userMap.get("ip");
                String date1 = (String) userMap.get("date1");
                if (!curSessionid.equals(hisSesssionid)) {
                    String jspMess = username + "已于" + date1 + "从" + ip
                            + "登陆到了服务器上，当前登陆已被注销！";
                    request.setAttribute("ERROR", jspMess);
                    session.removeAttribute("NAME");
                    session.invalidate();
                    filterConfig.getServletContext().getRequestDispatcher(
                            "/main.jsp").forward(request, response);
                    return;
                }
                log.info("----SingleFilter.hisSessionid not null!");
            }
        }
        chain.doFilter(request, response);
    }
    public void destroy() {
    }
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
}