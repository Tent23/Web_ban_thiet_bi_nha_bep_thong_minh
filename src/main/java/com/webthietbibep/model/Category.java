package com.webthietbibep.model;

import java.io.Serializable;

public class Category implements Serializable {
    int category_id;
    String category_name;
    String logo;

    public Category(int category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.logo = logo;
    }

    public Category() {
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}


