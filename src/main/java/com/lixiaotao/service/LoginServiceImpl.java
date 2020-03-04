package com.lixiaotao.service;

import com.lixiaotao.dao.LoginMapper;
import com.lixiaotao.pojo.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginServicr {
    @Autowired
    private LoginMapper loginMapper;
    public User selectUser(User user) {
        return loginMapper.selectUser(user);
    }

    public User selectUserByName(String user) {
        return loginMapper.selectUserByName(user);
    }

    public boolean addUser(User user) {
        return loginMapper.addUser(user);
    }

    public boolean deleUser(int id) {
        return loginMapper.deleUser(id);
    }

    public boolean updateUser(User user) {
        return loginMapper.updateUser(user);
    }
}
