package com.webthietbibep.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Product implements Serializable {
    // Tên biến này dùng để map với :product_id
    private int product_id;
    private int categoryid;
    private String productname;
    private String description;
    private double price;
    private int stockquantity;
    private int brand_id;
    private String image;
    private LocalDateTime created_at;

    public Product() {}

    // --- GETTER & SETTER (Bắt buộc phải có để JDBI bindBean hoạt động) ---

    public int getProduct_id() { return product_id; }
    public void setProduct_id(int product_id) { this.product_id = product_id; }

    public int getCategoryid() { return categoryid; }
    public void setCategoryid(int categoryid) { this.categoryid = categoryid; }

    public String getProductname() { return productname; }
    public void setProductname(String productname) { this.productname = productname; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStockquantity() { return stockquantity; }
    public void setStockquantity(int stockquantity) { this.stockquantity = stockquantity; }

    public int getBrand_id() { return brand_id; }
    public void setBrand_id(int brand_id) { this.brand_id = brand_id; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public LocalDateTime getCreated_at() { return created_at; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }
}