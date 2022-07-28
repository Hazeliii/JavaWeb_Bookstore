package com.wqm.pojo;

import java.math.BigDecimal;

public class orderItem {
    //订单中的订单项，即订单中每个商品的属性
    private Integer orderItemId;
    private String itemName;
    private Integer itemCount;
    private BigDecimal itemUnitPrice;
    private BigDecimal itemTotalPrice;
    //表明当前商品是属于哪个订单的
    private String orderId;

    public orderItem() {
    }

    public orderItem(Integer orderItemId, String itemName, Integer itemCount, BigDecimal itemUnitPrice, BigDecimal itemTotalPrice, String orderId) {
        this.orderItemId = orderItemId;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemUnitPrice = itemUnitPrice;
        this.itemTotalPrice = itemTotalPrice;
        this.orderId = orderId;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public BigDecimal getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(BigDecimal itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public BigDecimal getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(BigDecimal itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "orderItem{" +
                "orderItemId=" + orderItemId +
                ", itemName='" + itemName + '\'' +
                ", itemCount=" + itemCount +
                ", itemUnitPrice=" + itemUnitPrice +
                ", itemTotalPrice=" + itemTotalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
