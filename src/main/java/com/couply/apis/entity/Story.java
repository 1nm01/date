package com.couply.apis.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Story {
    @GeneratedValue
    @Id
    private Integer id;
    private String ur_name;
    private String partner_name;
    private String detail;

    public String getUr_name() {
        return ur_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public String getDetail() {
        return detail;
    }

    public void setUr_name(String ur_name) {
        this.ur_name = ur_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
