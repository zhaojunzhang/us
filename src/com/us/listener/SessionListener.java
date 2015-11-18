package com.us.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.us.po.UsersCustom;


public class SessionListener implements HttpSessionListener,ServletContextListener{ 
    
    private int count; 
    private ServletContext servletContext = null; 
        
    public SessionListener() { 
        count = 0; 
    } 
    
    private Logger logger = Logger.getLogger(this.getClass()); 
    @Override
    public void sessionCreated(HttpSessionEvent event) { 
        count++; 
        setContext(event); 
        logger.info("***************the  http session is created...***************"); 
    } 
    
    @Override
    public void sessionDestroyed(HttpSessionEvent event) { 
        //在session销毁的时候 把loginUserMap中保存的键值对清除  
        UsersCustom usersCustom = (UsersCustom)event.getSession().getAttribute("loginUser"); 
        if(usersCustom!=null){ 
            Map<String, String> loginUserMap = (Map<String, String>)event.getSession().getServletContext().getAttribute("loginUserMap"); 
            loginUserMap.remove(usersCustom.getUsername()); 
            event.getSession().getServletContext().setAttribute("loginUserMap",loginUserMap); 
        } 
            
        count--; 
        setContext(event); 
        logger.info("***************the  http session is destroyed...***************"); 
    } 
    
    public void setContext(HttpSessionEvent httpSessionEvent){ 
        httpSessionEvent.getSession().getServletContext().setAttribute("online", count); 
    } 
        
        
    @Override
    public void contextDestroyed(ServletContextEvent servletcontextevent) {      
        this.servletContext = null; 
        logger.info("***************the  servlet context is destroyed...***************"); 
    } 
    
    @Override
    public void contextInitialized(ServletContextEvent servletcontextevent) { 
        this.servletContext = servletcontextevent.getServletContext(); 
        logger.info("***************the  servlet context is initialized...***************"); 
    } 
}