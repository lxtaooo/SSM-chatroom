package com.lixiaotao.listener;

import com.lixiaotao.pojo.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class MyServletListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("----------------------------------------------");
        Map<User, HttpSession> userMap = new HashMap();
        servletContextEvent.getServletContext().setAttribute("usermap",userMap);

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
