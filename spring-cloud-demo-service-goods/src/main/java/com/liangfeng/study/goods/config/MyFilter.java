package com.liangfeng.study.goods.config;


import com.liangfeng.study.common.constant.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: MyFilter
 * @Description:
 * @date  2018/4/12 10:49
 */
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class MyFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.info("=========MyFilter 经过==============================");
        WebUtils.setSessionAttribute((HttpServletRequest)request, AppConstant.SESSION_ATTR_NAME_USERID,new Long("666"));
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
