package com.hk.geteway.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 资源过滤器
 * 所有的资源请求在路由之前进行前置过滤
 * 如果请求头不包含 Authorization参数值，直接拦截不再路由
 *
 * @author kevin
 * @date 2018-07-25 09:45
 */
public class AccessFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器的类型 pre表示请求在路由之前被过滤
     *
     * @return pre
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器的执行顺序
     *
     * @return 顺序 数字越大表示优先级越低，越后执行
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 过滤器是否会被执行
     *
     * @return true
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        LOGGER.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        Object accessToken = request.getHeader("Authorization");
        if (accessToken == null) {
            LOGGER.warn("Authorization token is empty");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            requestContext.setResponseBody("Authorization token is empty");
            return null;
        }
        LOGGER.info("Authorization token is ok");
        return null;
    }
}
