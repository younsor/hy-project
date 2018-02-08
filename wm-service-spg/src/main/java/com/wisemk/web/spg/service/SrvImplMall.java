package com.wisemk.web.spg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisemk.web.spg.domain.Mall;
import com.wisemk.web.spg.repo.RepoMall;

import cn.zyy.oss.share.OssLog;

@Service
public class SrvImplMall
{
    private static final OssLog log = new OssLog();

    @Autowired
    private RepoMall            repoMall;

    public String getSecret(String appid)
    {
        Mall mall = repoMall.findByAppidAndDel(appid, 0);
        if (null == mall)
        {
            return null;
        }

        return mall.getSecret();
    }
}
