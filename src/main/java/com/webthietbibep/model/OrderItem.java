package com.webthietbibep.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private int order_id ;
    private int product_id ;
    private int quantity ;
    private double price ;

    public OrderItem(int order_id, int product_id, int quantity, double price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }
}
