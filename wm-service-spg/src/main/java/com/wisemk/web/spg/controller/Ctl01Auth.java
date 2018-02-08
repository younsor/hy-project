package com.wisemk.web.spg.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.wisemk.web.spg.dto.DtoToken;
import com.wisemk.web.spg.service.SrvImplAuth;
import com.wisemk.web.spg.share.WHttpReq;
import com.wisemk.web.spg.share.WJsonResult;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Ctl01Auth extends BaseController
{
    private static final OssLog log = new OssLog();

    @Autowired
    private SrvImplAuth         srvAuth;

    @PostMapping(value = "/code")
    public WJsonResult login(String code, String appid)
    {
        if (OssFunc.isEmpty(code))
        {
            return renderError(HttpStatus.UNAUTHORIZED, "code为空");
        }

        String codeReqUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> mapParam = Maps.newHashMap();
        mapParam.put("appid", appid);
        mapParam.put("secret", "");
        mapParam.put("js_code", "");
        mapParam.put("grant_type", "authorization_code");
        String strRet = WHttpReq.httpsGet(codeReqUrl, mapParam);
        if (OssFunc.isEmpty(strRet))
        {
            return renderExc("jscode2session error");
        }

        JSONObject jsonRet = JSONObject.parseObject(strRet);
        String strOpenId = jsonRet.getString("openid");
        String strSessKey = jsonRet.getString("session_key");
        String strUnionId = jsonRet.getString("unionid");
        String clientIp = getClientIp();

        /* 处理用户信息, 生成此次会话token */
        DtoToken dtoToken = srvAuth.loginCode(appid, strOpenId, strSessKey, strUnionId, clientIp);
        return renderSuccess(dtoToken);
    }
}
