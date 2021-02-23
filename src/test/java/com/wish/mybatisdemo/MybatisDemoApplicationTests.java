package com.wish.mybatisdemo;

import com.wish.mybatisdemo.dao.UserDao;
import com.wish.mybatisdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisDemoApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void test01(){
        Map<String, Object> map = userDao.selectMapById(6L);
        map.forEach((k, v) -> System.out.println(k+"-->"+v));
    }

	@Test
    @Transactional
	public void test02(){
        User user1 = userDao.selectById(6L);
        User user2 = userDao.selectById(6L);
        System.out.println(user1);
        System.out.println(user2);
    }

	@Test
	public void test03(){
        List<User> users = userDao.selectTest("st6");
        System.out.println(users);
    }

}
