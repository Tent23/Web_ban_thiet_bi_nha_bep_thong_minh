package com.webthietbibep.dao;

import com.webthietbibep.model.Product;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO extends BaseDao {
    static Map<Integer,Product> data= new HashMap<>();
    static{
        data.put(1,new Product(1,2,"","",2.500,2,"",LocalDateTime.now()));

    }
    public void insert(List<Product>products){
        Jdbi jdbi= get();
        jdbi.useHandle(h->{
           PreparedBatch batch= h.prepareBatch("""
            INSERT INTO products
            ( category_id, product_name, description, price, stock_quantity, image)
            VALUES
            ( :category_id, :product_name, :description, :price, :stock_quantity, :image)
        """);
           products.forEach(product -> {
               batch.bindBean(product).add();
           });
           batch.execute();
        });
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> products= dao.getListProduct();
        dao.insert(products);
    }

    public List<Product> getListProduct() {
        return get().withHandle(h -> h.createQuery("select * from products").mapToBean(Product.class).list());
    }
    public Product getProduct(int id){
        return get().withHandle(h -> h.createQuery("select * from products where product_id=:product_id").bind("product_id",id).mapToBean(Product.class).stream().findFirst().orElse(null));

    }
}
