package com.wisemk.web.spg.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wisemk.web.spg.domain.ArticleCat;
import com.wisemk.web.spg.domain.ArticleShare;
import com.wisemk.web.spg.domain.PromClkAsset;
import com.wisemk.web.spg.domain.PromCode;
import com.wisemk.web.spg.domain.PromCreative;
import com.wisemk.web.spg.domain.PromImpAsset;
import com.wisemk.web.spg.domain.PromType;
import com.wisemk.web.spg.domain.ShareAccout;
import com.wisemk.web.spg.dto.DtoArticle;
import com.wisemk.web.spg.dto.DtoArticleCat;
import com.wisemk.web.spg.dto.DtoArticleDetail;
import com.wisemk.web.spg.dto.DtoMbClkAssets;
import com.wisemk.web.spg.dto.DtoMbUrlSign;
import com.wisemk.web.spg.repo.RepoArticleCat;
import com.wisemk.web.spg.repo.RepoArticleShare;
import com.wisemk.web.spg.repo.RepoPromClkAsset;
import com.wisemk.web.spg.repo.RepoPromCode;
import com.wisemk.web.spg.repo.RepoPromCreative;
import com.wisemk.web.spg.repo.RepoPromImpAsset;
import com.wisemk.web.spg.repo.RepoPromType;
import com.wisemk.web.spg.repo.RepoShareAccout;
import com.wisemk.web.spg.share.Constants;
import com.wisemk.web.spg.share.MArticleContent;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;

@Service
public class SrvImplMobile
{
    private static final OssLog log = new OssLog();

    @Autowired
    private RepoArticleCat      repoArticleCat;

    @Autowired
    private RepoArticleShare    repoArticleShare;

    @Autowired
    private RepoPromCode        repoPromCode;

    @Autowired
    private RepoPromCreative    repoCreative;

    @Autowired
    private RepoPromImpAsset    repoImpAsset;

    @Autowired
    private RepoPromClkAsset    repoClkAsset;

    @Autowired
    private RepoShareAccout     repoShareAccout;

    @Autowired
    private RepoPromType        repoPromType;

    public String getTemplateUrl(String promCodeShowId)
    {
        PromCode promCode = repoPromCode.findByShowId(promCodeShowId);
        if (null == promCode)
        {
            log.error("no prom code info for show-id=" + promCodeShowId);
            return null;
        }

        int promCreativeId = promCode.getPromCreativeId();
        PromCreative promCreative = repoCreative.findById(promCreativeId);
        if (null == promCreative)
        {
            log.error("no prom creative info for creative-id=" + promCreativeId);
            return null;
        }

        int promTypeId = promCreative.getPromTypeId();
        PromType promType = repoPromType.getOne(promTypeId);
        if (null == promType)
        {
            log.error("no prom type info for promTypeId=" + promTypeId + ", when creative-id=" + promCreativeId);
            return null;
        }

        return promType.getTemplateUrl();
    }

    /* 获取文章分类 */
    public List<DtoArticleCat> getMbArticleCatList()
    {
        List<ArticleCat> lstArticleCat = repoArticleCat.findByIsDel(0);
        if (null == lstArticleCat)
        {
            return null;
        }

        List<DtoArticleCat> lstDtoArticleCat = Lists.newArrayList();
        for (ArticleCat articleCat : lstArticleCat)
        {
            DtoArticleCat dtoArticleCat = DtoArticleCat.of(articleCat);
            lstDtoArticleCat.add(dtoArticleCat);
        }

        return lstDtoArticleCat;
    }

    /* 获取某一个文章分类下的文章列表信息 */
    public List<DtoArticle> getMbArticleList(int articleCatId, int step)
    {
        List<ArticleShare> lstShareArticle = repoArticleShare.findByArticleCatIdOrderByAppearTimeDesc(articleCatId);
        if (null == lstShareArticle)
        {
            return null;
        }
        log.info("req /categories/" + articleCatId + " return " + lstShareArticle.size() + " articles");

        List<DtoArticle> lstDtoArticle = Lists.newArrayList();
        for (ArticleShare shareArticle : lstShareArticle)
        {
            lstDtoArticle.add(new DtoArticle(shareArticle));
        }

        return lstDtoArticle;
    }

    /* 注册用户私有文章 */
    public DtoArticleDetail registerMbUserSelfArticle(String originArticleUrl)
    {
        /* 如果是我们自己的文章, 则直接返回 */
        ArticleShare shareArticle = repoArticleShare.findByUrl(originArticleUrl);
        if (null != shareArticle)
        {
            return new DtoArticleDetail(shareArticle);
        }

        shareArticle = repoArticleShare.findByOriginUrl(originArticleUrl);
        if (null != shareArticle)
        {
            /* 已经有了文章,  */
            return new DtoArticleDetail(shareArticle);
        }

        /* 没有文章的话, 则注册 */
        MArticleContent articleContent = new MArticleContent(originArticleUrl);
        int iRet = articleContent.handlerContent();
        if (Constants.RET_OK != iRet)
        {
            /* 文章处理失败 */
            log.error("");
            return null;
        }

        shareArticle = new ArticleShare();
        shareArticle.setArticleCollectId(0);
        shareArticle.setArticleCatId(0);
        shareArticle.setUrl("");
        shareArticle.setTitle(articleContent.getTitle());
        shareArticle.setDescription(articleContent.getDesc());
        shareArticle.setIconUrl(articleContent.getIcon());

        // shareArticle.setAppearTime(OssFunc.TimeConvert.Date2Format(new Date(), OssFunc.TimeConvert.DF_SECOND));
        shareArticle.setAppearTime(OssFunc.TimeConvert.Date2Format(new Date(), OssFunc.TimeConvert.DF_SECOND));

        shareArticle.setStar(0);
        shareArticle.setState(1);
        shareArticle.setOriginUrl("");
        shareArticle.setIsDel(0);

        shareArticle = repoArticleShare.save(shareArticle);
        String fileName = "self" + shareArticle.getId() + ".html";

        /* 上传到CDN */
        String strAccessPath = null;
        try
        {
            strAccessPath = articleContent.uploadFile2Cdn(fileName);

            log.info("upload to cdn success: " + fileName);
        }
        catch (Exception e1)
        {
            log.error("upload " + fileName + " to cdn exception: \n" + OssFunc.getExceptionInfo(e1));
            try
            {
                log.info("upload again to cdn.");
                strAccessPath = articleContent.uploadFile2Cdn(fileName);

                log.info("upload again to cdn success: " + fileName);
            }
            catch (Exception e2)
            {
                strAccessPath = null;
                log.error("upload again to cdn exception.");
            }
        }

        if (null == strAccessPath)
        {
            /* 上传cdn错误, 则删除刚刚的记录 */
            repoArticleShare.delete(shareArticle);
            return null;
        }

        shareArticle.setUrl(strAccessPath);
        shareArticle.setOriginUrl(originArticleUrl);

        shareArticle = repoArticleShare.save(shareArticle);

        return new DtoArticleDetail(shareArticle);
    }

    /* 分享文章 */
    public DtoArticleDetail shareMbArticle(String shareArticleUrl)
    {
        ArticleShare shareArticle = repoArticleShare.findByUrl(shareArticleUrl);
        if (null != shareArticle)
        {
            /* 已经有了文章,  */
            return new DtoArticleDetail(shareArticle);
        }

        return null;
    }

    /* 获取分享文章的详细信息 */
    public DtoArticleDetail getMbShareArticleDetail(int shareArticleId)
    {
        ArticleShare shareArticle = repoArticleShare.findById(shareArticleId);
        if (null != shareArticle)
        {
            /* 已经有了文章 */
            return new DtoArticleDetail(shareArticle);
        }

        return null;
    }

    /* 获取广告码当前的素材信息 */
    public JSONObject getMbCreativeDetail(String promCodeShowId, String ep)
    {
        PromCode promCode = repoPromCode.findByShowId(promCodeShowId);
        if (null == promCode)
        {
            return null;
        }

        if (promCode.getPromCreativeId() <= 0)
        {
            log.error("promcode[" + promCodeShowId + "] has no valid prom creative");
            return null;
        }

        PromCreative promCreative = repoCreative.findById(promCode.getPromCreativeId());
        if (null == promCreative)
        {
            log.error("promcode[" + promCodeShowId + "]'s promCreativeId[" + promCode.getPromCreativeId() + "] has no valid prom creative");
        }

        int promCreativeId = promCreative.getId();
        JSONObject jsonCreative = new JSONObject();
        jsonCreative.put("id", promCreativeId);

        /* 展示素材 */
        List<PromImpAsset> lstImpAsset = repoImpAsset.findByPromCreativeId(promCreativeId);
        if (null != lstImpAsset)
        {
            for (PromImpAsset impAsset : lstImpAsset)
            {
                jsonCreative.put(impAsset.getKeyName(), impAsset.getCdnPath());
            }
        }
        else
        {
            log.error("no prom-imp-asset for prom-creative-id: " + promCreativeId);
            return null;
        }

        /* 点击素材, 判断点击样式id值: 
         * -如果为1, 则返回所有创意素材明细
         * -如果为2, 则返回"/landing_egg"
         * */
        if (2 != promCreative.getPromClkLayoutId())
        {
            List<PromClkAsset> lstClkAsset = repoClkAsset.findByPromCreativeId(promCreativeId);
            if (null != lstClkAsset)
            {
                for (PromClkAsset clkAsset : lstClkAsset)
                {
                    jsonCreative.put(clkAsset.getKeyName(), clkAsset.getCdnPath());
                }
            }
            else
            {
                log.error("no prom-clk-asset for prom-creative-id: " + promCreativeId);
            }
        }
        else
        {
            jsonCreative.put("landing_url", ep + "/landing_egg?adid=" + promCodeShowId);
        }

        return jsonCreative;
    }

    /* 获取广告码当前的素材信息 */
    public JSONObject getMbLandingAsset(String promCodeShowId)
    {
        PromCode promCode = repoPromCode.findByShowId(promCodeShowId);
        if (null == promCode)
        {
            return null;
        }

        if (promCode.getPromCreativeId() <= 0)
        {
            log.error("promcode[" + promCodeShowId + "] has no valid prom creative");
            return null;
        }

        PromCreative promCreative = repoCreative.findById(promCode.getPromCreativeId());
        if (null == promCreative)
        {
            log.error("promcode[" + promCodeShowId + "]'s promCreativeId[" + promCode.getPromCreativeId() + "] has no valid prom creative");
        }

        int promCreativeId = promCreative.getId();
        JSONObject jsonCreative = new JSONObject();
        jsonCreative.put("id", promCreativeId);

        /* 点击素材, 判断点击样式id值: 
         * -如果为1, 则返回所有创意素材明细
         * -如果为2, 则返回"/landing_egg"
         * */
        List<PromClkAsset> lstClkAsset = repoClkAsset.findByPromCreativeId(promCreativeId);
        if (null != lstClkAsset)
        {
            for (PromClkAsset clkAsset : lstClkAsset)
            {
                jsonCreative.put(clkAsset.getKeyName(), clkAsset.getCdnPath());
            }
        }
        else
        {
            log.error("no prom-clk-asset for prom-creative-id: " + promCreativeId);
        }

        return jsonCreative;
    }

    /* 获取分享文章签名接口 */
    public DtoMbUrlSign getMbUrlSign(String url)
    {
        List<ShareAccout> lstShareAccout = repoShareAccout.findByState(1);
        if (null == lstShareAccout || lstShareAccout.size() <= 0)
        {
            log.error("no valid share-accout can use!!!");
            return null;
        }
        ShareAccout useShareAccout = lstShareAccout.get(0);
        String timeStamp = System.currentTimeMillis() / 1000 + "";

        /* 生成签名1: 组装签名字段 */
        Map<String, String> mapSignFields = new TreeMap<String, String>();
        mapSignFields.put("noncestr", useShareAccout.getRandomStr());
        mapSignFields.put("jsapi_ticket", useShareAccout.getTicket());
        mapSignFields.put("timestamp", timeStamp);
        mapSignFields.put("url", url);

        StringBuffer originSignInfo = new StringBuffer();
        for (String tmpKey : mapSignFields.keySet())
        {
            if (originSignInfo.length() > 0)
            {
                originSignInfo.append("&");
            }
            originSignInfo.append(tmpKey + "=");
            originSignInfo.append(mapSignFields.get(tmpKey));
        }

        String urlSign = OssFunc.SHA1(originSignInfo.toString());
        if (null == urlSign)
        {
            log.error("calcu url[" + url + "]'s sign error");
            return null;
        }

        /* 更新 */
        DtoMbUrlSign dtoUrlSign = new DtoMbUrlSign();
        dtoUrlSign.setAppId(useShareAccout.getAccout());
        dtoUrlSign.setNoncestr(useShareAccout.getRandomStr());
        dtoUrlSign.setTs(timeStamp);
        dtoUrlSign.setSign(urlSign);

        return dtoUrlSign;
    }

    public DtoMbClkAssets getMbClkAsset(String promCodeShowId)
    {
        DtoMbClkAssets dtoClkAsset = new DtoMbClkAssets();
        dtoClkAsset.smashEggTimes = 2;
        dtoClkAsset.eggAnimationNum = 2;
        dtoClkAsset.lstPrizeItem = Lists.newArrayList();

        DtoMbClkAssets.PrizeItem prizeItem = new DtoMbClkAssets.PrizeItem();
        prizeItem.image = "http://cdn.apilnk.com/zhichuan-test/zhangyutest/zm/wenzongkehu.png";
        prizeItem.desc = "奖品1-百度";
        prizeItem.expiryDate = "2018-01-29";
        prizeItem.landingUrl = "http://www.baidu.com";
        dtoClkAsset.lstPrizeItem.add(prizeItem);

        prizeItem = new DtoMbClkAssets.PrizeItem();
        prizeItem.image = "http://cdn.apilnk.com/zhichuan-test/zhangyutest/zm/zm-pic.png";
        prizeItem.desc = "奖品2-新浪";
        prizeItem.expiryDate = "2018-01-30";
        prizeItem.landingUrl = "http://www.sina.com.cn";
        dtoClkAsset.lstPrizeItem.add(prizeItem);

        return dtoClkAsset;
    }
}
