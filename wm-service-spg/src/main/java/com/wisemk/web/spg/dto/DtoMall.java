package com.wisemk.web.spg.dto;

import java.util.List;

import cn.zyy.oss.share.OssFunc;

public class DtoMall
{
    public static class RollPic
    {
        public String imageUrl;
        public String landingUrl;

        public static RollPic valueOf(Object[] lstObj)
        {
            RollPic rollPic = new RollPic();
            rollPic.imageUrl = OssFunc.DataConvert.toStr(lstObj[0], "");
            rollPic.landingUrl = OssFunc.DataConvert.toStr(lstObj[2], "");

            return rollPic;
        }
    }

    public static class Subject
    {
        public int    id;
        public String name;

        public static Subject valueOf(Object[] lstObj)
        {
            Subject subject = new Subject();
            subject.id = OssFunc.DataConvert.toInt(lstObj[0], 0);
            subject.name = OssFunc.DataConvert.toStr(lstObj[1], "");

            return subject;
        }
    }

    public String        notice;
    public List<RollPic> lstRollPic;
    public List<Subject> lstSubject;

    public String getNotice()
    {
        return notice;
    }

    public void setNotice(String notice)
    {
        this.notice = notice;
    }

    public List<RollPic> getLstRollPic()
    {
        return lstRollPic;
    }

    public void setLstRollPic(List<RollPic> lstRollPic)
    {
        this.lstRollPic = lstRollPic;
    }

    public List<Subject> getLstSubject()
    {
        return lstSubject;
    }

    public void setLstSubject(List<Subject> lstSubject)
    {
        this.lstSubject = lstSubject;
    }
}
