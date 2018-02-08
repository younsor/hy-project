package com.wisemk.web.spg.dto;

public class DtoMbCreativeDetail
{
    public String id;

    public String splash;       /* 公共信息-硬广霸屏大图 */
    public String interstitial; /* 公共信息-硬广插屏 */
    public String banner;       /* 公共信息-硬广横幅 */

    public String circularPic;  /* 公共信息-软文个人头像 */
    public String name;         /* 公共信息-软文联系人名称 */
    public String desc;         /* 公共信息-软文联系人描述 */

    public String qrcode;       /* 公共信息-联系人二维码图片 */
    public String tele;         /* 公共信息-联系人电话号码 */
    public String landingUrl;   /* 公共信息-落地页 */

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSplash()
    {
        return splash;
    }

    public void setSplash(String splash)
    {
        this.splash = splash;
    }

    public String getInterstitial()
    {
        return interstitial;
    }

    public void setInterstitial(String interstitial)
    {
        this.interstitial = interstitial;
    }

    public String getBanner()
    {
        return banner;
    }

    public void setBanner(String banner)
    {
        this.banner = banner;
    }

    public String getCircularPic()
    {
        return circularPic;
    }

    public void setCircularPic(String circularPic)
    {
        this.circularPic = circularPic;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getQrcode()
    {
        return qrcode;
    }

    public void setQrcode(String qrcode)
    {
        this.qrcode = qrcode;
    }

    public String getTele()
    {
        return tele;
    }

    public void setTele(String tele)
    {
        this.tele = tele;
    }

    public String getLandingUrl()
    {
        return landingUrl;
    }

    public void setLandingUrl(String landingUrl)
    {
        this.landingUrl = landingUrl;
    }
}
