package com.wqm.test;

import com.wqm.pojo.Book;
import com.wqm.pojo.Page;
import com.wqm.service.BookService;
import com.wqm.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "麦田里的守望者", "杰罗姆·大卫·塞林格", new BigDecimal(33),
                231, 142, "static/img/bookCover1.jpg"));
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(7);
    }

    @Test
    public void updateBook() {
        Book book = new Book(24, "麦田里的守望者1", "杰罗姆·大卫·塞林格", new BigDecimal(43),
                231, 142, "static/img/bookCover1.jpg");
        bookService.updateBook(book);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(24));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for(Book book:books){
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        Page<Book> page = bookService.page(1, Page.PAGE_SIZE);
        System.out.println(page);
    }

    @Test
    public void pageByPrice(){
        Page<Book> page = bookService.pageByPrice(2, Page.PAGE_SIZE, 20, 40);
        System.out.println(page);
    }
}