package com.wish.mybatisdemo.service.impl;

import com.wish.mybatisdemo.entity.User;
import com.wish.mybatisdemo.dao.UserDao;
import com.wish.mybatisdemo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 表(user)服务实现类
 * @author tangjie
 * @since 2020-05-07 15:11:42
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 新增
     */
    @Override
    public int add(User user) {
        user.setCreateTime(new Date());
        return userDao.insert(user);
    }

    /**
     * 根据ID删除
     */
    @Override
    public int deleteById(Long id) {
        return userDao.deleteById(id);
    }

    /**
     * 更新
     */
    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    /**
     * 根据ID查询详情
     */
    @Override
    public User selectById(Long id) {
        return userDao.selectById(id);
    }

    /**
     * 查询列表
     */
    @Override
    public List<User> list(User user) {
        return userDao.selectList(user);
    }
}