package com.wqm.pojo;

import java.util.List;

/**
 * Page为分页的模型对象
 * @param <T> 页面中显示的数据的类型（很多种类型的数据在显示时均需要分类）
 */
public class Page<T> {
    //分页对象,属性一般直接从数据库或者客户端获取

    public static final  Integer PAGE_SIZE = 4;  //每页显示的数目
    private Integer pageNo;  // 当前为第几页
    private Integer pageTotal;  //一共有多少页

    private Integer pageSize = PAGE_SIZE;
    private Integer totalItemsCount;   //总的数据的数量
    private List<T> currentItems;  //当前页显示的数据

    private String url; //分页条中的请求地址

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", totalItemsCount=" + totalItemsCount +
                ", currentItems=" + currentItems +
                ", url='" + url + '\'' +
                '}';
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo) {
        //如果pageNo<1,则显示第一页，如果pageNo>总页数，则设置为最后一页,数据边界的有效检查
        if(pageNo<1){
            pageNo = 1;
        } else if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getTotalItemsCount() {
        return totalItemsCount;
    }

    public void setTotalItemsCount(Integer totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
    }

    public List<T> getCurrentItems() {
        return currentItems;
    }

    public void setCurrentItems(List<T> currentItems) {
        this.currentItems = currentItems;
    }



}
