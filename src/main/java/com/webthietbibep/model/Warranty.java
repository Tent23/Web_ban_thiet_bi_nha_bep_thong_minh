package com.webthietbibep.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Warranty implements Serializable {
    private String series ;
    private String productName ;
    private int productid;
    private String customerName;
    private int customerid;
    private int orderid;
    private String phoneNumber;
    private LocalDate purchaseDate;
    private String warranty_time;

    public Warranty() {
    }

    public Warranty(String series, String productName, int productid, String customerName, int customerid, int orderid, String phoneNumber, LocalDate purchaseDate, String warranty_time) {
        this.series = series;
        this.productName = productName;
        this.productid = productid;
        this.customerName = customerName;
        this.customerid = customerid;
        this.phoneNumber = phoneNumber;
        this.purchaseDate = purchaseDate;
        this.warranty_time =warranty_time;
        this.orderid = orderid;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getWarranty_time() {
        return warranty_time;
    }

    public void setWarranty_time(String warranty_time) {
        this.warranty_time = warranty_time;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
}
