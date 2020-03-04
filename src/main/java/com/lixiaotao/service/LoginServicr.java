package com.lixiaotao.service;

import com.lixiaotao.pojo.User;

import java.util.List;

public interface LoginServicr {
    User selectUser(User user);
    User selectUserByName(String user);
    boolean addUser(User user);
    boolean deleUser(int id);
    boolean updateUser(User user);
}
