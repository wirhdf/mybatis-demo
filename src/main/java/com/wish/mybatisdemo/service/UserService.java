package com.wish.mybatisdemo.service;

import com.wish.mybatisdemo.entity.User;
import java.util.List;

/**
 * 表(user)服务接口
 * @author tangjie
 * @since 2020-05-07 15:11:42
 */
public interface UserService{

    /**
     * 新增
     */
    int add(User user);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 更新
     */
    int update(User user);

    /**
     * 根据ID查询详情
     */
    User selectById(Long id);
    
    /**
     * 查询列表
     */
    List<User> list(User user);
    
}