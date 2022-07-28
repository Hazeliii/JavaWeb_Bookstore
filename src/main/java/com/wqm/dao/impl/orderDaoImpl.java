package com.wqm.dao.impl;

import com.wqm.dao.OrderDao;
import com.wqm.pojo.Order;

public class orderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`order_create_time`,`order_total_price`," +
                "`order_status`, `user_id`) values (?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getOrderCreatedTime(), order.getOrderTotalPrice(),
                order.getOrderStatus(), order.getUserId());
    }
}
