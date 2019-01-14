package com.chen.mybatis.comm.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * xss攻击拦截器
 *
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-14 上午11:18
 **/
@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
public class XssFilter implements Filter {

    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
