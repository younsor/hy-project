package com.wisemk.web.spg.dto;

import com.wisemk.web.spg.domain.ArticleShare;

public class DtoArticle
{
    public String key;
    public String url;
    public String title;
    public String pv;
    public String ts;

    public DtoArticle()
    {}

    public DtoArticle(ArticleShare shareArticle)
    {
        key = shareArticle.getId() + "";
        url = shareArticle.getUrl();
        title = shareArticle.getTitle();
        pv = "1万+";
        ts = shareArticle.getAppearTime();
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPv()
    {
        return pv;
    }

    public void setPv(String pv)
    {
        this.pv = pv;
    }

    public String getTs()
    {
        return ts;
    }

    public void setTs(String ts)
    {
        this.ts = ts;
    }
}
