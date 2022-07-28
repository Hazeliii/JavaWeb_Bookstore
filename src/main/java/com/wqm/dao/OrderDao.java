package com.wqm.dao;

import com.wqm.pojo.Order;

public interface OrderDao {
    public int saveOrder(Order order);  //保存订单
}
