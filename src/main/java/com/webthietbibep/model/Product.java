package com.webthietbibep.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class Product implements Serializable {
    private int productid;
    private int categoryid;
    private String productname;
    private String description;
    private double price;
    private int stockquantity;
    private String image;
    private LocalDateTime createat;

    public Product() {
    }

    public Product(int productid, int categoryid, String productname,
                   String description, double price, int stockquantity,
                   String image, LocalDateTime createat) {

        this.productid = productid;
        this.categoryid = categoryid;
        this.productname = productname;
        this.description = description;
        this.price = price;
        this.stockquantity = stockquantity;
        this.image = image;
        this.createat = createat;
    }

    // Getter & Setter

    public int getProductid() {
        return productid;
    }

    public void setProductid(int product_id) {
        this.productid = productid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int category_id) {
        this.categoryid = categoryid;
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

    public LocalDateTime getCreateat() {
        return createat;
    }

    public void setCreateat(LocalDateTime create_at) {
        this.createat = createat;
    }



    // Hàm định dạng tiền tệ (Ví dụ: 15.000.000 đ)
    public String getPriceFormat() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return nf.format(price);
    }
}