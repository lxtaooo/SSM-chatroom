package com.lixiaotao.controller;

import com.lixiaotao.pojo.User;
import com.lixiaotao.service.LoginServicr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginServicr loginServicr;
    @RequestMapping("/kick")
    public String kick(String username, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        Map<User, HttpSession> usermap = (Map<User, HttpSession>) httpServletRequest.getServletContext().getAttribute("usermap");
        User user = loginServicr.selectUserByName(username);


        if(user!=null&&user.getType()!=0){
            HttpSession session = usermap.get(user);
            session.invalidate();
            //usermap.remove(user);
        }
        User user1 = (User) httpServletRequest.getSession().getAttribute("user");
        httpServletRequest.setAttribute("userinfo",user1);
        return "main";
    }

    @RequestMapping("/getMessage")
    public String getMessage(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws IOException {
        String message = (String) httpServletRequest.getServletContext().getAttribute("message");
        if(message!=null){
            httpServletResponse.getWriter().print(message);
        }
        return null;
    }

    @RequestMapping("/send")
    public String send(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException {
        String post = httpServletRequest.getParameter("post");
        String get = httpServletRequest.getParameter("get");
        String message = httpServletRequest.getParameter("message");
        if(get.equals("")){
            httpServletResponse.getWriter().print(1);
            return null;
        }
        if(message.equals(""))
        {
            httpServletResponse.getWriter().print(2);
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        ServletContext servletContext = httpServletRequest.getServletContext();
        String allmessage = (String) servletContext.getAttribute("message");
        allmessage+= post+"对"+get+"说：“"+message+"”("+format+")<br/>";
        servletContext.setAttribute("message",allmessage);
        return "redirect:/user/getMessage";
    }

    @RequestMapping("/check")
    public String check(HttpServletResponse httpServletResponsem,HttpServletRequest httpServletRequest) throws IOException {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if(user==null)
        {
            httpServletResponsem.getWriter().print(1);
        }else {
            httpServletResponsem.getWriter().print(2);
        }
        return null;
    }
}
