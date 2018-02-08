package cn.zyy.zhichuan.webpd.dto;

import java.util.List;

public class DtoPromLayoutClk
{
    public static class LayoutElem
    {
        public int     elemId;
        public String  name;
        public String  type;
        public int     elemKbytes;
        public String  elemMime;
        public String  elemRegex;
        public String  regexMismatchTip;
        public int     sizeMinLen;
        public int     sizeMaxLen;
        public int     sizeW;
        public int     sizeH;
        public String  description;
        public boolean elemRequired;
        public boolean elemMatch;
    };

    public int              id;
    public String           name;
    public List<LayoutElem> clkLayout;
}
