package com.wisemk.web.spg.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the conf_city database table.
 * 
 */
@Entity
@Table(name = "conf_city")
@NamedQuery(name = "ConfCity.findAll", query = "SELECT c FROM ConfCity c")
public class ConfCity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(nullable = false)
    private int               del;

    @Column(nullable = false, length = 255)
    private String            name;

    @Column(nullable = false)
    private int               order;

    @Column(name = "province_id", nullable = false)
    private int               provinceId;

    public ConfCity()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getDel()
    {
        return this.del;
    }

    public void setDel(int del)
    {
        this.del = del;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getOrder()
    {
        return this.order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public int getProvinceId()
    {
        return this.provinceId;
    }

    public void setProvinceId(int provinceId)
    {
        this.provinceId = provinceId;
    }

}