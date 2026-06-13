package com.webthietbibep.cart;

import com.webthietbibep.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Cart implements Serializable {
    Map<Integer, CartItem> data = new HashMap<Integer, CartItem>();

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> data) {
        this.data = data;
    }

    public void addItem(Product product, int quantity) {
        if(quantity <= 0) {
            quantity = 1;
        }
        if(!data.containsKey(product.getProduct_id())){
            data.put(product.getProduct_id(),new CartItem(product,quantity,product.getPrice() ));
        }
        else {
            data.get(product.getProduct_id()).upQuantity(quantity);
        }
    }

    public void delItem(int id ) {
        data.remove(id);
    }
    public List<CartItem>  delAllItems() {
        if(this.data != null ) {
            this.data.clear();
        }
        return new ArrayList<>();
    }
    public List<CartItem> getItems(){
        return new ArrayList<>(data.values());
    }
    public int getTotalQuantity(){
        AtomicInteger total =  new AtomicInteger();
        data.values().forEach(item->total.addAndGet(item.getQuantity()));
        return  total.get();
    }

    public double getTotal(){
        AtomicReference<Double> sum = new AtomicReference<>((double)0);
        data.values().forEach(item->sum.updateAndGet(v -> v + (item.getPrice() * item.getQuantity())));
        return  sum.get();
    }

    public Map<Integer, CartItem> getData() {
        return data;
    }

    public void setData(Map<Integer, CartItem> data) {
        this.data = data;
    }

    public String getFormatTotal(){
        return CartItem.Format(getTotal());
    }

    public String toJson() {
        StringBuilder json = new StringBuilder();
        json.append("{\"items\":[");
        boolean firstItem = true;
        for (CartItem item : data.values()) {
            if (!firstItem) {
                json.append(",");
            }
            json.append("{");
            json.append("\"productId\":").append(item.getProduct().getProduct_id()).append(",");
            json.append("\"productName\":\"").append(escapeJson(item.getProduct().getProduct_name())).append("\",");
            json.append("\"price\":").append(item.getPrice()).append(","); // Use price from CartItem (price_at_purchase)
            json.append("\"quantity\":").append(item.getQuantity());
            json.append("}");
            firstItem = false;
        }
        json.append("]}");
        return json.toString();
    }

    private String escapeJson(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}
