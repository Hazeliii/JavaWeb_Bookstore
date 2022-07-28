package com.wqm.test;

import com.wqm.dao.OrderDao;
import com.wqm.dao.impl.orderDaoImpl;
import com.wqm.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new orderDaoImpl();
        int row = orderDao.saveOrder(new Order("orderId", new Date(), new BigDecimal(100), 0, 1));
        System.out.println("OrderDao.saveOrder影响的行数:"+row);
    }
}