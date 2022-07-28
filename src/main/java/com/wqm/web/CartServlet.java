package com.wqm.web;

import com.google.gson.Gson;
import com.wqm.pojo.Book;
import com.wqm.pojo.Cart;
import com.wqm.pojo.CartItem;
import com.wqm.service.BookService;
import com.wqm.service.impl.BookServiceImpl;
import com.wqm.utils.webUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void addItemToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //加入购物车
        System.out.println("---addItemToCart()---");
        Integer bookId = webUtils.parseInt(request.getParameter("id"), 0);
        Book book = bookService.queryBookById(bookId);
        //System.out.println("从浏览器获取的id:"+bookId+", book的id="+book.getId());
        CartItem cartItem = new CartItem(bookId, book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println("当前购物车:"+cart);
        request.getSession().setAttribute("lastAddedItem", cartItem.getName());
        //request.getHeader("Referer"):返回浏览器跳转过来时候的地址
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void addItemToCartByAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //加入购物车
        System.out.println("---addItemToCart()---");
        Integer bookId = webUtils.parseInt(request.getParameter("id"), 0);
        Book book = bookService.queryBookById(bookId);
        //System.out.println("从浏览器获取的id:"+bookId+", book的id="+book.getId());
        CartItem cartItem = new CartItem(bookId, book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println("当前购物车:"+cart);
        //request.getSession().setAttribute("lastAddedItem", cartItem.getName());
        //request.getHeader("Referer"):返回浏览器跳转过来时候的地址
        //response.sendRedirect(request.getHeader("Referer"));
        //直接用json数据通过ajax返回前端所需数据即可
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastAddedItem", cartItem.getName());
        Gson gson = new Gson();
        String resultMapJson = gson.toJson(resultMap);
        response.getWriter().write(resultMapJson);
    }

    protected void deleteItemInCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //删除购物车中的商品项
        System.out.println("---deleteItemInCart()---");
        Integer itemId = webUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(itemId);
        }
        System.out.println("当前购物车:"+cart);
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清空购物车
        System.out.println("---clearCart()----");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
        }
        System.out.println("当前购物车:"+cart);
        response.sendRedirect(request.getHeader("Referer"));
    }


    protected void updateItemCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清空购物车
        System.out.println("---updateItemCount()----");
        Integer itemId = webUtils.parseInt(request.getParameter("itemId"), 0);
        Integer count = webUtils.parseInt(request.getParameter("count"), 1);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateItemCount(itemId, count);
        }
        System.out.println("当前购物车:"+cart);
        response.sendRedirect(request.getHeader("Referer"));
    }


}
