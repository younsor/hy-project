package com.wisemk.web.spg.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.wisemk.web.spg.domain.PdUser;
import com.wisemk.web.spg.dto.DtoToken;
import com.wisemk.web.spg.repo.RepoPdUser;
import com.wisemk.web.spg.share.Constants;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;

@Service
public class SrvImplAuth
{
    private static final OssLog log = new OssLog();

    @Autowired
    private RepoPdUser          repoPdUser;

    @Value("${access.token.expire.seconds}")
    private long                tokenExpireSeconds;

    @Autowired
    private StringRedisTemplate strRedisTemplate;

    public DtoToken login(String account, String passwdMd5)
    {
        PdUser pdUser = repoPdUser.findByAccountAndPasswordAndIsDel(account, passwdMd5, 0);
        if (null == pdUser)
        {
            return null;
        }

        /* 生成token */
        String token = generateAndSetToken(pdUser.getId());

        DtoToken dtoToken = new DtoToken();
        dtoToken.setId(pdUser.getId());
        dtoToken.setName(pdUser.getName());
        dtoToken.setToken(token);

        return dtoToken;
    }

    public void logout(String token)
    {
        delToken(token);
    }

    public boolean isPdUserExist(int pdUid)
    {
        PdUser pdUser = repoPdUser.findByIdAndIsDel(pdUid, 0);
        if (null == pdUser)
        {
            return false;
        }

        return true;
    }

    public int getUserIdFromToken(String token)
    {
        String strUserId = strRedisTemplate.opsForValue().get(token);
        if (null == strUserId || strUserId.length() <= 0)
        {
            return Constants.RET_ERROR;
        }

        return Integer.parseInt(strUserId);
    }

    public void resetTokenExpireTime(String token, int userId)
    {
        strRedisTemplate.opsForValue().set(token, userId + "", tokenExpireSeconds, TimeUnit.SECONDS);
    }

    private String generateAndSetToken(int userId)
    {
        String md5Key = userId + System.currentTimeMillis() + "";
        String md5Token = OssFunc.Md5(md5Key);

        if (tokenExpireSeconds <= 0)
        {
            tokenExpireSeconds = 1800;
        }
        strRedisTemplate.opsForValue().set(md5Token, userId + "", tokenExpireSeconds, TimeUnit.SECONDS);

        return md5Token;
    }

    private void delToken(String token)
    {
        strRedisTemplate.delete(token);
    }
}
