package com.wqm.web;

import com.wqm.pojo.User;
import com.wqm.service.UserService;
import com.wqm.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("输入的用户名和密码为："+username+","+password);

        User user = userService.login(username, password);
        if(user==null){
            System.out.println(username+"登陆失败");
            //把错误信息保存到request域中，返回给用户
            request.setAttribute("msg","用户名或密码错误");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            System.out.println(username+" 登陆成功");
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }
}
