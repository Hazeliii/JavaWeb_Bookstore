package com.wqm.dao;

import com.alibaba.druid.support.spring.stat.SpringMethodStatValue;
import com.wqm.pojo.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);

    public int deleteBook(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    //求总记录数
    public int queryForTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);

    public Integer queryForTotalCountByPrice(int min, int max);
}
