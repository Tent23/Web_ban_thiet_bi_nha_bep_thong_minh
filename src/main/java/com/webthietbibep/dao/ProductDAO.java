package com.webthietbibep.dao;

import com.webthietbibep.model.Product;
import com.webthietbibep.util.JdbiConnector;
import org.jdbi.v3.core.Handle;

public class ProductDAO {
    public static boolean insertProduct(Product p) {
        String sql = "INSERT INTO products (product_name, description, image, status, price, category_id, sku, stock_quantity) " +
                "VALUES (:product_name, :description, :image, :status, :price, :category_id, :sku, :stock_quantity)";
        try (Handle handle = JdbiConnector.get().open()) {
            int result = handle.createUpdate(sql)
                    .bindBean(p)
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}