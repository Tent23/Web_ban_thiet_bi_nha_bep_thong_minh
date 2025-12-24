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


    public List<Product> getListProduct() {
        return get().withHandle(h -> h.createQuery("select * from products").mapToBean(Product.class).list());
    }
    public Product getProduct(int id){
        return get().withHandle(h -> h.createQuery("select * from products where product_id=:product_id").bind("product_id",id).mapToBean(Product.class).stream().findFirst().orElse(null));

    }
    public void insertOne(Product product) {
        get().useHandle(h ->
                h.createUpdate("""
                INSERT INTO products
                (category_id, product_name, description, price, stock_quantity, image, create_at)
                VALUES
                (:category_id, :product_name, :description, :price, :stock_quantity, :image, :create_at)
            """)
                        .bindBean(product)
                        .execute()
        );
    }

    public void update(Product product) {
        get().useHandle(h ->
                h.createUpdate("""
                UPDATE products SET
                    category_id = :category_id,
                    product_name = :product_name,
                    description = :description,
                    price = :price,
                    stock_quantity = :stock_quantity,
                    image = :image
                WHERE product_id = :product_id
            """)
                        .bindBean(product)
                        .execute()
        );
    }

    public void delete(int productId) {
        get().useHandle(h ->
                h.createUpdate("DELETE FROM products WHERE product_id = :id")
                        .bind("id", productId)
                        .execute()
        );
    }
}
