package com.webthietbibep.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class Product implements Serializable {
    private int productid;
    private int category_id;
    private String productname;
    private String description;
    private double price;
    private int stockquantity;
    private int brand_id;
    private String image;
    private LocalDateTime created_at;

    public Product() {
    }

    public Product(int productid, int category_id, String productname,
                   String description, double price, int stockquantity, int brand_id,
                   String image, LocalDateTime createat) {

        this.productid = productid;
        this.category_id = category_id;
        this.productname = productname;
        this.description = description;
        this.price = price;
        this.stockquantity = stockquantity;
        this.image = image;
        this.created_at = created_at;
    }

    // Getter & Setter

    public int getProductid() {
        return productid;
    }

    public void setProductid(int product_id) {
        this.productid = productid;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String product_name) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockquantity() {
        return stockquantity;
    }

    public void setStockquantity(int stockquantity) {
        this.stockquantity = stockquantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    // Hàm định dạng tiền tệ (Ví dụ: 15.000.000 đ)
    public String getPriceFormat() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return nf.format(price);
    }
}