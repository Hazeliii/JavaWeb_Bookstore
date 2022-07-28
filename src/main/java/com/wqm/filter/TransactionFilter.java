package com.wqm.filter;

import com.wqm.utils.jdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            jdbcUtils.commitAndClose();
        } catch (Exception e) {
            jdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e); //把异常抛给tomcat，返回给用户进行错误展示
        }

    }

    @Override
    public void destroy() {
    }
}
