package com.webthietbibep.dao;

import com.webthietbibep.context.DBContext;
import com.webthietbibep.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public static List<Product> getBestSeller() {
        List<Product> list = new ArrayList<>();

        String sql = """
            SELECT p.*, SUM(oi.quantity) AS sold
            FROM Products p
            JOIN Order_Items oi ON p.product_id = oi.product_id
            GROUP BY p.product_id
            ORDER BY sold DESC
            LIMIT 10
        """;

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setThumbnailUrl(rs.getString("thumbnail_url"));
                p.setSlug(rs.getString("slug"));
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
