package com.wisemk.web.spg.dto;

public class DtoMbUrlSign
{
    public String appId;
    public String noncestr;
    public String ts;
    public String sign;

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getNoncestr()
    {
        return noncestr;
    }

    public void setNoncestr(String noncestr)
    {
        this.noncestr = noncestr;
    }

    public String getTs()
    {
        return ts;
    }

    public void setTs(String ts)
    {
        this.ts = ts;
    }

    public String getSign()
    {
        return sign;
    }

    public void setSign(String sign)
    {
        this.sign = sign;
    }
}
