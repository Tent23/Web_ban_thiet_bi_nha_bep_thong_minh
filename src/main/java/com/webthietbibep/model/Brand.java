package com.webthietbibep.model;

import java.io.Serializable;

public class Brand implements Serializable {
    private int brand_id;
    private String brand_name;

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
}

