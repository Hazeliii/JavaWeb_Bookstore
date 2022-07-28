package com.wqm.web;

import com.wqm.pojo.User;
import com.wqm.service.UserService;
import com.wqm.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * 用户注册功能
 * 1.获取请求参数，由于password不能被看见，用post方法
 * 2.检查验证码是否正确
 * 2.1 正确：检查用户名是否可用
 *   2.1.1 可用：保存到数据库，跳转到 注册成功页面
 *   2.1.2 不可用：跳转回注册页面
 * 2.2 不正确：跳转到注册页面
 */
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email ");
        String code = request.getParameter("code");

        //检查验证码 先写死验证码为 abc
        if(code.equalsIgnoreCase("abc")){
            if(userService.userExists(username)){
                //用户名已经存在
                request.setAttribute("msg", "用户名以及存在！");
                System.out.println("用户名"+username+"已经存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }else {
                //用户名可用
                userService.registerUser(new User(null, username, password, email));
                System.out.println(username+" 注册成功");
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        }else {
            //跳回注册页面
            //返回错误信息给用户
            request.setAttribute("msg", "验证码错误！");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            System.out.println("注册 "+username+"时验证码错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }

    }
}
