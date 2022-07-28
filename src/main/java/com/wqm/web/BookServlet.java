package com.wqm.web;

import com.wqm.pojo.Book;
import com.wqm.pojo.Page;
import com.wqm.service.BookService;
import com.wqm.service.impl.BookServiceImpl;
import com.wqm.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //多加了一本书之后，可能会排到当前所有页数的下一页，所以直接显示totalPage+1的那一页
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"),0) +1;
        System.out.println("addBook()");
        Book book = webUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.addBook(book);
        //重定向到展示全部图书界面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=splitBookPages&pageNo="+pageNo);
    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"),0);
        System.out.println("deleteBook()");
        //获取图书的id
        int id = webUtils.parseInt(request.getParameter("id"),0);
        System.out.println("删除的图书的id为："+id);
        bookService.deleteBook(id);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=splitBookPages&pageNo="+pageNo);
    }

    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updateBook()");
        /**
         *  System.out.println(request.getParameter("id"));
         *   int id = webUtils.parseInt(request.getParameter("id"),0);
         *   System.out.println("id="+id);
         *   request.setAttribute("id", id);
         *    Set<String> keys = request.getParameterMap().keySet();
         *    System.out.println(keys);
         */
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"),0);
        Book book = webUtils.copyParamToBean(request.getParameterMap(), new Book());
        System.out.println("更新之后的book："+book);
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=splitBookPages&pageNo="+pageNo);

    }
    protected void queryAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("queryAllBooks()");
        //1.查询全部图书
        List<Book> books = bookService.queryBooks();
//        2.把全部图书保存到request域
        request.setAttribute("books", books);
//        3.转发到/pages/manager/book_manager.jsp中
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getBook()");
        //根据图书编号查询该书，将书的相关信息返回到页面
        int id = webUtils.parseInt(request.getParameter("id"), 0);
        //request.setAttribute("id", id);
        Book book = bookService.queryBookById(id);
        System.out.println("getBook()中得到的book的id="+id+"，图书信息为"+book);
        request.setAttribute("book", book);
//        3.转发到/pages/manager/book_edit.jsp中
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }

    /**
     * 对book数据进行分类
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void splitBookPages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("splitBookPages()");
        //获取请求的参数：pageNo和pageSize
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1); //默认为第一页
        int pageSize = webUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        System.out.println("获取到的:pageNo="+pageNo+", pageSize="+pageSize);

        //调用bookService.page(pageNo, pageSize)，获取page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=splitBookPages");
        System.out.println("设置之后：pageNo="+page.getPageNo()+", pageSize="+pageSize);
        //保存返回的对象到request
        request.setAttribute("page", page);
        //返回给用户，显示到页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }


}
