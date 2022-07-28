package com.wqm.test;

import com.wqm.dao.BookDao;
import com.wqm.dao.impl.BookDaoImpl;
import com.wqm.pojo.Book;
import com.wqm.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        int nums = bookDao.addBook(new Book(null, "算法导论","Randal E.Bryant", new BigDecimal(125.8), 13,
                500, "static/img/bookCover1.jpg"));
        System.out.println("添加一本书，影响数据库行数为："+nums);
    }

    @Test
    public void deleteBook() {
        int rows = bookDao.deleteBook(3);
        System.out.println("删除一本图书影响的行数为："+rows);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(23, "算法导论","Randal E.Bryant", new BigDecimal(125.8), 13,
                356, "static/img/bookCover1.jpg"));
        System.out.println("修改书的信息");
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(5);
        System.out.println("通过id查询图书：");
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        System.out.println("查询全部图书：");
        for(Book book:books){
            System.out.println(book);
        }
    }

    @Test
    public void queryForTotalCount(){
        System.out.println(bookDao.queryForTotalCount());
    }

    @Test
    public void queryForPageItems(){
        List<Book> books = bookDao.queryForPageItems(1, 4);
        for(Book book: books){
            System.out.println(book);
        }
    }

    @Test
    public void queryForTotalCountByPrice(){
        System.out.println(bookDao.queryForTotalCountByPrice(20, 40));
    }

    @Test
    public void queryForPageItemsByPrice(){
        List<Book> books = bookDao.queryForPageItemsByPrice(0, 7,20, 40);
        for(Book book: books){
            System.out.println(book);
        }
    }


}