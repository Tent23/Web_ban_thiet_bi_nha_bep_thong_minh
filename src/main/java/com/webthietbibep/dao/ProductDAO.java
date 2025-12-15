package com.webthietbibep.dao;

import com.webthietbibep.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/webthietbibep?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASS = "";

    // ===== KẾT NỐI DB =====
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ===== LẤY TẤT CẢ SẢN PHẨM =====
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ===== LẤY SẢN PHẨM THEO ID =====
    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapProduct(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ===== THÊM SẢN PHẨM =====
    public boolean addProduct(Product p) {
        String sql = """
            INSERT INTO product(name, price, image, category, brand, description)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            setProduct(ps, p);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== CẬP NHẬT =====
    public boolean updateProduct(Product p) {
        String sql = """
            UPDATE product
            SET name=?, price=?, image=?, category=?, brand=?, description=?
            WHERE id=?
            """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            setProduct(ps, p);
            ps.setInt(7, p.getId());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== XÓA =====
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== LỌC THEO BRAND + GIÁ =====
    public List<Product> filter(String[] brands, String priceRange) {
        List<Product> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM product WHERE 1=1 ");

        if (brands != null && brands.length > 0) {
            sql.append("AND brand IN (");
            for (int i = 0; i < brands.length; i++) {
                sql.append("?");
                if (i < brands.length - 1) sql.append(",");
            }
            sql.append(") ");
        }

        if (priceRange != null) {
            switch (priceRange) {
                case "1" -> sql.append("AND price < 5000000 ");
                case "2" -> sql.append("AND price BETWEEN 5000000 AND 10000000 ");
                case "3" -> sql.append("AND price BETWEEN 10000000 AND 20000000 ");
                case "4" -> sql.append("AND price > 20000000 ");
            }
        }

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int idx = 1;
            if (brands != null) {
                for (String b : brands) {
                    ps.setString(idx++, b);
                }
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ===== MAP RESULTSET =====
    private Product mapProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("image"),
                rs.getString("category"),
                rs.getString("brand"),
                rs.getString("description")
        );
    }

    // ===== SET PREPAREDSTATEMENT =====
    private void setProduct(PreparedStatement ps, Product p) throws SQLException {
        ps.setString(1, p.getName());
        ps.setDouble(2, p.getPrice());
        ps.setString(3, p.getImage());
        ps.setString(4, p.getCategory());
        ps.setString(5, p.getBrand());
        ps.setString(6, p.getDescription());
    }
}
