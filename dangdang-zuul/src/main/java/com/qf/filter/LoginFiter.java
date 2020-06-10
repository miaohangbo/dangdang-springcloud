package com.qf.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 54110 on 2020/6/4.
 */
@Component
public class LoginFiter extends ZuulFilter {

    @Autowired
    RedisUtils redisUtils;

    private static String LOGIN_URL="/dangdang-user/user/login";
    private static String REGISTRY_URL="/dangdang-user/user/registry";
    private static String EMAIL_URL="/dangdang-user/user/sendEmail";
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String requestURI = request.getRequestURI();

        if (REGISTRY_URL.equals(requestURI)||LOGIN_URL.equals(requestURI)||EMAIL_URL.equals(requestURI)){
          return   false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();

        HttpServletRequest request = currentContext.getRequest();

        String token = request.getHeader("token");

        Object o = redisUtils.get(token);

        if (o==null){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
        }
        return null;
    }
}

