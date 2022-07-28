package com.wqm.dao;

import com.wqm.pojo.User;

public interface UserDao {
    //快捷键 ctrl+shift+T 对该接口中的方法创建测试类

    //注册时，首先需要查询用户名是否有效（唯一），返回Null时表示不存在该用户
    public User  queryUserByUsername(String username);

    //保存用户信息
    public int saveUser(User user);

    //登陆时，需要根据用户名和密码查询用户
    public User queryUserByUsernameAndPassword(String username, String password);
}
