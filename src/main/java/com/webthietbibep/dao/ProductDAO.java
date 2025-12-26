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
    public List<Product> getProductsFilter(
            String priceRange,
            String sort,
            String[] brands,
            Integer categoryId
    ) {

        StringBuilder sql = new StringBuilder("""
        SELECT
            p.productid,
            p.categoryid,
            p.productname,
            p.description,
            p.price,
            p.stockquantity,
            p.brand_id,
            p.image,
            p.created_at
        FROM products p
        LEFT JOIN brands b ON p.brand_id = b.brand_id
        WHERE 1=1
    """);

        // ===== CATEGORY =====
        if (categoryId != null) {
            sql.append(" AND p.categoryid = :categoryId");
        }

        // ===== PRICE =====
        if (priceRange != null) {
            switch (priceRange) {
                case "1" -> sql.append(" AND p.price < 5000000");
                case "2" -> sql.append(" AND p.price BETWEEN 5000000 AND 10000000");
                case "3" -> sql.append(" AND p.price BETWEEN 10000000 AND 20000000");
                case "4" -> sql.append(" AND p.price > 20000000");
            }
        }

        // ===== BRAND =====
        if (brands != null && brands.length > 0) {
            sql.append(" AND p.brand_id IN (<brands>)");
        }

        // ===== SORT =====
        if ("price_asc".equals(sort)) {
            sql.append(" ORDER BY p.price ASC");
        } else if ("price_desc".equals(sort)) {
            sql.append(" ORDER BY p.price DESC");
        } else {
            sql.append(" ORDER BY p.created_at DESC");
        }

        return get().withHandle(h -> {
            var query = h.createQuery(sql.toString());

            if (categoryId != null) {
                query.bind("categoryId", categoryId);
            }
            if (brands != null && brands.length > 0) {
                query.bindList("brands", brands);
            }

            return query.mapToBean(Product.class).list();
        });
    }

}
