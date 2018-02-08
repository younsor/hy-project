package com.wisemk.web.spg.dto;

import com.wisemk.web.spg.domain.ArticleShare;

public class DtoArticleDetail
{
    public String id;
    public String url;
    public String title;
    public String desc;
    public String thumb;

    public DtoArticleDetail()
    {}

    public DtoArticleDetail(ArticleShare shareArticle)
    {
        this.id = shareArticle.getId() + "";
        this.url = shareArticle.getUrl();
        this.title = shareArticle.getTitle();
        this.desc = shareArticle.getDescription();
        this.thumb = shareArticle.getIconUrl();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getThumb()
    {
        return thumb;
    }

    public void setThumb(String thumb)
    {
        this.thumb = thumb;
    }
}
