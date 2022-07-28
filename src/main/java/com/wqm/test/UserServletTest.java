package com.wqm.test;

import com.wqm.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Method;

public class UserServletTest {
    protected void Login(){
        System.out.println("执行login()方法");
    }
    protected void Register(){
        System.out.println("执行Register()方法");
    }
    protected void updatePassword(){
        System.out.println("执行updatePassword()方法");
    }
    protected void updateUser(){
        System.out.println("执行updateUser()方法");
    }

    public static void main(String[] args) {
        String action = "Login";
        try {
            Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(declaredMethod);  //com.wqm.test.UserServletTest.Login()

            //通过invoke调用目标方法
            declaredMethod.invoke(new UserServletTest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
