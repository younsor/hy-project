package cn.zyy.zhichuan.webpd.dto;

import java.util.List;

public class DtoPromCreativeDetail
{
    public static class Asset
    {
        public int    id;
        public String name;
        public int    elemId;
        public String type;
        public String content;
        public String description;
    };

    public int         id;
    public String      name;
    public int         promotionTypeId;
    public int         clkLayoutId;
    public List<Asset> impAssets;
    public List<Asset> clkAssets;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPromotionTypeId()
    {
        return promotionTypeId;
    }

    public void setPromotionTypeId(int promotionTypeId)
    {
        this.promotionTypeId = promotionTypeId;
    }

    public int getClkLayoutId()
    {
        return clkLayoutId;
    }

    public void setClkLayoutId(int clkLayoutId)
    {
        this.clkLayoutId = clkLayoutId;
    }

    public List<Asset> getImpAssets()
    {
        return impAssets;
    }

    public void setImpAssets(List<Asset> impAssets)
    {
        this.impAssets = impAssets;
    }

    public List<Asset> getClkAssets()
    {
        return clkAssets;
    }

    public void setClkAssets(List<Asset> clkAssets)
    {
        this.clkAssets = clkAssets;
    }
}
