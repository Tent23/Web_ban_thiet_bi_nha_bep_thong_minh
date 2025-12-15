package com.webthietbibep.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Product {
    private int id;
    private String name;
    private double price;
    private String image;
    private String category;
    private String brand;
    private String description;

    public Product(String name, double price, String image,
                   String category, String brand, String decription) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.brand = brand;
        this.description = description;
    }

    // Constructor đầy đủ (dùng khi lấy từ database)
    public Product(int id, String name, double price, String image,
                   String category, String brand, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.brand = brand;
        this.description = description;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // Hàm định dạng tiền tệ (Ví dụ: 15.000.000 đ)
    public String getPriceFormat() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return nf.format(price);
    }
}