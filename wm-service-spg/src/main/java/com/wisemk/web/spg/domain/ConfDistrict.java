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
 * The persistent class for the conf_district database table.
 * 
 */
@Entity
@Table(name = "conf_district")
@NamedQuery(name = "ConfDistrict.findAll", query = "SELECT c FROM ConfDistrict c")
public class ConfDistrict implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(name = "city_id", nullable = false)
    private int               cityId;

    @Column(nullable = false)
    private int               del;

    @Column(nullable = false, length = 255)
    private String            name;

    @Column(nullable = false)
    private int               order;

    public ConfDistrict()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCityId()
    {
        return this.cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId = cityId;
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

}