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
            ( category_id, product_name, description, price, stock_quantity,brand_id, image)
            VALUES
            ( :category_id, :product_name, :description, :price, :stock_quantity, :brand_id, :image)
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
    public List<Product> getProductsFilter(
            String priceRange,
            String sort,
            String[] brands,
            Integer categoryId,
            int page,
            int pageSize
    ) {

        StringBuilder sql = new StringBuilder("""
        SELECT
            p.product_id,
            p.category_id,
            p.product_name,
            p.description,
            p.price,
            p.stock_quantity,
            p.brand_id,
            p.image,
            p.created_at
        FROM products p
        WHERE 1=1
    """);

        // ===== CATEGORY =====
        if (categoryId != null) {
            sql.append(" AND p.category_id = :categoryId");
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
        sql.append(" LIMIT :limit OFFSET :offset");

        int offset = (page - 1) * pageSize;

        return get().withHandle(h -> {
            var query = h.createQuery(sql.toString()).bind("limit",pageSize).bind("offset",offset);

            if (categoryId != null) {
                query.bind("categoryId", categoryId);
            }
            if (brands != null && brands.length > 0) {
                query.bindList("brands", brands);
            }

            return query.mapToBean(Product.class).list();
        });
    }
    public int countProducts(
            String priceRange,
            String[] brands,
            Integer categoryId
    ) {
        StringBuilder sql = new StringBuilder("""
            SELECT COUNT(*) FROM products p WHERE 1=1
        """);

        if (categoryId != null) {
            sql.append(" AND p.category_id = :categoryId");
        }

        if (priceRange != null) {
            switch (priceRange) {
                case "1" -> sql.append(" AND p.price < 5000000");
                case "2" -> sql.append(" AND p.price BETWEEN 5000000 AND 10000000");
                case "3" -> sql.append(" AND p.price BETWEEN 10000000 AND 20000000");
                case "4" -> sql.append(" AND p.price > 20000000");
            }
        }

        if (brands != null && brands.length > 0) {
            sql.append(" AND p.brand_id IN (<brands>)");
        }

        return get().withHandle(h -> {
            var query = h.createQuery(sql.toString());

            if (categoryId != null) {
                query.bind("categoryId", categoryId);
            }
            if (brands != null && brands.length > 0) {
                query.bindList("brands", brands);
            }

            return query.mapTo(Integer.class).one();
        });
    }
}
