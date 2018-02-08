package com.wisemk.web.spg.share;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;

public class MArticleContent
{
    private static final OssLog log               = new OssLog();

    public final static String  htmlReplaceCode10 = "\"";
    public final static String  htmlReplaceCode11 = "&quot;";
    public final static String  htmlReplaceCode12 = "\\x26quot;";
    public final static String  htmlReplaceCode20 = "\'";
    public final static String  htmlReplaceCode21 = "&apos;";
    public final static String  htmlReplaceCode22 = "\\x26apos;";

    private static String       cdnHtmlDir        = null;
    public static String        sAddJs            = null;

    private static String       cdnKeyId          = "LTAISEzznWhRyyIB";
    private static String       cdnKeySecret      = "phiXu1K3v8kiibWGcfcJuk6m8tH3XL";
    private static String       cdnEndpoint       = "http://oss-cn-beijing.aliyuncs.com";
    private static String       cdnBucket         = "smartad-asset";
    private static String       cdnAccessDomain   = "http://cdn.apilnk.com";

    public String               url;
    public String               title             = "";
    public String               desc              = "";
    public String               icon              = "";
    public String               html;

    public static boolean init(String cdnDir)
    {
        sAddJs = OssFunc.getFileContent("add.js");
        if (null == sAddJs || sAddJs.length() <= 0)
        {
            return false;
        }

        cdnHtmlDir = cdnDir;

        return true;
    }

    public MArticleContent(String url)
    {
        this.url = url;
    }

    public int handlerContent()
    {
        StringBuffer htmlBuff = new StringBuffer();
        URL urlPath = null;
        HttpURLConnection conn = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            /* 用来存储整个html */
            urlPath = new URL(url);
            conn = (HttpURLConnection) urlPath.openConnection();
            isr = new InputStreamReader(conn.getInputStream());
            br = new BufferedReader(isr);
            String temp;
            while ((temp = br.readLine()) != null)
            {
                String trimTemp = temp.trim();
                if (title.length() <= 0 && trimTemp.contains("var") && trimTemp.contains("msg_title") && trimTemp.contains("="))
                {
                    title = trimTemp.substring(trimTemp.indexOf("=") + 1).trim();
                    if (title.endsWith(";"))
                    {
                        title = title.substring(0, title.length() - 1).trim();
                    }

                    if (title.startsWith("\""))
                    {
                        title = title.substring(1).trim();
                    }

                    if (title.endsWith("\""))
                    {
                        title = title.substring(0, title.length() - 1).trim();
                    }
                }

                if (desc.length() <= 0 && trimTemp.contains("var") && trimTemp.contains("msg_desc") && trimTemp.contains("="))
                {
                    desc = trimTemp.substring(trimTemp.indexOf("=") + 1).trim();
                    if (desc.endsWith(";"))
                    {
                        desc = desc.substring(0, desc.length() - 1).trim();
                    }

                    if (desc.startsWith("\""))
                    {
                        desc = desc.substring(1).trim();
                    }

                    if (desc.endsWith("\""))
                    {
                        desc = desc.substring(0, desc.length() - 1).trim();
                    }
                }

                if (icon.length() <= 0 && trimTemp.contains("var") && trimTemp.contains("msg_cdn_url") && trimTemp.contains("="))
                {
                    icon = trimTemp.substring(trimTemp.indexOf("=") + 1).trim();
                    if (icon.endsWith(";"))
                    {
                        icon = icon.substring(0, icon.length() - 1).trim();
                    }

                    if (icon.startsWith("\""))
                    {
                        icon = icon.substring(1).trim();
                    }

                    if (icon.endsWith("\""))
                    {
                        icon = icon.substring(0, icon.length() - 1).trim();
                    }
                }

                String appendHtml = temp;

                // 如果附加js不为空，则添加附加js
                if (null != sAddJs)
                {
                    /* 如果该行是</body>, 则插入附加js */
                    if (!temp.contains("<!--") && temp.contains("</body>"))
                    {
                        appendHtml = temp.replace("</body>", sAddJs + "</body>");
                    }
                }

                htmlBuff.append(appendHtml).append("\n");
            }
        }
        catch (Exception e)
        {
            log.error("get article's content exception: \n" + OssFunc.getExceptionInfo(e));

            return Constants.RET_ERROR;
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    log.error(OssFunc.getExceptionInfo(e));
                }
                br = null;
            }

            if (null != isr)
            {
                try
                {
                    isr.close();
                }
                catch (IOException e)
                {
                    log.error(OssFunc.getExceptionInfo(e));
                }

                isr = null;
            }

            if (null != conn)
            {
                conn.disconnect();
            }
            conn = null;
        }

        if (!OssFunc.isEmpty(title))
        {
            title = title.replace(htmlReplaceCode11, htmlReplaceCode10);
            title = title.replace(htmlReplaceCode12, htmlReplaceCode10);
            title = title.replace(htmlReplaceCode21, htmlReplaceCode20);
            title = title.replace(htmlReplaceCode22, htmlReplaceCode20);
            title = title.trim();
        }
        else
        {
            /* 如果没有获取到标题, 则文件错误; 有可能是过期, 或者文件被封 */
            log.error("cannot parse article-title-info, for article[" + url + "]");
            return Constants.RET_ERROR;
        }

        if (null != desc)
        {
            desc = desc.replace(htmlReplaceCode11, htmlReplaceCode10);
            desc = desc.replace(htmlReplaceCode12, htmlReplaceCode10);
            desc = desc.replace(htmlReplaceCode21, htmlReplaceCode20);
            desc = desc.replace(htmlReplaceCode22, htmlReplaceCode20);
            desc = desc.trim();
        }

        this.html = htmlBuff.toString();

        return Constants.RET_OK;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDesc()
    {
        return desc;
    }

    public String getIcon()
    {
        return icon;
    }

    /**************************************** CDN接口 *****************************************/
    public String uploadFile2Cdn(String uploadName)
    {
        byte[] bytes = html.getBytes();

        OSSClient client = new OSSClient(cdnEndpoint, cdnKeyId, cdnKeySecret);

        /* 判断bucket是否存在 */
        boolean exists = client.doesBucketExist(cdnBucket);
        if (!exists)
        {
            log.error("cdn's bucket[" + cdnBucket + "] not exist, and file[" + uploadName + "] upload fail");
            return null;
        }

        /* 获取指定文件的输入流 */
        InputStream content = new ByteArrayInputStream(bytes);

        /* 创建上传Object的Metadata */
        ObjectMetadata meta = new ObjectMetadata();

        /* 必须设置ContentLength */
        meta.setContentLength(bytes.length);

        /* 上传Object */
        String cdnFileUrl = cdnHtmlDir + "/" + uploadName;
        PutObjectResult result = client.putObject(cdnBucket, cdnFileUrl, content, meta);

        return cdnAccessDomain + "/" + cdnFileUrl;
    }
}
