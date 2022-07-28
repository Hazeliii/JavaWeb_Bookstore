package com.wqm.test;

import com.wqm.pojo.Cart;
import com.wqm.pojo.CartItem;
import com.wqm.service.OrderService;
import com.wqm.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);

        OrderService orderService = new OrderServiceImpl();
        String orderId = orderService.createOrder(cart, 1);
        System.out.println("生成订单！订单号="+orderId);
    }
}