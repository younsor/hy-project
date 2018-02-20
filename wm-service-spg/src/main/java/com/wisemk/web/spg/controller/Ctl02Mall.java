package com.wisemk.web.spg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisemk.web.spg.dto.DtoMall;
import com.wisemk.web.spg.service.SrvImplMall;
import com.wisemk.web.spg.share.WJsonResult;

@RestController
@RequestMapping(value = "/mall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Ctl02Mall extends BaseController
{
    @Autowired
    private SrvImplMall srvMall;

    @GetMapping(value = "/info")
    public WJsonResult getMallInfo()
    {
        int mallId = getMallId();
        DtoMall dtoMall = srvMall.getMainInfo(mallId);
        return renderSuccess(dtoMall);
    }
}
