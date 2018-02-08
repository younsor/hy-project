package com.wisemk.web.spg.dto;

import java.util.List;

public class DtoMbClkAssets
{
    public static class PrizeItem
    {
        public String image;
        public String desc;
        public String expiryDate;
        public String landingUrl;
    }

    public int             smashEggTimes;
    public int             eggAnimationNum;
    public List<PrizeItem> lstPrizeItem;

    public int getSmashEggTimes()
    {
        return smashEggTimes;
    }

    public void setSmashEggTimes(int smashEggTimes)
    {
        this.smashEggTimes = smashEggTimes;
    }

    public int getEggAnimationNum()
    {
        return eggAnimationNum;
    }

    public void setEggAnimationNum(int eggAnimationNum)
    {
        this.eggAnimationNum = eggAnimationNum;
    }

    public List<PrizeItem> getLstPrizeItem()
    {
        return lstPrizeItem;
    }

    public void setLstPrizeItem(List<PrizeItem> lstPrizeItem)
    {
        this.lstPrizeItem = lstPrizeItem;
    }
}
