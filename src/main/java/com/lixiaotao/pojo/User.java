package com.lixiaotao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.sound.midi.Soundbank;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements HttpSessionBindingListener {
    private int id;
    private String user;
    private String pwd;
    private int type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return Objects.equals(user, user1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    // Javabean与session绑定
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("进入了。。。");
        //通过事件对象获取事件源对象
        HttpSession session = httpSessionBindingEvent.getSession();
        //从servletContext 获得人员列表的map集合
        Map<User,HttpSession> userMap = (Map<User, HttpSession>) session
                .getServletContext().getAttribute("usermap");
        //将当前user和session存入map
        userMap.put(this,session);

    }

    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("tuichule...");
        HttpSession session = httpSessionBindingEvent.getSession();
        Map<User,HttpSession> userMap = (Map<User, HttpSession>) session
                .getServletContext().getAttribute("usermap");
        userMap.remove(this);
        Queue
    }
}
