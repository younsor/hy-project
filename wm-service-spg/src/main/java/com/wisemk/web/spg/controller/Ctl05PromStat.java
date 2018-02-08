package com.wisemk.web.spg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisemk.web.spg.dto.DtoBasicStatsChart;
import com.wisemk.web.spg.service.SrvImplTrackStats;
import com.wisemk.web.spg.share.JsonResult;

@RestController
@RequestMapping(value = "/mngr/stats", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Ctl05PromStat extends BaseController
{
    @Autowired
    private SrvImplTrackStats srvTrackStat;

    @GetMapping(value = { "/table" })
    public JsonResult getModules(Integer adid, Integer mid, String beginDate, String endDate)
    {
        return renderSuccess(srvTrackStat.getBasicStatsTable(getUserId(), mid, adid, beginDate, endDate));
    }

    @GetMapping(value = { "/charts" })
    public JsonResult getModules(Integer adid, Integer mid, String quota, String beginDate, String endDate)
    {
        DtoBasicStatsChart data = srvTrackStat.getBasicStatsCharts(getUserId(), mid, adid, quota, beginDate, endDate);
        return renderSuccess(data);
    }
}
