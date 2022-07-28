package com.wqm.service.impl;

import com.wqm.dao.BookDao;
import com.wqm.dao.OrderDao;
import com.wqm.dao.OrderItemDao;
import com.wqm.dao.impl.BookDaoImpl;
import com.wqm.dao.impl.orderDaoImpl;
import com.wqm.dao.impl.orderItemDaoImpl;
import com.wqm.pojo.*;
import com.wqm.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new orderDaoImpl();
    private OrderItemDao orderItemDao = new orderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号:需要唯一
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);

        //遍历购物车中每一个商品，保存到orderItem数据库表
        for(Map.Entry<Integer, CartItem> entry: cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();

            orderItem item = new orderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getUnitPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(item);

            //修改商品对应的库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }
}
