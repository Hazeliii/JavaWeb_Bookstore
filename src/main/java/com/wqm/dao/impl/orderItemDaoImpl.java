package com.wqm.dao.impl;

import com.wqm.dao.OrderItemDao;
import com.wqm.pojo.orderItem;

public class orderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(orderItem orderItem) {
        String sql = "insert into t_orderitem(`item_name`,`item_unit_price` ," +
                "`item_total_money`, `item_count`, `order_id`) values (?,?,?,?,?)";
        return update(sql, orderItem.getItemName(), orderItem.getItemUnitPrice(), orderItem.getItemTotalPrice(),
                orderItem.getItemCount(), orderItem.getOrderId());
    }
}
