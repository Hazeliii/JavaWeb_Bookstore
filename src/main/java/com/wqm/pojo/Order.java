package com.wqm.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    //订单项，即每次购买时一起下单的所有东西
    private String orderId;
    private Date orderCreatedTime;
    private BigDecimal orderTotalPrice;
    //订单状态：0 未发货 1 已发货 2 已签收
    private Integer orderStatus;
    //表明该订单是哪个用户的
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date orderCreatedTime, BigDecimal orderTotalPrice, Integer orderStatus, Integer userId) {
        this.orderId = orderId;
        this.orderCreatedTime = orderCreatedTime;
        this.orderTotalPrice = orderTotalPrice;
        this.orderStatus = orderStatus;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderCreatedTime() {
        return orderCreatedTime;
    }

    public void setOrderCreatedTime(Date orderCreatedTime) {
        this.orderCreatedTime = orderCreatedTime;
    }

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderCreatedTime=" + orderCreatedTime +
                ", orderTotalPrice=" + orderTotalPrice +
                ", orderStatus=" + orderStatus +
                ", userId=" + userId +
                '}';
    }
}
