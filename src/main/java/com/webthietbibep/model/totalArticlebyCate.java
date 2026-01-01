package com.webthietbibep.model;

import java.io.Serializable;

public class totalArticlebyCate implements Serializable {

    private int category_id;
    private String category_name;
    private int total;

    public totalArticlebyCate() {
    }

    public totalArticlebyCate( int category_id,String category_name, int total) {

        this.category_id = category_id;
        this.total = total;
        this.category_name = category_name;
    }



    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
