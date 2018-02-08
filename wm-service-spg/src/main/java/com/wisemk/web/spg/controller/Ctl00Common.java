package com.wisemk.web.spg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisemk.web.spg.service.SrvImplCommon;
import com.wisemk.web.spg.share.JsonResult;

@RestController
@RequestMapping(value = "/mngr", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Ctl00Common extends BaseController
{
    @Autowired
    private SrvImplCommon srvCommon;

    @GetMapping(value = { "/modules" })
    public JsonResult getModules()
    {
        return renderSuccess(srvCommon.getPdModules());
    }
}
