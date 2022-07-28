package com.wqm.test;

import com.wqm.pojo.User;
import com.wqm.service.UserService;
import com.wqm.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
       boolean succ = userService.registerUser(new User(null, "Jonny", "jonny", "jonny@gamil.com"));
       if(succ){
           System.out.println("添加用户成功！");
       }else {
           System.out.println("添加用户失败！");
       }
    }

    @Test
    public void login() {
        User user = userService.login("Jonny1", "jonny");
        if(user==null){
            System.out.println("登陆失败");
        }else {
            System.out.println(user.getUsername()+" 登陆成功!");
        }
    }

    @Test
    public void userExists() {
        boolean exist = userService.userExists("Jonny1");
        if(exist){
            System.out.println("用户名已经存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}