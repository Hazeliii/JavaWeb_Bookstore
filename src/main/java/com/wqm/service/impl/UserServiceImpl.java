package com.wqm.service.impl;

import com.wqm.dao.UserDao;
import com.wqm.dao.impl.UserDaoImpl;
import com.wqm.pojo.User;
import com.wqm.service.UserService;

public class UserServiceImpl implements UserService{
    //需要操作数据库
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean registerUser(User user) {
        int rows = userDao.saveUser(user);
        if(rows>=0){
            return true;
        } else{
            return false;
        }

    }

    @Override
    public User login(String username, String password) {
        return userDao.queryUserByUsernameAndPassword(username, password);
    }

    @Override
    public boolean userExists(String username) {
        if(userDao.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }
}
