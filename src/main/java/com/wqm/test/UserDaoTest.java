package com.wqm.test;

import com.wqm.dao.UserDao;
import com.wqm.dao.impl.UserDaoImpl;
import com.wqm.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {

        if(userDao.queryUserByUsername("admin")==null){
            System.out.println("用户名可用！");
        }else{
            System.out.println("用户已经存在！");
        }

    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "wqm", "123", "123@qq.com")));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","12345354")==null){
            System.out.println("用户名和密码不正确，登陆失败");
        }else {
            System.out.println("用户名和密码正确，登陆成功");
        }
    }
}