package com.wqm.service;

import com.wqm.pojo.Book;
import com.wqm.pojo.Page;

import java.util.List;

public interface BookService {

    public void addBook(Book book);
    public void deleteBook(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    public Page<Book> page(int pageNo, int pageSize);

    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
