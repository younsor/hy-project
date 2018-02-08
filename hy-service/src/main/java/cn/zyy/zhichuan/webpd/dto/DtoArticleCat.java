package cn.zyy.zhichuan.webpd.dto;

import cn.zyy.zhichuan.webpd.domain.ArticleCat;

public class DtoArticleCat
{
    public String key;
    public String label;

    public static DtoArticleCat of(ArticleCat cat)
    {
        DtoArticleCat inst = new DtoArticleCat();
        inst.setKey(cat.getId() + "");
        inst.setLabel(cat.getCatName());
        return inst;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
}
