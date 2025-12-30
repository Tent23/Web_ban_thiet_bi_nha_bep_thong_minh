package com.webthietbibep.model;

import java.io.Serializable;

public class Brand implements Serializable {
    private int brand_id;
    private String brand_name;
    private String logo_url;

    public Brand() {
    }

    public Brand(int brand_id, String logo_url, String brand_name) {
        this.brand_id = brand_id;
        this.logo_url = logo_url;
        this.brand_name = brand_name;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}

