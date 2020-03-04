package com.lixiaotao.controller;

import com.lixiaotao.pojo.User;
import com.lixiaotao.service.LoginServicr;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private LoginServicr loginServicr;
    @RequestMapping("/goindex")
    public String goindex(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest,httpServletResponse);
        return null;
    }
    @RequestMapping("/login")
    public String login(User user, Model model,HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws ServletException, IOException {
        User res = loginServicr.selectUser(user);
        httpServletRequest.setCharacterEncoding("utf-8");
        if(null==res){
            model.addAttribute("msg","用户名或密码错误");
            httpServletRequest.getRequestDispatcher("index.jsp")
                    .forward(httpServletRequest,httpServletResponse);
            return null;
        }else{
            httpServletRequest.getSession().invalidate();
            Map<User,HttpSession> usermap = (Map<User, HttpSession>) httpServletRequest.getServletContext().getAttribute("usermap");
            if(usermap.containsKey(res)){
                HttpSession session = usermap.get(res);
                session.invalidate();
            }
            httpServletRequest.getSession().setAttribute("user",res);
            httpServletRequest.setAttribute("userinfo",res);
            ServletContext servletContext = httpServletRequest.getServletContext();
            String sourcemessage = "";
            if(null!= servletContext.getAttribute("message")){
                sourcemessage = servletContext.getAttribute("message").toString();
            }
            sourcemessage +="系统公告：" +user.getUser() +"走进了聊天室！<br/>";
            servletContext.setAttribute("message",sourcemessage);
        }
        return "main";
    }

    @RequestMapping("/register")
    public String register(User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String name = user.getUser();
        User user1 = loginServicr.selectUserByName(name);
        if(null!=user1||null==user.getUser()){
            httpServletRequest.setAttribute("msg","用户名已存在");
        }else{
            user.setType(1);
            loginServicr.addUser(user);
            httpServletRequest.setAttribute("msg","注册成功，请重新登陆");
        }
        httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest,httpServletResponse);
        return null;
    }

    @RequestMapping(value = "/find",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void find(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException {
        ServletContext servletContext = httpServletRequest.getServletContext();
        Map<User,HttpSession> userMap = (Map<User, HttpSession>)servletContext.getAttribute("usermap");
        ArrayList<User> list = new ArrayList();
        for(Map.Entry<User,HttpSession> a:userMap.entrySet()){
            list.add(a.getKey());
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        //转成String类型，这里要解释，虽然后面的param的type是json，但是并不影响实际参数是字符串
        String result = jsonArray.toString();
        System.out.println(result);
        httpServletResponse.getWriter().print(result);

    }

    @RequestMapping("/logout/{id}")
    public String logout(@PathVariable int id,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servletContext = httpServletRequest.getServletContext();
        Map<User,HttpSession> map = (Map<User,HttpSession>)servletContext.getAttribute("usermap");
        for(Map.Entry<User,HttpSession> a:map.entrySet()) {
            int idtemp = a.getKey().getId();
            if (id == idtemp) {
                a.getValue().invalidate();
                break;
            }
        }
        return "redirect:/goindex";
    }

}
