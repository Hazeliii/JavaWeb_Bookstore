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

public class ClientBookServlet extends BaseServlet{
    //请求分页
    BookService bookService = new BookServiceImpl();

    protected void splitBookPages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("clientBookServlet中的splitBookPages()");
        //获取请求的参数：pageNo和pageSize
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1);  //默认为第一页
        int pageSize = webUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //调用bookService.page(pageNo, pageSize)，获取page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=splitBookPages");
        //保存返回的对象到request
        request.setAttribute("page", page);
        //返回给用户，显示到页面
        request.getRequestDispatcher("/pages/client/index_ajax.jsp").forward(request,response);
    }

    protected void splitBookPagesByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("clientBookServlet中的splitBookPagesByPrice()");
        //获取请求的参数：pageNo和pageSize
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1);  //默认为第一页
        int pageSize = webUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = webUtils.parseInt(request.getParameter("min"), 0);
        int max = webUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        //调用bookService.page(pageNo, pageSize)，获取page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=splitBookPagesByPrice");
        if(request.getParameter("min")!=null){
            stringBuilder.append("&min=").append(request.getParameter("min"));
        }
        if (request.getParameter("max")!=null) {
            stringBuilder.append("&max=").append(request.getParameter("max"));
        }
        System.out.println("splitBookPagesByPrice中的url="+stringBuilder);
        page.setUrl(stringBuilder.toString());
        //保存返回的对象到request
        request.setAttribute("page", page);
        //返回给用户，显示到页面
        request.getRequestDispatcher("/pages/client/index_ajax.jsp").forward(request,response);
    }
}
