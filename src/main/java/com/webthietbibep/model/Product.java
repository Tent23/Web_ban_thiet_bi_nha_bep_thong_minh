package com.webthietbibep.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Product {
    private int id;
    private String name;
    private double price;
    private String image;

    public Product() {
    }

    public Product(int id, String name, double price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    // Hàm định dạng tiền tệ (Ví dụ: 15.000.000 đ)
    public String getPriceFormat() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return nf.format(price);
    }
}