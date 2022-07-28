package com.wqm.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    //private Integer totalCount;
    //private BigDecimal totalPrice;
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
//        for(Map.Entry<Integer, CartItem> itemEntry: items.entrySet()){
//            totalCount += itemEntry.getValue().getCount();
//        }
        for (CartItem value : items.values()) {
            totalCount += value.getCount();
        }
        return totalCount;
    }

//    public void setTotalCount(Integer totalCount) {
//        this.totalCount = totalCount;
//    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem value : items.values()) {
            totalPrice = totalPrice.add(value.getTotalPrice());
        }
        return totalPrice;
    }

//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    //添加商品项
    public void addItem(CartItem cartItem){
        //当商品存在时，直接累加（数量，总金额）
        CartItem existedItem = items.get(cartItem.getId());
        if(existedItem == null){
            items.put(cartItem.getId(), cartItem);
        }else {
            existedItem.setCount(existedItem.getCount()+cartItem.getCount());
            existedItem.setTotalPrice(existedItem.getUnitPrice().multiply(new BigDecimal(existedItem.getCount())));
        }
    }

    public void deleteItem(int id){
        items.remove(id);
    }

    //清空购物车
    public void clear(){
        items.clear();
    }

    public void updateItemCount(Integer id, Integer count){
        CartItem existedItem = items.get(id);
        if(existedItem != null){
            existedItem.setCount(count);
            existedItem.setTotalPrice(existedItem.getUnitPrice().multiply(new BigDecimal(existedItem.getCount())));
        }
    }
}
