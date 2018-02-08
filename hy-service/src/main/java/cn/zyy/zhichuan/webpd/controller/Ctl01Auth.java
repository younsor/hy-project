package cn.zyy.zhichuan.webpd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;
import cn.zyy.zhichuan.webpd.dto.DtoToken;
import cn.zyy.zhichuan.webpd.service.SrvImplAuth;
import cn.zyy.zhichuan.webpd.share.Constants;
import cn.zyy.zhichuan.webpd.share.JsonResult;

@RestController
@RequestMapping(value = "/mngr", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Ctl01Auth extends BaseController
{
    private static final OssLog log = new OssLog();

    @Autowired
    private SrvImplAuth         srvAuth;

    @PostMapping(value = "/login")
    public JsonResult login(@RequestParam("loginname") String loginName, @RequestParam("password") String passwdMd5)
    {
        if (OssFunc.isEmpty(loginName))
        {
            return renderError(HttpStatus.UNAUTHORIZED, "用户名为空!");
        }

        if (OssFunc.isEmpty(passwdMd5))
        {
            return renderError(HttpStatus.UNAUTHORIZED, "用户密码为空!");
        }

        DtoToken dtoToken = srvAuth.login(loginName, passwdMd5);
        if (null == dtoToken)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "用户名或密码不存在!");
        }

        return renderSuccess(dtoToken);
    }

    @PostMapping(value = "/logout")
    public JsonResult logout(@RequestHeader(Constants.HTTP_HEADER_USER_TOKEN) String token)
    {
        srvAuth.logout(token);
        return renderSuccess();
    }
}
