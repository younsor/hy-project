package com.wisemk.web.spg.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wisemk.web.spg.domain.PromCode;
import com.wisemk.web.spg.dto.DtoPromCode;
import com.wisemk.web.spg.dto.DtoPromCodeDetail;
import com.wisemk.web.spg.dto.DtoPullDown;
import com.wisemk.web.spg.repo.RepoPromCode;
import com.wisemk.web.spg.share.CommonPage;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;

@Service
public class SrvImplPromCode
{
    private static final OssLog log = new OssLog();

    @Value("${access.promcode.url.prefix}")
    private String              promCodeUrlPrefix;

    @Autowired
    private RepoPromCode        repoPromCode;

    public List<DtoPullDown> getPromCodePullDown(Integer pdUid)
    {
        List<DtoPullDown> lstDtoPullDown = Lists.newArrayList();

        List<Object[]> lstPromCode = repoPromCode.findPromCodePullDown(pdUid);
        if (null == lstPromCode || lstPromCode.isEmpty())
        {
            return lstDtoPullDown;
        }

        for (Object[] aPromCodeInfo : lstPromCode)
        {
            int tmpValue = Integer.parseInt(aPromCodeInfo[0].toString());
            String tmpText = aPromCodeInfo[1].toString();
            lstDtoPullDown.add(new DtoPullDown(tmpValue, tmpText));
        }

        return lstDtoPullDown;
    }

    public CommonPage<DtoPromCode> getPromCodeList(Integer pdUid, String isActivate, String keyName, PageRequest page)
    {
        Page<Object[]> pageRslt = null;
        if (null == isActivate || isActivate.length() <= 0)
        {
            if (null == keyName || keyName.length() <= 0)
            {
                pageRslt = repoPromCode.findPromCodeListByPdUserId(pdUid, page);
            }
            else
            {
                String tmpKeyName = "%" + keyName.trim() + "%";
                pageRslt = repoPromCode.findPromCodeListByPdUserIdAndNameLike(pdUid, tmpKeyName, page);
            }
        }
        else
        {
            int state = Integer.parseInt(isActivate);
            if (null == keyName || keyName.length() <= 0)
            {
                pageRslt = repoPromCode.findPromCodeListByPdUserIdAndState(pdUid, state, page);
            }
            else
            {
                String tmpKeyName = "%" + keyName.trim() + "%";
                pageRslt = repoPromCode.findPromCodeListByPdUserIdAndStateAndNameLike(pdUid, state, tmpKeyName, page);
            }
        }

        List<DtoPromCode> lstDtoPromCode = Lists.newArrayList();
        if (null != pageRslt)
        {
            for (Object[] lstField : pageRslt)
            {
                DtoPromCode dtoPromCode = new DtoPromCode();
                dtoPromCode.id = OssFunc.DataConvert.toInt(lstField[0], 0);
                dtoPromCode.status = (Integer.parseInt(lstField[1].toString()) > 0) ? true : false;
                dtoPromCode.mid = OssFunc.DataConvert.toInt(lstField[2], 0);
                dtoPromCode.mName = OssFunc.DataConvert.toStr(lstField[3], "");
                dtoPromCode.name = OssFunc.DataConvert.toStr(lstField[4], "");
                dtoPromCode.tel = OssFunc.DataConvert.toStr(lstField[5], "");
                dtoPromCode.limitTimes = OssFunc.DataConvert.toInt(lstField[6], 0);

                String tmpTs = lstField[7] + "";
                if (tmpTs.contains(".0"))
                {
                    tmpTs = tmpTs.substring(0, tmpTs.length() - 2);
                }
                dtoPromCode.limitTs = OssFunc.DataConvert.toStr(tmpTs, "");

                dtoPromCode.url = promCodeUrlPrefix + OssFunc.DataConvert.toStr(lstField[8], "");

                lstDtoPromCode.add(dtoPromCode);
            }
        }

        return CommonPage.build(lstDtoPromCode, pageRslt);
    }

    public DtoPromCodeDetail getPromCodeDetail(int promCodeId)
    {
        PromCode promCode = repoPromCode.findById(promCodeId);
        if (null == promCode)
        {
            return null;
        }

        DtoPromCodeDetail promCodeDetail = new DtoPromCodeDetail();
        promCodeDetail.id = promCode.getId();
        promCodeDetail.mid = promCode.getPromCreativeId();
        promCodeDetail.name = promCode.getName();
        promCodeDetail.tel = promCode.getTel();
        promCodeDetail.times = promCode.getLimitTimes() - promCode.getTimes();

        return promCodeDetail;
    }

    private String getRandomMd5(Random randomKey)
    {
        int rk = randomKey.nextInt(Math.abs(randomKey.nextInt()));
        String md5Key = String.valueOf(rk) + String.valueOf(System.nanoTime());
        return OssFunc.Md5(md5Key);
    }

    public boolean generatePromCode(int pdUid, int num)
    {
        List<PromCode> lstPromCodes = Lists.newArrayList();

        Random randomKey = new Random(System.nanoTime());
        for (int idxCode = 0; idxCode < num; idxCode++)
        {
            PromCode promCode = new PromCode();
            promCode.setShowId(getRandomMd5(randomKey));
            promCode.setPdUserId(pdUid);
            promCode.setState(0);
            promCode.setIsDel(0);
            promCode.setLimitTimes(1000);
            promCode.setTimes(0);

            lstPromCodes.add(promCode);
        }

        lstPromCodes = repoPromCode.save(lstPromCodes);
        if (null == lstPromCodes)
        {
            log.error("generate " + num + " prom-code for pd-user[" + pdUid + "] fail");
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean activateOrUpdatePromCode(int promCodeId, int promCreativeId, String userName, String tel)
    {
        PromCode promCode = repoPromCode.findById(promCodeId);
        if (null == promCode)
        {
            return false;
        }

        /* 判断是否是第一次激活 */
        if (promCode.getState() <= 0)
        {
            long currTs = System.currentTimeMillis();
            promCode.setBeginTime(new Timestamp(currTs));
            promCode.setEndTime(new Timestamp(currTs + +365 * 86400000L));
        }

        /* 判断是否需要更新次数 */
        if (promCreativeId != promCode.getPromCreativeId())
        {
            promCode.setTimes(promCode.getTimes() + 1);
        }

        promCode.setPromCreativeId(promCreativeId);
        promCode.setName(userName);
        promCode.setTel(tel);
        promCode.setState(1);

        repoPromCode.save(promCode);

        return true;
    }

    public int getPromCodeId(String promCodeShowId)
    {
        PromCode promCode = repoPromCode.findByShowId(promCodeShowId);
        if (null != promCode)
        {
            return promCode.getId();
        }
        else
        {
            return 0;
        }
    }
}
