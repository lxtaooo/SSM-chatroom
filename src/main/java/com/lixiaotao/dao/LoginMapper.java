package com.lixiaotao.dao;

import com.lixiaotao.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginMapper {
   User selectUser(User user);
   User selectUserByName(@Param("username") String user);
    boolean addUser(User user);
    boolean deleUser(int id);
    boolean updateUser(User user);
}
