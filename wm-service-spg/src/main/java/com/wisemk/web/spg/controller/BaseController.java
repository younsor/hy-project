package com.wisemk.web.spg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisemk.web.spg.domain.User;
import com.wisemk.web.spg.repo.RepoUser;
import com.wisemk.web.spg.share.WConstants;
import com.wisemk.web.spg.share.WJsonResult;

import cn.zyy.oss.share.OssFunc;

@Controller
public abstract class BaseController
{
    @Autowired
    private RepoUser repoUser;

    public String getToken()
    {
        return OssFunc.DataConvert.toStr(getRequest().getAttribute(WConstants.HTTP_ATTR_TOKEN), "");
    }

    public Integer getUid()
    {
        return (Integer) getRequest().getAttribute(WConstants.HTTP_ATTR_USER);
    }

    public Integer getMallId()
    {
        int uid = getUid();

        User user = repoUser.findByIdAndDel(uid, 0);
        if (null == user)
        {
            return 0;
        }

        return user.getMallId();
    }

    public String getClientIp()
    {
        return "";
    }

    protected HttpServletRequest getRequest()
    {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected WJsonResult renderExc(String msg)
    {
        WJsonResult result = new WJsonResult();
        result.setCode(HttpStatus.BAD_REQUEST);
        result.setMessage(msg);
        return result;
    }

    protected WJsonResult renderError(HttpStatus status, String msg)
    {
        WJsonResult result = new WJsonResult();
        result.setCode(status);
        result.setMessage(msg);
        return result;
    }

    protected WJsonResult renderSuccess(Object obj)
    {
        WJsonResult result = new WJsonResult();
        result.setCode(HttpStatus.OK);
        result.setData(obj);
        return result;
    }

    protected WJsonResult renderSuccess()
    {
        WJsonResult result = new WJsonResult();
        result.setCode(HttpStatus.OK);
        result.setMessage("操作成功");
        return result;
    }
}
