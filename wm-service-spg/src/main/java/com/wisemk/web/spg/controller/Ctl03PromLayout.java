package com.wisemk.web.spg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisemk.web.spg.service.SrvImplPromCreative;
import com.wisemk.web.spg.share.JsonResult;

import cn.zyy.oss.share.OssLog;

@RestController
@RequestMapping(value = "/mngr", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Ctl03PromLayout extends BaseController
{
    private static final OssLog log = new OssLog();

    @Autowired
    private SrvImplPromCreative srvPromCreative;

    /* 推广类型下拉列表 */
    @GetMapping(value = { "/promtype" })
    public JsonResult getPromTypePullDown()
    {
        return renderSuccess(srvPromCreative.getPromTypePullDown());
    }

    /* 推广-展示样式 */
    @GetMapping(value = { "/promtype/{id}" })
    public JsonResult getPromImpLayout(@PathVariable(value = "id") int impLayoutId)
    {
        return renderSuccess(srvPromCreative.getPromImpLayout(impLayoutId));
    }

    /* 推广-点击样式下拉列表 */
    @GetMapping(value = { "/clklayout/{id}" })
    public JsonResult getPromClkLayout(@PathVariable(value = "id") int clkLayoutId)
    {
        return renderSuccess(srvPromCreative.getPromClkLayout(clkLayoutId));
    }

    /* 推广-点击样式 */
    @GetMapping(value = { "/clklayout" })
    public JsonResult getPromClkLayoutPullDown(int promotionTypeId)
    {
        return renderSuccess(srvPromCreative.getPromClkLayoutPullDown(promotionTypeId));
    }
}
