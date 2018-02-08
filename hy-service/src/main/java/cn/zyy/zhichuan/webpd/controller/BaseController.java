package cn.zyy.zhichuan.webpd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.zyy.zhichuan.webpd.share.Constants;
import cn.zyy.zhichuan.webpd.share.JsonResult;

@Controller
public abstract class BaseController
{
    public Integer getUserId()
    {
        return (Integer) getRequest().getAttribute(Constants.HTTP_ATTR_USER_ID);
    }

    protected HttpServletRequest getRequest()
    {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected JsonResult renderExc(String msg)
    {
        JsonResult result = new JsonResult();
        result.setCode(HttpStatus.BAD_REQUEST);
        result.setMessage(msg);
        return result;
    }

    protected JsonResult renderError(HttpStatus status, String msg)
    {
        JsonResult result = new JsonResult();
        result.setCode(status);
        result.setMessage(msg);
        return result;
    }

    protected JsonResult renderSuccess(Object obj)
    {
        JsonResult result = new JsonResult();
        result.setCode(HttpStatus.OK);
        result.setData(obj);
        return result;
    }

    protected JsonResult renderSuccess()
    {
        JsonResult result = new JsonResult();
        result.setCode(HttpStatus.OK);
        result.setMessage("操作成功");
        return result;
    }
}
