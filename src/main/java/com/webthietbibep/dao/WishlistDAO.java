package com.webthietbibep.dao;

import com.webthietbibep.db.JDBIConnector;
import com.webthietbibep.model.Product;
import java.util.List;

public class WishlistDAO {

    // Lấy danh sách sản phẩm yêu thích của 1 user (JOIN bảng products)
    public List<Product> getWishlistByUserId(int userId) {
        String sql = """
            SELECT p.* FROM products p
            JOIN wishlist w ON p.product_id = w.product_id
            WHERE w.user_id = :userId
            ORDER BY w.created_at DESC
        """;

        return JDBIConnector.get().withHandle(h ->
                h.createQuery(sql)
                        .bind("userId", userId)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    // Thêm sản phẩm vào yêu thích
    public boolean insert(int userId, int productId) {
        String sql = "INSERT INTO wishlist (user_id, product_id, created_at) VALUES (:uid, :pid, NOW())";
        try {
            return JDBIConnector.get().withHandle(h ->
                    h.createUpdate(sql)
                            .bind("uid", userId)
                            .bind("pid", productId)
                            .execute() > 0
            );
        } catch (Exception e) {
            // Nếu trùng (đã tồn tại) sẽ ném lỗi do Primary Key -> Trả về false
            return false;
        }
    }

    // Xóa sản phẩm khỏi yêu thích
    public boolean delete(int userId, int productId) {
        String sql = "DELETE FROM wishlist WHERE user_id = :uid AND product_id = :pid";
        return JDBIConnector.get().withHandle(h ->
                h.createUpdate(sql)
                        .bind("uid", userId)
                        .bind("pid", productId)
                        .execute() > 0
        );
    }

    // Kiểm tra sản phẩm đã được thích chưa
    public boolean checkExist(int userId, int productId) {
        String sql = "SELECT COUNT(*) FROM wishlist WHERE user_id = :uid AND product_id = :pid";
        return JDBIConnector.get().withHandle(h ->
                h.createQuery(sql)
                        .bind("uid", userId)
                        .bind("pid", productId)
                        .mapTo(Integer.class)
                        .one() > 0
        );
    }
}