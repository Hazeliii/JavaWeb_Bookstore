package com.wqm.service.impl;

import com.wqm.dao.BookDao;
import com.wqm.dao.impl.BookDaoImpl;
import com.wqm.pojo.Book;
import com.wqm.pojo.Page;
import com.wqm.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);

    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);

    }

    @Override
    public void updateBook(Book book) {
        System.out.println("BookServiceImpl中的book:"+book);
        bookDao.updateBook(book);

    }

    @Override
    public Book queryBookById(Integer id) {
        Book book = bookDao.queryBookById(id);
        if(book==null){
            System.out.println("queryBookById没有查询到书.");
        }
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();

        page.setPageSize(pageSize);
        //求总记录数
        Integer totalItemsCount = bookDao.queryForTotalCount();
        page.setTotalItemsCount(totalItemsCount);

        //求总页码
        Integer pageTotal = totalItemsCount / pageSize;
        if(totalItemsCount % pageSize !=0) pageTotal++;
        page.setPageTotal(pageTotal);

        //必须放到设置了pageTotal之后！不然没有办法将设置的pageNo与pageTotal进行对比
        page.setPageNo(pageNo);

        //求当前页数据,注意不能直接用传进来的pageNo进行计算，因为不一定在规定范围内，用get方法得到的是正确的
        int begin = (page.getPageNo()-1)*pageSize;  //当前页开始的数据索引
        List<Book> currentItems = bookDao.queryForPageItems(begin, pageSize);
        page.setCurrentItems(currentItems);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {

        Page<Book> page = new Page<Book>();
        page.setPageSize(pageSize);
        //求总记录数
        Integer totalItemsCount = bookDao.queryForTotalCountByPrice(min, max);
        page.setTotalItemsCount(totalItemsCount);

        //求总页码
        Integer pageTotal = totalItemsCount / pageSize;
        if(totalItemsCount % pageSize !=0) pageTotal++;
        page.setPageTotal(pageTotal);

        //必须放到设置了pageTotal之后！不然没有办法将设置的pageNo与pageTotal进行对比
        page.setPageNo(pageNo);

        //求当前页数据,注意不能直接用传进来的pageNo进行计算，因为不一定在规定范围内，用get方法得到的是正确的
        int begin = (page.getPageNo()-1)*pageSize;  //当前页开始的数据索引
        List<Book> currentItems = bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        page.setCurrentItems(currentItems);
        return page;
    }
}
