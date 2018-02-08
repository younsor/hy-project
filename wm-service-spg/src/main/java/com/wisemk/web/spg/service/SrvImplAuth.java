package com.wisemk.web.spg.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.wisemk.web.spg.domain.User;
import com.wisemk.web.spg.dto.DtoToken;
import com.wisemk.web.spg.repo.RepoUser;
import com.wisemk.web.spg.share.WConstants;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;

@Service
public class SrvImplAuth
{
    private static final OssLog log = new OssLog();

    @Autowired
    private RepoUser            repoUser;

    @Value("${access.token.expire.seconds}")
    private long                tokenExpireSeconds;

    @Autowired
    private StringRedisTemplate strRedisTemplate;

    public DtoToken loginCode(String appid, String openId, String sessKey, String unionId, String clentIp)
    {
        Date curDate = new Date();
        User pdUser = repoUser.findByOpenidAndDel(openId, 0);
        if (null == pdUser)
        {
            /* 第一次登录小程序, 创建该用户 */
            pdUser = new User();
            pdUser.setAppid(appid);
            pdUser.setOpenid(openId);
            pdUser.setUnionid(unionId);
            pdUser.setRegSource("");
            pdUser.setRegTs(OssFunc.TimeConvert.Date2Format(curDate, OssFunc.TimeConvert.DF_SECOND));
            pdUser.setRegIp(clentIp);
            pdUser.setLoginTs(OssFunc.TimeConvert.Date2Format(curDate, OssFunc.TimeConvert.DF_SECOND));
            pdUser.setLoginIp(clentIp);
            pdUser.setLoginSessKey(sessKey);

            pdUser = repoUser.save(pdUser);
        }

        /* 生成token */
        String token = generateAndSetToken(pdUser.getId());

        DtoToken dtoToken = new DtoToken();
        dtoToken.setId(pdUser.getId());
        dtoToken.setToken(token);

        return dtoToken;
    }

    public boolean isUserExist(int uid)
    {
        User pdUser = repoUser.findByIdAndDel(uid, 0);
        if (null == pdUser)
        {
            return false;
        }

        return true;
    }

    public int getUidFromToken(String token)
    {
        String strUserId = strRedisTemplate.opsForValue().get(token);
        if (null == strUserId || strUserId.length() <= 0)
        {
            return WConstants.RET_ERROR;
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
}
