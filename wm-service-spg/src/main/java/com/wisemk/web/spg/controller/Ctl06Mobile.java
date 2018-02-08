package com.wisemk.web.spg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wisemk.web.spg.dto.DtoArticle;
import com.wisemk.web.spg.dto.DtoArticleCat;
import com.wisemk.web.spg.dto.DtoArticleDetail;
import com.wisemk.web.spg.dto.DtoMbClkAssets;
import com.wisemk.web.spg.dto.DtoMbUrlSign;
import com.wisemk.web.spg.service.SrvImplMobile;
import com.wisemk.web.spg.service.SrvImplPromCode;
import com.wisemk.web.spg.service.SrvImplTrackStats;
import com.wisemk.web.spg.share.JsonResult;

import cn.zyy.oss.share.OssLog;

@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Ctl06Mobile extends BaseController
{
    private static final OssLog log = new OssLog();

    @Autowired
    private SrvImplMobile       srvMobile;

    @Autowired
    private SrvImplPromCode     srvPromCode;

    @Autowired
    private SrvImplTrackStats   trackStatsService;

    /* 根据推广码, 获取当前对应的模版 */
    @GetMapping(value = { "/template" })
    public JsonResult getAdTemplate(String adid)
    {
        String templateUrl = srvMobile.getTemplateUrl(adid);
        if (null == templateUrl)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "get template-url fail for prom-code=" + adid);
        }
        else
        {
            JSONObject jsonResult = new JSONObject();
            jsonResult.put("templateUrl", templateUrl);
            return renderSuccess(jsonResult);
        }
    }

    /**************************************移动端文章相关接口**************************************/
    @GetMapping(value = { "/categories" })
    public JsonResult getArticleCat()
    {
        List<DtoArticleCat> lstDtoArticleCat = srvMobile.getMbArticleCatList();
        if (null == lstDtoArticleCat)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "categories info null");
        }
        else
        {
            return renderSuccess(lstDtoArticleCat);
        }
    }

    @GetMapping(value = { "/categories/{id}" })
    public JsonResult getArticleList(@PathVariable(value = "id") int catId)
    {
        List<DtoArticle> lstDtoArticle = srvMobile.getMbArticleList(catId, 0);
        if (null == lstDtoArticle)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "categories " + catId + " article-info null");
        }
        else
        {
            JsonResult jsonResult = renderSuccess(lstDtoArticle);

            log.info("req /categories/" + catId + " return " + lstDtoArticle.size() + " articles\n" + jsonResult.getCode());

            return jsonResult;
        }
    }

    @PostMapping(value = "/regarticles")
    public JsonResult postRegisterArticle(@RequestParam("adid") String adidStr, String article)
    {
        DtoArticleDetail dtoArticleDetail = srvMobile.registerMbUserSelfArticle(article);
        if (null == dtoArticleDetail)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "register user-self article fail");
        }
        else
        {
            return renderSuccess(dtoArticleDetail);
        }
    }

    @PostMapping(value = "/articles")
    public JsonResult postShareArticle(@RequestParam("adid") String adidStr, String article)
    {
        DtoArticleDetail dtoArticleDetail = srvMobile.shareMbArticle(article);
        if (null == dtoArticleDetail)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "share article fail");
        }
        else
        {
            return renderSuccess(dtoArticleDetail);
        }
    }

    @GetMapping(value = { "/articles/{id}" })
    public JsonResult getArticleDesc(@PathVariable(value = "id") int shareArticleId)
    {
        DtoArticleDetail dtoArticleDetail = srvMobile.getMbShareArticleDetail(shareArticleId);
        if (null == dtoArticleDetail)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "article detail info null");
        }
        else
        {
            return renderSuccess(dtoArticleDetail);
        }
    }

    @GetMapping(value = "/adcode/check")
    public JsonResult checkId(@RequestParam("adid") String adidStr)
    {
        return renderSuccess();
    }

    @GetMapping(value = { "/adcode/mertial" })
    public JsonResult getMertial(String adid, String ep)
    {
        JSONObject jsonCreative = srvMobile.getMbCreativeDetail(adid, ep);
        if (null == jsonCreative)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "prom-code[" + adid + "]'s creative null");
        }
        else
        {
            return renderSuccess(jsonCreative);
        }
    }

    @GetMapping(value = { "/adcode/landing" })
    public JsonResult getLandingAsset(String adid)
    {
        JSONObject jsonCreative = srvMobile.getMbLandingAsset(adid);
        if (null == jsonCreative)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "prom-code[" + adid + "]'s creative null");
        }
        else
        {
            return renderSuccess(jsonCreative);
        }
    }

    /* 文章统计 */
    @GetMapping(value = { "/adcode/articlenum" })
    public JsonResult getArticleNum(@RequestParam("adid") String adidStr)
    {
        return renderSuccess(trackStatsService.getArticleNum4PromCode(adidStr));
    }

    @GetMapping(value = { "/adcode/articles" })
    public JsonResult getArticles(@RequestParam("adid") String adidStr)
    {
        return renderSuccess(trackStatsService.getArticleStat4PromCode(adidStr));
    }

    /* 展示统计 */
    @GetMapping(value = { "/adcode/track/pv" })
    public JsonResult getPV(@RequestParam("adid") String adidStr)
    {
        return renderSuccess(trackStatsService.getArticlePv4PromCode(adidStr));
    }

    @GetMapping(value = { "/adcode/track/detail" })
    public JsonResult getViewRecords(@RequestParam("adid") String adidStr, @PageableDefault(size = 20, page = 0) Pageable pageable)
    {
        Integer adid = srvPromCode.getPromCodeId(adidStr);
        if (null == adid)
            throw new RuntimeException("ad code not found! id = " + adidStr);

        int pageNum = pageable.getPageNumber() < 1 ? 0 : pageable.getPageNumber() - 1;
        int pageSize = pageable.getPageSize() > 0 ? pageable.getPageSize() : 20;
        PageRequest page = new PageRequest(pageNum, pageSize, pageable.getSort());

        return renderSuccess(trackStatsService.getViewDetailByAdCode(adid, page));
    }

    @GetMapping(value = { "/sign/article" })
    public JsonResult getArticleSign(@RequestParam("url") String shareUrl)
    {
        DtoMbUrlSign dtoUrlSign = srvMobile.getMbUrlSign(shareUrl);
        if (null == dtoUrlSign)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "get sign error for: " + shareUrl);
        }
        else
        {
            return renderSuccess(dtoUrlSign);
        }
    }

    /********************************监测******************************/
    private static class TrackInfo
    {
        public Integer adid;
        public Integer artid;
        public String  ip;
        public String  country;
        public String  province;
        public String  city;
        public String  client;

        @Override
        public String toString()
        {
            return "{" + "adid=" + adid + ", artid=" + artid + ", ip='" + ip + '\'' + ", country='" + country + '\'' + ", province='" + province + '\'' + ", city='" + city + '\'' + ", client='" + client + '\'' + '}';
        }
    }

    @GetMapping(value = { "/track/impression" })
    public JsonResult impression(String artid, String adid, String country, String province, String city, String client)
    {
        TrackInfo trackInfo = trackInfoBuild(artid, adid, country, province, city, client);
        log.info("impress: " + trackInfo);

        // 展示信息统计
        trackRecord(trackInfo, false);
        return renderSuccess();
    }

    @GetMapping(value = { "/track/click" })
    public JsonResult click(String artid, String adid, String country, String province, String city, String client)
    {
        TrackInfo trackInfo = trackInfoBuild(artid, adid, country, province, city, client);
        log.info("click: " + trackInfo);

        // 点击信息统计
        trackRecord(trackInfo, true);
        return renderSuccess();
    }

    private void trackRecord(TrackInfo trackInfo, boolean isClick)
    {
        trackStatsService.saveTrackDetail(trackInfo.adid, trackInfo.artid, isClick, trackInfo.ip, trackInfo.client, trackInfo.country, trackInfo.province, trackInfo.city);
        trackStatsService.increaseTrack(trackInfo.adid, trackInfo.artid, isClick);
    }

    private TrackInfo trackInfoBuild(String artid, String adid, String country, String province, String city, String client)
    {
        TrackInfo trackInfo = new TrackInfo();
        trackInfo.adid = srvPromCode.getPromCodeId(adid);
        trackInfo.artid = Integer.parseInt(artid);

        trackInfo.ip = getRequest().getHeader("X-Forwarded-For");
        if (null == trackInfo.ip)
        {
            trackInfo.ip = getRequest().getHeader("x-forwarded-for");
        }

        trackInfo.country = country;
        trackInfo.province = province;
        trackInfo.city = city;
        trackInfo.client = client;

        return trackInfo;
    }

    @GetMapping(value = { "/adcode/clkasset" })
    public JsonResult getClkAsset(String adid)
    {
        DtoMbClkAssets dtoClkAsset = srvMobile.getMbClkAsset(adid);
        if (null == dtoClkAsset)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "prom-code[" + adid + "]'s clk-asset null");
        }
        else
        {
            return renderSuccess(dtoClkAsset);
        }
    }
}
