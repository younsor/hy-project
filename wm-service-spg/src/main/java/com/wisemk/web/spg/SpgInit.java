package com.wisemk.web.spg;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import cn.zyy.oss.share.OssLog;

@Component
public class SpgInit
{
    private static final OssLog log = new OssLog();

    @PostConstruct
    public void initMethod() throws Exception
    {}
}
