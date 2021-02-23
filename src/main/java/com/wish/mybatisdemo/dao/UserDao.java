package com.wish.mybatisdemo.dao;

import com.wish.mybatisdemo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (User)表数据库访问层
 *
 * @author tangjie
 * @since 2020-05-07 15:11:42
 */
public interface UserDao {

    /**
     * 新增
     */
    int insert(User user);

    /**
     * 根据id删除
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
    List<User> selectList(User user);

    Map<String,Object> selectMapById(Long id);

    List<User> selectTest(@Param("s") String str);

}