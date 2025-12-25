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
            ( categoryid, productname, description, price, stockquantity, image)
            VALUES
            ( :categoryid, :productname, :description, :price, :stockquantity, :image)
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
        return get().withHandle(h -> h.createQuery("select * from products where product_id=:productid").bind("productid",id).mapToBean(Product.class).stream().findFirst().orElse(null));

    }
    public List<Product> getProductsFilter(String priceRange, String sort,String[] brands) {
        StringBuilder sql = new StringBuilder("SELECT p.* FROM products p " +
                "LEFT JOIN brands b ON p.brand_id = b.brand_id " +
                "WHERE 1=1");

        // LỌC GIÁ
        if (priceRange != null) {
            switch (priceRange) {
                case "1":
                    sql.append(" AND price < 5000000");
                    break;
                case "2":
                    sql.append(" AND price BETWEEN 5000000 AND 10000000");
                    break;
                case "3":
                    sql.append(" AND price BETWEEN 10000000 AND 20000000");
                    break;
                case "4":
                    sql.append(" AND price > 20000000");
                    break;
            }
        }
        if (brands != null && brands.length > 0) {
            sql.append(" AND b.brand_id IN (");
            for (int i = 0; i < brands.length; i++) {
                sql.append("'").append(brands[i]).append("'");
                if (i < brands.length - 1) sql.append(",");
            }
            sql.append(")");
        }

        // SẮP XẾP
        if (sort != null) {
            switch (sort) {
                case "price_asc":
                    sql.append(" ORDER BY price ASC");
                    break;
                case "price_desc":
                    sql.append(" ORDER BY price DESC");
                    break;
                default:
                    sql.append(" ORDER BY created_at DESC");
            }
        } else {
            sql.append(" ORDER BY created_at DESC");
        }

        return get().withHandle(h ->
                h.createQuery(sql.toString())
                        .mapToBean(Product.class)
                        .list()
        );
    }

}
