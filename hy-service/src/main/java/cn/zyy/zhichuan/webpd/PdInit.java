package cn.zyy.zhichuan.webpd;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.zyy.oss.share.OssLog;
import cn.zyy.zhichuan.webpd.share.MArticleContent;

@Component
public class PdInit
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
