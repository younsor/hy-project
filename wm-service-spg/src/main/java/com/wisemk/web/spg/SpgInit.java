package com.wisemk.web.spg;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wisemk.web.spg.share.MArticleContent;

import cn.zyy.oss.share.OssLog;

@Component
public class SpgInit
{
    private static final OssLog log = new OssLog();

    @Value("${upload.cdn.dir}")
    public String               cdnUploadDir;

    @PostConstruct
    public void initMethod() throws Exception
    {
        if (null == cdnUploadDir || cdnUploadDir.length() <= 0)
        {
            log.error("null == cdnUploadDir || cdnUploadDir.length() <= 0");
            throw new Exception("cdnUploadDir invalid");
        }
        if (!MArticleContent.init(cdnUploadDir))
        {
            throw new Exception("load add.js fail");
        }
    }
}
