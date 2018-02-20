package com.wisemk.web.spg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wisemk.web.spg.domain.Mall;
import com.wisemk.web.spg.dto.DtoMall;
import com.wisemk.web.spg.repo.RepoMall;

import cn.zyy.oss.share.OssLog;

@Service
public class SrvImplMall
{
    private static final OssLog log = new OssLog();

    @Autowired
    private RepoMall            repoMall;

    public String getSecret(String appid)
    {
        Mall mall = repoMall.findByAppidAndDel(appid, 0);
        if (null == mall)
        {
            return null;
        }

        return mall.getSecret();
    }

    public DtoMall getMainInfo(int mallId)
    {
        Mall mall = repoMall.findByIdAndDel(mallId, 0);
        if (null == mall)
        {
            return null;
        }

        /* 商城公告 */
        DtoMall mallInfo = new DtoMall();
        mallInfo.notice = mall.getNotice();

        /* 商城轮播图 */
        mallInfo.lstRollPic = Lists.newArrayList();
        List<Object[]> lstPollPic = repoMall.getMallRoll(mallId);
        if (null != lstPollPic)
        {
            for (Object[] lstObj : lstPollPic)
            {
                mallInfo.lstRollPic.add(DtoMall.RollPic.valueOf(lstObj));
            }
        }

        /* 商城专题 */
        mallInfo.lstSubject = Lists.newArrayList();
        List<Object[]> lstSubject = repoMall.getMallSubject(mallId);
        if (null != lstSubject)
        {
            for (Object[] lstObj : lstSubject)
            {
                mallInfo.lstSubject.add(DtoMall.Subject.valueOf(lstObj));
            }
        }

        return mallInfo;
    }
}
