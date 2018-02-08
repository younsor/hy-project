package cn.zyy.zhichuan.webpd.dto;

import java.util.List;

import cn.zyy.zhichuan.webpd.domain.PdModule;

public class DtoPdModule
{
    public int               id;
    public String            name;
    public String            type;
    public int               parentId;
    public String            control;
    public String            action;
    public int               sort;
    public String            icon;
    public List<DtoPdModule> children;

    public static DtoPdModule of(PdModule m)
    {
        if (null == m)
            return null;

        DtoPdModule inst = new DtoPdModule();
        inst.id = m.getId();
        inst.name = m.getName();
        inst.type = m.getType();
        inst.parentId = m.getParentId();
        inst.control = m.getControl();
        inst.action = m.getAction();
        inst.sort = m.getSort();
        inst.icon = m.getIcon();

        return inst;
    }

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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public String getControl()
    {
        return control;
    }

    public void setControl(String control)
    {
        this.control = control;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public int getSort()
    {
        return sort;
    }

    public void setSort(int sort)
    {
        this.sort = sort;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public List<DtoPdModule> getChildren()
    {
        return children;
    }

    public void setChildren(List<DtoPdModule> children)
    {
        this.children = children;
    }
}
