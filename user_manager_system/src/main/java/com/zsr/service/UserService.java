package com.zsr.service;

import com.zsr.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    //查询所有用户
    List<User> findAll();

    //添加用户
    void add(User user);

    //修改用户信息
    void update(User user);

    //删除指定用户
    void deleteByID(int id);

    //分页查询
    List<User> findByPage(int nowPage, int rows);

    //查询总条数
    Long findTotals();
}
