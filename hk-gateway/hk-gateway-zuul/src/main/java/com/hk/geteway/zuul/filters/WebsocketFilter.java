package com.hk.geteway.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huangkai
 * @date 2018-9-22 22:26
 */
public class WebsocketFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 0;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String upgradeHeader = request.getHeader("Upgrade");
        if (null == upgradeHeader) {
            upgradeHeader = request.getHeader("upgrade");
        }
        if (null != upgradeHeader && "websocket".equalsIgnoreCase(upgradeHeader)) {
            context.addZuulRequestHeader("connection", "Upgrade");
        }
        return null;
    }
}
