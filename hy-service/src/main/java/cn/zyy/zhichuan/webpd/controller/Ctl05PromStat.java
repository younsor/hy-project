package cn.zyy.zhichuan.webpd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zyy.zhichuan.webpd.dto.DtoBasicStatsChart;
import cn.zyy.zhichuan.webpd.service.SrvImplTrackStats;
import cn.zyy.zhichuan.webpd.share.JsonResult;

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
