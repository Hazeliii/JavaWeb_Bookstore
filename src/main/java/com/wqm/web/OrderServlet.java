package com.wqm.web;

import com.alibaba.druid.util.JdbcUtils;
import com.wqm.pojo.Cart;
import com.wqm.pojo.User;
import com.wqm.service.OrderService;
import com.wqm.service.impl.OrderServiceImpl;
import com.wqm.utils.jdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---createOrder---");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");
        if(loginUser==null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
            return;
        }
        System.out.println("用户"+loginUser.getUsername()+"的购物车："+cart);
        Integer id = loginUser.getId();
        String orderId = orderService.createOrder(cart, id);

        //防止用户重复提交页面，改为重定向
        //req.setAttribute("orderId", orderId);
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
