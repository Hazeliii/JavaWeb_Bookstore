package com.wqm.test;

import com.wqm.dao.OrderItemDao;
import com.wqm.dao.impl.orderItemDaoImpl;
import com.wqm.pojo.orderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new orderItemDaoImpl();
        int rows = orderItemDao.saveOrderItem(new orderItem(null, "11", 1, new BigDecimal(11),
                new BigDecimal(11), "orderId"));
        orderItemDao.saveOrderItem(new orderItem(null, "人月神话", 1, new BigDecimal(11),
                new BigDecimal(11), "orderId"));
        orderItemDao.saveOrderItem(new orderItem(null, "杀死一只知更鸟", 1, new BigDecimal(11),
                new BigDecimal(11), "orderId"));
        System.out.println("OrderItemDao.saveOrderItem()");
    }
}