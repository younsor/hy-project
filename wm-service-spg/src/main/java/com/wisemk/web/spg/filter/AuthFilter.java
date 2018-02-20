package com.wisemk.web.spg.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.wisemk.web.spg.service.SrvImplAuth;
import com.wisemk.web.spg.share.WConstants;
import com.wisemk.web.spg.share.WJsonResult;

import cn.zyy.oss.share.OssLog;

@Component
public class AuthFilter implements Filter
{
    private static final OssLog log = new OssLog();

    @Value("${filter.auth.exclude:/auth/code}")
    private String[]            excludeUrls;

    @Autowired
    private SrvImplAuth         srvAuth;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void destroy()
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        /* 不需要认证，直接通过 */
        if (!isNeedAuth(httpServletRequest.getServletPath()))
        {
            chain.doFilter(request, response);
            return;
        }

        /* token认证 */
        String token = httpServletRequest.getHeader(WConstants.HTTP_ATTR_TOKEN);
        Integer userId = srvAuth.getUidFromToken(token);
        if (userId != null && userId > 0)
        {
            /* 将token有效期复位 */
            srvAuth.resetTokenExpireTime(token, userId);

            httpServletRequest.setAttribute(WConstants.HTTP_ATTR_USER, userId);
            chain.doFilter(request, response);
            return;
        }

        /* token认证不通过, 则返回错误 */
        WJsonResult jsonResult = new WJsonResult(HttpStatus.UNAUTHORIZED, "login timeout");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = httpServletResponse.getWriter();
        out.println(JSONObject.toJSONString(jsonResult, true));
    }

    private boolean isNeedAuth(String url)
    {
        for (String excludeUrl : excludeUrls)
        {
            if (url.equals(excludeUrl))
            {
                return false;
            }
        }

        return true;
    }
}