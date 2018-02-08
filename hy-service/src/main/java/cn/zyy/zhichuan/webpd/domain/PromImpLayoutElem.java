package cn.zyy.zhichuan.webpd.domain;

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
 * The persistent class for the prom_imp_layout_elem database table.
 * 
 */
@Entity
@Table(name = "prom_imp_layout_elem")
@NamedQuery(name = "PromImpLayoutElem.findAll", query = "SELECT p FROM PromImpLayoutElem p")
public class PromImpLayoutElem implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(length = 128)
    private String            description;

    @Column(name = "is_essential", nullable = false)
    private byte              isEssential;

    @Column(name = "is_match", nullable = false)
    private byte              isMatch;

    @Column(name = "limit_kbytes")
    private int               limitKbytes;

    @Column(name = "limit_mime", length = 64)
    private String            limitMime;

    @Column(length = 32)
    private String            name;

    @Column(name = "prom_imp_layout_id", nullable = false)
    private int               promImpLayoutId;

    @Column(name = "prom_layout_elem_attr_id", nullable = false)
    private int               promLayoutElemAttrId;

    @Column(length = 256)
    private String            regex;

    @Column(name = "regex_mismatch_tip", length = 255)
    private String            regexMismatchTip;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(name = "size_h")
    private int               sizeH;

    @Column(name = "size_max_len")
    private int               sizeMaxLen;

    @Column(name = "size_min_len")
    private int               sizeMinLen;

    @Column(name = "size_w")
    private int               sizeW;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public PromImpLayoutElem()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public byte getIsEssential()
    {
        return this.isEssential;
    }

    public void setIsEssential(byte isEssential)
    {
        this.isEssential = isEssential;
    }

    public byte getIsMatch()
    {
        return this.isMatch;
    }

    public void setIsMatch(byte isMatch)
    {
        this.isMatch = isMatch;
    }

    public int getLimitKbytes()
    {
        return this.limitKbytes;
    }

    public void setLimitKbytes(int limitKbytes)
    {
        this.limitKbytes = limitKbytes;
    }

    public String getLimitMime()
    {
        return this.limitMime;
    }

    public void setLimitMime(String limitMime)
    {
        this.limitMime = limitMime;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPromImpLayoutId()
    {
        return this.promImpLayoutId;
    }

    public void setPromImpLayoutId(int promImpLayoutId)
    {
        this.promImpLayoutId = promImpLayoutId;
    }

    public int getPromLayoutElemAttrId()
    {
        return this.promLayoutElemAttrId;
    }

    public void setPromLayoutElemAttrId(int promLayoutElemAttrId)
    {
        this.promLayoutElemAttrId = promLayoutElemAttrId;
    }

    public String getRegex()
    {
        return this.regex;
    }

    public void setRegex(String regex)
    {
        this.regex = regex;
    }

    public String getRegexMismatchTip()
    {
        return this.regexMismatchTip;
    }

    public void setRegexMismatchTip(String regexMismatchTip)
    {
        this.regexMismatchTip = regexMismatchTip;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public int getSizeH()
    {
        return this.sizeH;
    }

    public void setSizeH(int sizeH)
    {
        this.sizeH = sizeH;
    }

    public int getSizeMaxLen()
    {
        return this.sizeMaxLen;
    }

    public void setSizeMaxLen(int sizeMaxLen)
    {
        this.sizeMaxLen = sizeMaxLen;
    }

    public int getSizeMinLen()
    {
        return this.sizeMinLen;
    }

    public void setSizeMinLen(int sizeMinLen)
    {
        this.sizeMinLen = sizeMinLen;
    }

    public int getSizeW()
    {
        return this.sizeW;
    }

    public void setSizeW(int sizeW)
    {
        this.sizeW = sizeW;
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