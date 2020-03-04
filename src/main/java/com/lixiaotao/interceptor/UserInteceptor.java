package com.lixiaotao.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

     if(null==request.getSession().getAttribute("user")){
         request.getRequestDispatcher("index.jsp").forward(request,response);
         return false;
     }
        return true;
    }
}
