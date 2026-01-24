package com.webthietbibep.model;

public class TopProduct {
    private String productName;
    private String productImage;
    private int totalSold;
    private double totalRevenue;

    // JDBI cần Constructor rỗng hoặc Getter/Setter
    public TopProduct() {}

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }
    public int getTotalSold() { return totalSold; }
    public void setTotalSold(int totalSold) { this.totalSold = totalSold; }
    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }
}