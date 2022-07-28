package com.wqm.service;


import com.wqm.pojo.User;

//业务层，一个业务一个方法
public interface UserService {

    /**
     * 注册
     * @param user
     * 返回ture表示插入成功
     */
    public boolean registerUser(User user);

    /**
     * 登录
     * @param username,password
     * @return
     */
    public User login(String username, String password);

    /**
     * 检查用户名是否已经存在
     * @param username
     * @return true表示用户名已经存在，false表示用户名可用
     */
    public boolean userExists(String username);
}
