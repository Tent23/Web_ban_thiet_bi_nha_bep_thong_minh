package com.webthietbibep.model;

public class Product {
    private int id;
    private String product_name;
    private String description;
    private String image;
    private String status;
    private boolean featured;
    private double price;
    private int category_id;
    private int brand_id;
    private String sku;
    private int stock_quantity;

    // Constructors
    public Product() {}

    // Getters and Setters (Quan trọng để JDBI tự động map dữ liệu)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getProduct_name() { return product_name; }
    public void setProduct_name(String product_name) { this.product_name = product_name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getCategory_id() { return category_id; }
    public void setCategory_id(int category_id) { this.category_id = category_id; }

    public int getBrand_id() { return brand_id; }
    public void setBrand_id(int brand_id) { this.brand_id = brand_id; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public int getStock_quantity() { return stock_quantity; }
    public void setStock_quantity(int stock_quantity) { this.stock_quantity = stock_quantity; }
}