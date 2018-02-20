package com.wisemk.web.spg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisemk.web.spg.domain.User;
import com.wisemk.web.spg.repo.RepoUser;

import cn.zyy.oss.share.OssLog;

@Service
public class SrvImplUser
{
    private static final OssLog log = new OssLog();

    @Autowired
    private RepoUser            repoUser;
}
