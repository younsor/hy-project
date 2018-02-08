package cn.zyy.zhichuan.webpd.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;
import cn.zyy.zhichuan.webpd.dto.DtoPromCreativeDetail;
import cn.zyy.zhichuan.webpd.service.SrvImplPromCreative;
import cn.zyy.zhichuan.webpd.share.CommonPage;
import cn.zyy.zhichuan.webpd.share.JsonResult;

@RestController
@RequestMapping(value = "/mngr/material", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Ctl04PromCreative extends BaseController
{
    private static final OssLog log = new OssLog();

    @Autowired
    private SrvImplPromCreative srvPromCreative;

    @GetMapping(value = { "", "/" })
    public CommonPage getPromCreativeList(Optional<String> enabled, Optional<String> key, @PageableDefault(size = 20, page = 0, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable)
    {
        int pageNum = pageable.getPageNumber() < 1 ? 0 : pageable.getPageNumber() - 1;
        int pageSize = pageable.getPageSize() > 0 ? pageable.getPageSize() : 20;
        PageRequest page = new PageRequest(pageNum, pageSize, pageable.getSort());

        String isActive = enabled.isPresent() ? enabled.get() : null;
        String matchUserName = key.isPresent() ? key.get() : null;
        return srvPromCreative.getPromCreativeList(getUserId(), isActive, matchUserName, page);
    }

    @GetMapping(value = { "/pulldown" })
    public JsonResult getMaterialPulldown()
    {
        return renderSuccess(srvPromCreative.getPromCreativePullDown(getUserId()));
    }

    /* 查询创意详情 */
    @GetMapping(value = { "/{id}" })
    public JsonResult getPromCreativeDetail(@PathVariable(value = "id") int promCreativeId)
    {
        DtoPromCreativeDetail promCreativeDetail = srvPromCreative.getPromCreativeDetail(promCreativeId);
        if (null == promCreativeDetail)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "no detail info for id=" + promCreativeId);
        }
        else
        {
            return renderSuccess(promCreativeDetail);
        }
    }

    /* 新建推广创意 */
    @PostMapping(value = { "", "/" })
    public JsonResult createPromCreative(String name, int promotionTypeId, String clkLayoutId, String impAsset, String clkAsset)
    {
        DtoPromCreativeDetail dtoPromCreativeDetail = new DtoPromCreativeDetail();
        dtoPromCreativeDetail.id = 0;
        dtoPromCreativeDetail.name = name;
        dtoPromCreativeDetail.promotionTypeId = promotionTypeId;
        dtoPromCreativeDetail.clkLayoutId = OssFunc.DataConvert.toInt(clkLayoutId, 0);

        if (null != impAsset && impAsset.trim().length() > 4)
        {
            dtoPromCreativeDetail.impAssets = Lists.newArrayList();
            JSONArray jsonA = JSONArray.parseArray(impAsset);

            for (int i = 0; i < jsonA.size(); i++)
            {
                JSONObject jsonAsset = jsonA.getJSONObject(i);
                DtoPromCreativeDetail.Asset dtoAsset = new DtoPromCreativeDetail.Asset();

                dtoAsset.elemId = jsonAsset.getIntValue("elemId");
                dtoAsset.content = jsonAsset.getString("content");
                dtoAsset.description = jsonAsset.getString("description");

                dtoPromCreativeDetail.impAssets.add(dtoAsset);
            }
        }
        
        if (null != clkAsset && clkAsset.trim().length() > 4)
        {
            dtoPromCreativeDetail.clkAssets = Lists.newArrayList();
            JSONArray jsonA = JSONArray.parseArray(clkAsset);

            for (int i = 0; i < jsonA.size(); i++)
            {
                JSONObject jsonAsset = jsonA.getJSONObject(i);
                DtoPromCreativeDetail.Asset dtoAsset = new DtoPromCreativeDetail.Asset();

                dtoAsset.elemId = jsonAsset.getIntValue("elemId");
                dtoAsset.content = jsonAsset.getString("content");
                dtoAsset.description = jsonAsset.getString("description");

                dtoPromCreativeDetail.clkAssets.add(dtoAsset);
            }
        }

        int promCreativeId = srvPromCreative.createPromCreative(getUserId(), dtoPromCreativeDetail);

        JSONObject rtnValue = new JSONObject();
        rtnValue.put("id", promCreativeId);
        return renderSuccess(rtnValue);
    }

    /* 新建推广创意 */
    @PostMapping(value = { "/{id}" })
    public JsonResult updatePromCreative(@PathVariable(value = "id") int promCreativeId, String name, int promotionTypeId, int clkLayoutId, String impAsset, String clkAsset)
    {
        DtoPromCreativeDetail dtoPromCreativeDetail = new DtoPromCreativeDetail();
        dtoPromCreativeDetail.id = promCreativeId;
        dtoPromCreativeDetail.name = name;
        dtoPromCreativeDetail.promotionTypeId = promotionTypeId;
        dtoPromCreativeDetail.clkLayoutId = clkLayoutId;

        if (null != impAsset)
        {
            dtoPromCreativeDetail.impAssets = Lists.newArrayList();
            JSONArray jsonA = JSONArray.parseArray(impAsset);

            for (int i = 0; i < jsonA.size(); i++)
            {
                JSONObject jsonAsset = jsonA.getJSONObject(i);
                DtoPromCreativeDetail.Asset dtoAsset = new DtoPromCreativeDetail.Asset();

                dtoAsset.elemId = jsonAsset.getIntValue("elemId");
                dtoAsset.content = jsonAsset.getString("content");
                dtoAsset.description = jsonAsset.getString("description");

                dtoPromCreativeDetail.impAssets.add(dtoAsset);
            }
        }

        if (null != clkAsset)
        {
            dtoPromCreativeDetail.clkAssets = Lists.newArrayList();
            JSONArray jsonA = JSONArray.parseArray(clkAsset);

            for (int i = 0; i < jsonA.size(); i++)
            {
                JSONObject jsonAsset = jsonA.getJSONObject(i);
                DtoPromCreativeDetail.Asset dtoAsset = new DtoPromCreativeDetail.Asset();

                dtoAsset.elemId = jsonAsset.getIntValue("elemId");
                dtoAsset.content = jsonAsset.getString("content");
                dtoAsset.description = jsonAsset.getString("description");

                dtoPromCreativeDetail.clkAssets.add(dtoAsset);
            }
        }

        srvPromCreative.updatePromCreative(getUserId(), dtoPromCreativeDetail);
        return renderSuccess();
    }

    @DeleteMapping(value = { "/{id}" })
    public JsonResult deletePromCreative(@PathVariable(value = "id") int promCreativeId)
    {
        if (srvPromCreative.deletePromCreative(getUserId(), promCreativeId))
        {
            return renderSuccess();
        }
        else
        {
            return renderError(HttpStatus.UNAUTHORIZED, "delete fail for prom-creative-id " + promCreativeId);
        }
    }
}
