package com.wqm.web;

import com.google.gson.Gson;
import com.wqm.pojo.User;
import com.wqm.service.UserService;
import com.wqm.service.impl.UserServiceImpl;
import com.wqm.utils.webUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    //通过继承BaseServlet()方法，执行doPost()，然后再执行对应的反射
    UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-----正在处理用户登录操作-----");

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
            request.getSession().setAttribute("user", user); //保存登陆成功的user到session中
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("-------正在处理用户注册操作------");
        // 获取 Session 中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = request.getParameter("code");

        //1.通过调用webUtils.copyParamToBean来获取传进来的参数
        User user = webUtils.copyParamToBean(request.getParameterMap(), new User());

        //检查验证码是否正确
        if(token!=null && token.equalsIgnoreCase(code)){
            if(userService.userExists(user.getUsername())){
                //用户名已经存在
                request.setAttribute("msg", "用户名已经存在！");
                System.out.println("用户名"+user.getUsername()+"已经存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }else {
                //用户名可用
                userService.registerUser(new User(null, user.getUsername(), user.getPassword(), user.getEmail()));
                System.out.println(user.getUsername()+" 注册成功");
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        }else {
            //跳回注册页面
            //返回错误信息给用户
            request.setAttribute("msg", "验证码错误！");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());
            System.out.println("注册 "+user.getUsername()+"时验证码错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("注销用户");
        //注销用户并返回首页
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }


    protected void ajaxUserExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //利用ajax判断用户输入的用户名是否存在
        String username = request.getParameter("username");
        boolean usernameIsExisted = userService.userExists(username);
        System.out.println("");
        Map<String, Object>results = new HashMap<>();
        results.put("usernameIsExisted", usernameIsExisted);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(results);
        response.getWriter().write(jsonStr);

    }
}
