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
         * ����û�û�е�½�����½��������½��Ϣ�ŵ�application��
         * <P>
         * ��ϢΪ�û�id���û�sessionid���û���½ʱ�䣬��½IP
         * <P>
         * ����û��ѵ�½����ÿ������Ҫ���application��
         * <P>
         * һ���û�id��ͬ��sessionid��ͬ�����������û��������ط���½��
         * <P>
         * ��ǰ��½������ע��
         * <P>
         * ע������Ϊ������ǰsessionʧЧ��ת����½ҳ�棬
         * <P>
         * ��ʾ�û����û�id������̨����ʲôʱ���½�ˣ���ǰ��½��ע��
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
            // ����Ѿ��е�½��Ϣ
            if (userMap != null) {
                log.info("----SingleFilter.userMap != null");
                String hisSesssionid = (String) userMap.get("sessionid");
                String ip = (String) userMap.get("ip");
                String date1 = (String) userMap.get("date1");
                if (!curSessionid.equals(hisSesssionid)) {
                    String jspMess = username + "����" + date1 + "��" + ip
                            + "��½���˷������ϣ���ǰ��½�ѱ�ע����";
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