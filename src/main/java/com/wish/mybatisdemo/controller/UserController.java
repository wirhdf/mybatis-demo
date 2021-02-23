package com.wish.mybatisdemo.controller;

import com.wish.mybatisdemo.entity.User;
import com.wish.mybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
 * 表(user)控制层
 * @author tangjie
 * @since 2020-05-07 15:44:13
 */
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;
    
   /**
    * 新增
    */
    @PostMapping(value="/add")
    public String insert(@RequestBody User user){
        userService.add(user);
        return "新增成功";
    }
    
   /**
    * 删除
    */
    @PostMapping(value="/delete")
    public String delete(@RequestBody User user){
        userService.deleteById(user.getId());
        return "删除成功";
    }
    
   /**
    * 更新
    */
    @RequestMapping(value="/update")
    public String update(@RequestBody User user){
        userService.update(user);
        return "更新成功";
    }
    
   /**
    * 根据ID查询详情
    */
    @PostMapping(value="/get")
    public User get(@RequestBody User user){
        User userResult = userService.selectById(user.getId());
        return userResult;
    }
    
    /**
    * 查询列表
    */
    @PostMapping(value="/list")
    public List<User> list(@RequestBody User user){
        List<User> userList = userService.list(user);
        return userList;
    }

}