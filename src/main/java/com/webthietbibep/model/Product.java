package com.webthietbibep.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class Product implements Serializable {
    private int product_id;
    private int category_id;
    private String product_name;
    private String description;
    private double price;
    private int stock_quantity;
    private String image;
    private LocalDateTime create_at;

    public Product() {
    }

    public Product(int product_id, int category_id, String product_name,
                   String description, double price, int stock_quantity,
                   String image, LocalDateTime create_at) {

        this.product_id = product_id;
        this.category_id = category_id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.image = image;
        this.create_at = create_at;
    }

    // Getter & Setter

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }



    // Hàm định dạng tiền tệ (Ví dụ: 15.000.000 đ)
    public String getPriceFormat() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return nf.format(price);
    }
}