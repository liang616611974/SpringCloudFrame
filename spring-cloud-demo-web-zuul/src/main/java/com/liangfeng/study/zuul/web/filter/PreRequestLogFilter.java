package com.liangfeng.study.zuul.web.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: PreRequestLogFilter
 * @Description: 网关打印请求日志过滤器
 * @date  2018/9/6 9:31
 */
@Slf4j
public class PreRequestLogFilter extends ZuulFilter {

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public boolean shouldFilter() {
       return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log .info("send {} request to {}",request.getMethod(),request.getRequestURL());
        return null;
    }

}
