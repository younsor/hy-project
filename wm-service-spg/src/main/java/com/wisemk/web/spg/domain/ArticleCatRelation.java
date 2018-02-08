package com.wisemk.web.spg.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * The persistent class for the article_cat_relation database table.
 * 
 */
@Entity
@Table(name = "article_cat_relation")
@NamedQuery(name = "ArticleCatRelation.findAll", query = "SELECT a FROM ArticleCatRelation a")
public class ArticleCatRelation implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(name = "part3_article_source_cat", nullable = false, length = 128)
    private String            part3ArticleSourceCat;

    @Column(name = "part3_article_source_cat_desc", nullable = false, length = 128)
    private String            part3ArticleSourceCatDesc;

    @Column(name = "part3_article_source_id", nullable = false)
    private int               part3ArticleSourceId;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public ArticleCatRelation()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getPart3ArticleSourceCat()
    {
        return this.part3ArticleSourceCat;
    }

    public void setPart3ArticleSourceCat(String part3ArticleSourceCat)
    {
        this.part3ArticleSourceCat = part3ArticleSourceCat;
    }

    public String getPart3ArticleSourceCatDesc()
    {
        return this.part3ArticleSourceCatDesc;
    }

    public void setPart3ArticleSourceCatDesc(String part3ArticleSourceCatDesc)
    {
        this.part3ArticleSourceCatDesc = part3ArticleSourceCatDesc;
    }

    public int getPart3ArticleSourceId()
    {
        return this.part3ArticleSourceId;
    }

    public void setPart3ArticleSourceId(int part3ArticleSourceId)
    {
        this.part3ArticleSourceId = part3ArticleSourceId;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public Timestamp getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
    }

}