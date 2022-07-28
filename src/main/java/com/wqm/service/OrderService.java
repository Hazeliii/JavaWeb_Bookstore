package com.wqm.service;

import com.wqm.pojo.Cart;

public interface OrderService {
    /**
     * 将当前购物车里的所有商品生成一个order记录，购物车里的每一个商品为一条orderItem
     * @param cart 当前购物车
     * @param userId 用户ID
     * @return orderId
     */
    public String createOrder(Cart cart, Integer userId);
}
