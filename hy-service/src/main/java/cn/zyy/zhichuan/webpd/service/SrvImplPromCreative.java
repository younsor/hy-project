package cn.zyy.zhichuan.webpd.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;
import cn.zyy.zhichuan.webpd.domain.PromClkAsset;
import cn.zyy.zhichuan.webpd.domain.PromClkLayoutElem;
import cn.zyy.zhichuan.webpd.domain.PromCreative;
import cn.zyy.zhichuan.webpd.domain.PromImpAsset;
import cn.zyy.zhichuan.webpd.domain.PromImpLayoutElem;
import cn.zyy.zhichuan.webpd.domain.PromType;
import cn.zyy.zhichuan.webpd.dto.DtoPromCreative;
import cn.zyy.zhichuan.webpd.dto.DtoPromCreativeDetail;
import cn.zyy.zhichuan.webpd.dto.DtoPromLayoutClk;
import cn.zyy.zhichuan.webpd.dto.DtoPromLayoutImp;
import cn.zyy.zhichuan.webpd.dto.DtoPullDown;
import cn.zyy.zhichuan.webpd.repo.RepoPromClkAsset;
import cn.zyy.zhichuan.webpd.repo.RepoPromClkLayout;
import cn.zyy.zhichuan.webpd.repo.RepoPromCreative;
import cn.zyy.zhichuan.webpd.repo.RepoPromImpAsset;
import cn.zyy.zhichuan.webpd.repo.RepoPromImpLayout;
import cn.zyy.zhichuan.webpd.repo.RepoPromType;
import cn.zyy.zhichuan.webpd.share.CommonPage;

@Service
public class SrvImplPromCreative
{
    private static final OssLog log = new OssLog();

    @Autowired
    private RepoPromType        repoPromType;

    @Autowired
    private RepoPromImpLayout   repoImpLayout;

    @Autowired
    private RepoPromClkLayout   repoClkLayout;

    @Autowired
    private RepoPromCreative    repoPromCreative;

    @Autowired
    private RepoPromImpAsset    repoImpAsset;

    @Autowired
    private RepoPromClkAsset    repoClkAsset;

    /* 推广类型下拉列表 */
    public List<DtoPullDown> getPromTypePullDown()
    {
        List<DtoPullDown> lstDtoPullDown = Lists.newArrayList();

        List<Object[]> lstPromCode = repoPromType.findPromTypePullDown();
        if (null == lstPromCode || lstPromCode.isEmpty())
        {
            return lstDtoPullDown;
        }

        for (Object[] aPromCodeInfo : lstPromCode)
        {
            int tmpValue = OssFunc.DataConvert.toInt(aPromCodeInfo[0], 0);
            String tmpText = OssFunc.DataConvert.toStr(aPromCodeInfo[1], "");
            lstDtoPullDown.add(new DtoPullDown(tmpValue, tmpText));
        }

        return lstDtoPullDown;
    }

    /* 推广展示样式详情 */
    public DtoPromLayoutImp getPromImpLayout(int promImpLayoutId)
    {
        /* 获取样式名称信息 */
        List<Object[]> impLayoutInfo = repoImpLayout.getPromImpLayout(promImpLayoutId);
        if (null == impLayoutInfo || impLayoutInfo.size() <= 0)
        {
            log.error("cannot find prom-imp-layout info for promImpLayoutId=" + promImpLayoutId);
            return null;
        }

        DtoPromLayoutImp dtoPromLayoutImp = new DtoPromLayoutImp();
        dtoPromLayoutImp.id = promImpLayoutId;
        dtoPromLayoutImp.name = OssFunc.DataConvert.toStr(impLayoutInfo.get(0)[0], "");
        dtoPromLayoutImp.impLayout = Lists.newArrayList();

        /* 获取样式元素信息 */
        List<PromImpLayoutElem> lstElem = repoImpLayout.findByPromImpLayoutIdOrderById(promImpLayoutId);
        if (null != lstElem)
        {
            for (PromImpLayoutElem tmpElem : lstElem)
            {
                DtoPromLayoutImp.LayoutElem impLayoutElem = new DtoPromLayoutImp.LayoutElem();
                impLayoutElem.elemId = tmpElem.getId();
                impLayoutElem.name = tmpElem.getName();

                int attrType = tmpElem.getPromLayoutElemAttrId();
                if (1 == attrType)
                {
                    impLayoutElem.type = "image";
                }
                else if (2 == attrType)
                {
                    impLayoutElem.type = "text";
                }
                else if (3 == attrType)
                {
                    impLayoutElem.type = "video";
                }
                else
                {
                    impLayoutElem.type = "";
                }

                impLayoutElem.elemKbytes = tmpElem.getLimitKbytes();
                impLayoutElem.elemMime = tmpElem.getLimitMime();
                impLayoutElem.elemRegex = tmpElem.getRegex();
                impLayoutElem.regexMismatchTip = tmpElem.getRegexMismatchTip();
                impLayoutElem.sizeMinLen = tmpElem.getSizeMinLen();
                impLayoutElem.sizeMaxLen = tmpElem.getSizeMaxLen();
                impLayoutElem.sizeW = tmpElem.getSizeW();
                impLayoutElem.sizeH = tmpElem.getSizeH();
                impLayoutElem.description = tmpElem.getDescription();
                impLayoutElem.elemRequired = (tmpElem.getIsEssential() != 0);
                impLayoutElem.elemMatch = (tmpElem.getIsMatch() != 0);

                dtoPromLayoutImp.impLayout.add(impLayoutElem);
            }
        }

        return dtoPromLayoutImp;
    }

    /* 推广类型下拉列表 */
    public List<DtoPullDown> getPromClkLayoutPullDown(int promTypeId)
    {
        PromType promType = repoPromType.findById(promTypeId);
        String strValues = promType.getPromClkLayoutIds();
        if (strValues.length() <= 0)
        {
            return null;
        }

        String[] arrayValues = strValues.split(",");
        if (null == arrayValues || arrayValues.length <= 0)
        {
            return null;
        }

        List<DtoPullDown> lstPullDown = Lists.newArrayList();
        for (String tmpValue : arrayValues)
        {
            Integer iValue = OssFunc.DataConvert.toInt(tmpValue.trim(), 0);
            if (0 == iValue)
            {
                continue;
            }

            /* 获取点击样式名称 */
            List<Object[]> lstClkLayoutInfo = repoClkLayout.getPromClkLayout(iValue);
            if (null == lstClkLayoutInfo)
            {
                log.error("no clk layout info for clkLayoutId=" + iValue);
                continue;
            }

            String layoutName = OssFunc.DataConvert.toStr(lstClkLayoutInfo.get(0)[0], "");

            lstPullDown.add(new DtoPullDown(iValue, layoutName));
        }

        return lstPullDown;
    }

    /* 推广点击样式详情 */
    public DtoPromLayoutClk getPromClkLayout(int promClkLayoutId)
    {
        /* 获取样式名称信息 */
        List<Object[]> clkLayoutInfo = repoClkLayout.getPromClkLayout(promClkLayoutId);
        if (null == clkLayoutInfo || clkLayoutInfo.size() <= 0)
        {
            log.error("cannot find prom-clk-layout info for promClkLayoutId=" + promClkLayoutId);
            return null;
        }

        DtoPromLayoutClk dtoPromLayoutClk = new DtoPromLayoutClk();
        dtoPromLayoutClk.id = promClkLayoutId;
        dtoPromLayoutClk.name = OssFunc.DataConvert.toStr(clkLayoutInfo.get(0)[0], "");
        dtoPromLayoutClk.clkLayout = Lists.newArrayList();

        /* 获取样式元素信息 */
        List<PromClkLayoutElem> lstElem = repoClkLayout.findByPromClkLayoutIdOrderById(promClkLayoutId);
        if (null != lstElem)
        {
            for (PromClkLayoutElem tmpElem : lstElem)
            {
                DtoPromLayoutClk.LayoutElem clkLayoutElem = new DtoPromLayoutClk.LayoutElem();
                clkLayoutElem.elemId = tmpElem.getId();
                clkLayoutElem.name = tmpElem.getName();

                int attrType = tmpElem.getPromLayoutElemAttrId();
                if (1 == attrType)
                {
                    clkLayoutElem.type = "image";
                }
                else if (2 == attrType)
                {
                    clkLayoutElem.type = "text";
                }
                else if (3 == attrType)
                {
                    clkLayoutElem.type = "video";
                }
                else
                {
                    clkLayoutElem.type = "";
                }

                clkLayoutElem.elemKbytes = tmpElem.getLimitKbytes();
                clkLayoutElem.elemMime = tmpElem.getLimitMime();
                clkLayoutElem.elemRegex = tmpElem.getRegex();
                clkLayoutElem.regexMismatchTip = tmpElem.getRegexMismatchTip();
                clkLayoutElem.sizeMinLen = tmpElem.getSizeMinLen();
                clkLayoutElem.sizeMaxLen = tmpElem.getSizeMaxLen();
                clkLayoutElem.sizeW = tmpElem.getSizeW();
                clkLayoutElem.sizeH = tmpElem.getSizeH();
                clkLayoutElem.description = tmpElem.getDescription();
                clkLayoutElem.elemRequired = (tmpElem.getIsEssential() != 0);
                clkLayoutElem.elemMatch = (tmpElem.getIsMatch() != 0);

                dtoPromLayoutClk.clkLayout.add(clkLayoutElem);
            }
        }

        return dtoPromLayoutClk;
    }

    public List<DtoPullDown> getPromCreativePullDown(Integer pdUid)
    {
        List<DtoPullDown> lstDtoPullDown = Lists.newArrayList();

        List<Object[]> lstPromCode = repoPromCreative.findPromCreativePullDown(pdUid);
        if (null == lstPromCode || lstPromCode.isEmpty())
        {
            return lstDtoPullDown;
        }

        for (Object[] aPromCodeInfo : lstPromCode)
        {
            int tmpValue = OssFunc.DataConvert.toInt(aPromCodeInfo[0], 0);
            String tmpText = OssFunc.DataConvert.toStr(aPromCodeInfo[1], "");
            lstDtoPullDown.add(new DtoPullDown(tmpValue, tmpText));
        }

        return lstDtoPullDown;
    }

    public CommonPage<DtoPromCreative> getPromCreativeList(Integer pdUid, String isActivate, String key, PageRequest page)
    {
        Page<Object[]> pageRslt = repoPromCreative.findPromCreativeListByPdUserId(pdUid, page);

        List<DtoPromCreative> lstDtoPromCreative = Lists.newArrayList();
        if (null != pageRslt)
        {
            for (Object[] lstField : pageRslt)
            {
                DtoPromCreative dtoPromCreative = new DtoPromCreative();
                dtoPromCreative.id = OssFunc.DataConvert.toInt(lstField[0], 0);
                dtoPromCreative.name = OssFunc.DataConvert.toStr(lstField[1], "");
                dtoPromCreative.promTypeId = OssFunc.DataConvert.toStr(lstField[2], "");
                dtoPromCreative.promTypeName = OssFunc.DataConvert.toStr(lstField[3], "");

                int auditStatus = OssFunc.DataConvert.toInt(lstField[4], 0);
                if (1 == auditStatus)
                {
                    dtoPromCreative.status = "审核通过";
                }
                else if (2 == auditStatus)
                {
                    dtoPromCreative.status = "审核拒绝";
                }
                else
                {
                    dtoPromCreative.status = "待审核";
                }

                String tmpTs = "";
                if (null != lstField[5])
                {
                    tmpTs = OssFunc.TimeConvert.Date2Format((Timestamp) lstField[5], OssFunc.TimeConvert.DF_SECOND);
                }
                dtoPromCreative.ts = tmpTs;

                lstDtoPromCreative.add(dtoPromCreative);
            }
        }

        return CommonPage.build(lstDtoPromCreative, pageRslt);
    }

    public DtoPromCreativeDetail getPromCreativeDetail(int promCreativeId)
    {
        PromCreative promCreative = repoPromCreative.findById(promCreativeId);
        if (null == promCreative)
        {
            log.error("cannot find prom-creative info for promCreativeId=" + promCreativeId);
            return null;
        }

        DtoPromCreativeDetail creativeDetail = new DtoPromCreativeDetail();
        creativeDetail.id = promCreative.getId();
        creativeDetail.name = promCreative.getName();
        creativeDetail.promotionTypeId = promCreative.getPromTypeId();
        creativeDetail.clkLayoutId = promCreative.getPromClkLayoutId();

        creativeDetail.impAssets = Lists.newArrayList();
        List<Object[]> lstImpAssets = repoImpAsset.findDetailByPromCreativeId(promCreativeId);
        if (null != lstImpAssets)
        {
            for (Object[] objAssets : lstImpAssets)
            {
                DtoPromCreativeDetail.Asset oneAsset = new DtoPromCreativeDetail.Asset();
                oneAsset.id = OssFunc.DataConvert.toInt(objAssets[0], 0);
                oneAsset.name = OssFunc.DataConvert.toStr(objAssets[1], "");
                oneAsset.elemId = OssFunc.DataConvert.toInt(objAssets[2], 0);

                int attrType = OssFunc.DataConvert.toInt(objAssets[3], 0);
                if (1 == attrType)
                {
                    oneAsset.type = "image";
                }
                else if (2 == attrType)
                {
                    oneAsset.type = "text";
                }
                else if (3 == attrType)
                {
                    oneAsset.type = "video";
                }
                else
                {
                    oneAsset.type = "";
                }

                oneAsset.content = OssFunc.DataConvert.toStr(objAssets[4], "");
                oneAsset.description = OssFunc.DataConvert.toStr(objAssets[5], "");

                creativeDetail.impAssets.add(oneAsset);
            }
        }

        creativeDetail.clkAssets = Lists.newArrayList();
        List<Object[]> lstClkAssets = repoClkAsset.findDetailByPromCreativeId(promCreativeId);
        if (null != lstClkAssets)
        {
            for (Object[] objAssets : lstClkAssets)
            {
                DtoPromCreativeDetail.Asset oneAsset = new DtoPromCreativeDetail.Asset();
                oneAsset.id = OssFunc.DataConvert.toInt(objAssets[0], 0);
                oneAsset.name = OssFunc.DataConvert.toStr(objAssets[1], "");
                oneAsset.elemId = OssFunc.DataConvert.toInt(objAssets[2], 0);

                int attrType = OssFunc.DataConvert.toInt(objAssets[3], 0);
                if (1 == attrType)
                {
                    oneAsset.type = "image";
                }
                else if (2 == attrType)
                {
                    oneAsset.type = "text";
                }
                else if (3 == attrType)
                {
                    oneAsset.type = "video";
                }
                else
                {
                    oneAsset.type = "";
                }

                oneAsset.content = OssFunc.DataConvert.toStr(objAssets[4], "");
                oneAsset.description = OssFunc.DataConvert.toStr(objAssets[5], "");

                creativeDetail.clkAssets.add(oneAsset);
            }
        }

        return creativeDetail;
    }

    public int createPromCreative(int pdUserId, DtoPromCreativeDetail dtoPromCreative)
    {
        /* 将一个创意结构, 编程3个表结构 */
        PromCreative promCreative = new PromCreative();
        promCreative.setName(dtoPromCreative.getName());
        promCreative.setPdUserId(pdUserId);
        promCreative.setPromTypeId(dtoPromCreative.getPromotionTypeId());
        promCreative.setPromImpLayoutId(dtoPromCreative.getPromotionTypeId());
        promCreative.setPromClkLayoutId(dtoPromCreative.getClkLayoutId());
        promCreative.setAuditStatus(0);
        promCreative.setAuditDesc("待审核");
        promCreative.setIsDel(0);

        promCreative = repoPromCreative.save(promCreative);
        int promCreativeId = promCreative.getId();

        /* 保存展示素材 */
        if (null != dtoPromCreative.impAssets)
        {
            for (DtoPromCreativeDetail.Asset impAsset : dtoPromCreative.impAssets)
            {
                /* 请求中只有elemId, 要根据次获取字段名称、属性 */
                PromImpLayoutElem impLayoutElem = repoImpLayout.findById(impAsset.elemId);

                PromImpAsset onePromImpAsset = new PromImpAsset();
                onePromImpAsset.setKeyName(impLayoutElem.getName());
                onePromImpAsset.setPromImpLayoutElemId(impAsset.elemId);
                onePromImpAsset.setPromCreativeId(promCreativeId);
                onePromImpAsset.setLocalPath(impAsset.content);
                onePromImpAsset.setCdnPath(impAsset.content);
                onePromImpAsset.setIsDel(0);

                repoImpAsset.save(onePromImpAsset);
            }
        }

        /* 保存点击素材 */
        if (null != dtoPromCreative.clkAssets)
        {
            for (DtoPromCreativeDetail.Asset clkAsset : dtoPromCreative.clkAssets)
            {
                /* 请求中只有elemId, 要根据次获取字段名称、属性 */
                PromClkLayoutElem clkLayoutElem = repoClkLayout.findById(clkAsset.elemId);

                PromClkAsset onePromClkAsset = new PromClkAsset();
                onePromClkAsset.setKeyName(clkLayoutElem.getName());
                onePromClkAsset.setPromClkLayoutElemId(clkAsset.elemId);
                onePromClkAsset.setPromCreativeId(promCreativeId);
                onePromClkAsset.setLocalPath(clkAsset.content);
                onePromClkAsset.setCdnPath(clkAsset.content);
                onePromClkAsset.setIsDel(0);

                repoClkAsset.save(onePromClkAsset);
            }
        }

        return promCreativeId;
    }

    public int updatePromCreative(int pdUserId, DtoPromCreativeDetail dtoPromCreative)
    {
        /* 将一个创意结构, 编程3个表结构 */
        PromCreative promCreative = new PromCreative();
        promCreative.setId(dtoPromCreative.id);
        promCreative.setName(dtoPromCreative.getName());
        promCreative.setPdUserId(pdUserId);
        promCreative.setPromTypeId(dtoPromCreative.getPromotionTypeId());
        promCreative.setPromImpLayoutId(dtoPromCreative.getPromotionTypeId());
        promCreative.setPromClkLayoutId(dtoPromCreative.getClkLayoutId());
        promCreative.setAuditStatus(0);
        promCreative.setAuditDesc("待审核");
        promCreative.setIsDel(0);

        promCreative = repoPromCreative.save(promCreative);
        int promCreativeId = promCreative.getId();

        /* 先将之前的展示素材删除, 再保存展示素材 */
        List<PromImpAsset> lstOldImpAsset = repoImpAsset.findByPromCreativeId(promCreativeId);
        if (null != lstOldImpAsset && lstOldImpAsset.size() >= 0)
        {
            repoImpAsset.delete(lstOldImpAsset);
        }

        if (null != dtoPromCreative.impAssets)
        {
            for (DtoPromCreativeDetail.Asset impAsset : dtoPromCreative.impAssets)
            {
                /* 请求中只有elemId, 要根据次获取字段名称、属性 */
                PromImpLayoutElem impLayoutElem = repoImpLayout.findById(impAsset.elemId);

                PromImpAsset onePromImpAsset = new PromImpAsset();
                onePromImpAsset.setKeyName(impLayoutElem.getName());
                onePromImpAsset.setPromImpLayoutElemId(impAsset.elemId);
                onePromImpAsset.setPromCreativeId(promCreativeId);
                onePromImpAsset.setLocalPath(impAsset.content);
                onePromImpAsset.setCdnPath(impAsset.content);
                onePromImpAsset.setIsDel(0);

                repoImpAsset.save(onePromImpAsset);
            }
        }

        /* 先将之前的展示素材删除, 再保存点击素材 */
        List<PromClkAsset> lstOldClkAsset = repoClkAsset.findByPromCreativeId(promCreativeId);
        if (null != lstOldClkAsset && lstOldClkAsset.size() >= 0)
        {
            repoClkAsset.delete(lstOldClkAsset);
        }

        if (null != dtoPromCreative.clkAssets)
        {
            for (DtoPromCreativeDetail.Asset clkAsset : dtoPromCreative.clkAssets)
            {
                /* 请求中只有elemId, 要根据次获取字段名称、属性 */
                PromClkLayoutElem clkLayoutElem = repoClkLayout.findById(clkAsset.elemId);

                PromClkAsset onePromClkAsset = new PromClkAsset();
                onePromClkAsset.setKeyName(clkLayoutElem.getName());
                onePromClkAsset.setPromClkLayoutElemId(clkAsset.elemId);
                onePromClkAsset.setPromCreativeId(promCreativeId);
                onePromClkAsset.setLocalPath(clkAsset.content);
                onePromClkAsset.setCdnPath(clkAsset.content);
                onePromClkAsset.setIsDel(0);

                repoClkAsset.save(onePromClkAsset);
            }
        }

        return promCreativeId;
    }

    public boolean deletePromCreative(int pdUserId, int promCreativeId)
    {
        PromCreative promCreative = repoPromCreative.findByIdAndPdUserIdAndIsDel(promCreativeId, pdUserId, 0);
        if (null == promCreative)
        {
            return false;
        }

        promCreative.setIsDel(1);
        repoPromCreative.save(promCreative);
        return true;
    }
}
