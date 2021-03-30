package com.zsr.service;

import com.zsr.dao.UserDao;
import com.zsr.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteByID(int id) {
        userDao.deleteByID(id);
    }

    @Override
    public List<User> findByPage(int nowPage, int rows) {
        int begin = (nowPage - 1) * rows;
        return userDao.findByPage(begin, rows);
    }


    @Override
    public Long findTotals() {
        return userDao.findTotals();
    }
}
