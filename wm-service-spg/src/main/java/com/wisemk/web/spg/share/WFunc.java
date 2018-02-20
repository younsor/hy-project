package com.wisemk.web.spg.share;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;

public class WFunc
{
    private static final OssLog log = new OssLog();

    public static String logReqRsp(HttpServletRequest request, WJsonResult rsp)
    {
        /* 请求信息 */
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("\n------------------------------------------");
        strBuff.append("\nrequest-info\n");
        strBuff.append("\ttype: " + request.getMethod() + "\n");
        strBuff.append("\turl: " + request.getServletPath() + "\n");

        strBuff.append("\tparam:\n");
        Enumeration<String> tmpParamEnum = request.getParameterNames();
        while (tmpParamEnum.hasMoreElements())
        {
            String tmpParam = tmpParamEnum.nextElement();
            String tmpParamValue = request.getParameter(tmpParam);
            strBuff.append("\t\t" + tmpParam + "=" + tmpParamValue + "\n");
        }

        String headToken = OssFunc.DataConvert.toStr(request.getHeader(WConstants.HTTP_ATTR_TOKEN), "");
        strBuff.append("\theader-token: " + headToken + "\n");

        String attrUid = OssFunc.DataConvert.toStr(request.getAttribute(WConstants.HTTP_ATTR_USER), "");
        strBuff.append("\tattr-uid: " + attrUid + "\n");

        /* 响应信息 */
        strBuff.append("rsp-info\n");
        String prettyRsp = "\t" + rsp.toJsonString();
        prettyRsp = prettyRsp.replaceAll("\n", "\n\t");
        strBuff.append(prettyRsp + "\n\n");

        return strBuff.toString();
    }
}