package com.webthietbibep.dao;

import com.webthietbibep.db.JDBIConnector;
import com.webthietbibep.model.Product;
import java.util.List;

public class WishlistDAO {

    // 1. Lấy danh sách sản phẩm yêu thích (Đảm bảo lấy giá)
    public List<Product> getWishlistByUserId(int userId) {
        // Lưu ý: "SELECT p.*" sẽ lấy tất cả cột từ bảng products.
        // Đảm bảo bảng products của bạn có cột tên là "price" (hoặc tên tương tự khớp với Class Product)
        String sql = """
            SELECT p.* FROM products p
            JOIN wishlist w ON p.product_id = w.product_id
            WHERE w.user_id = :userId
            ORDER BY w.created_at DESC
        """;

        return JDBIConnector.get().withHandle(h ->
                h.createQuery(sql)
                        .bind("userId", userId)
                        .mapToBean(Product.class) // Tự động map cột DB vào field của Product
                        .list()
        );
    }

    // 2. Thêm sản phẩm vào yêu thích
    public boolean insert(int userId, int productId) {
        // Sử dụng NOW() cho MySQL. Nếu dùng SQL Server hãy đổi thành GETDATE()
        String sql = "INSERT INTO wishlist (user_id, product_id, created_at) VALUES (:uid, :pid, NOW())";
        try {
            return JDBIConnector.get().withHandle(h ->
                    h.createUpdate(sql)
                            .bind("uid", userId)
                            .bind("pid", productId)
                            .execute() > 0
            );
        } catch (Exception e) {
            // Nếu đã tồn tại (Duplicate Key) thì bỏ qua, không báo lỗi, coi như thêm thất bại
            return false;
        }
    }

    // 3. Xóa sản phẩm khỏi yêu thích
    public boolean delete(int userId, int productId) {
        String sql = "DELETE FROM wishlist WHERE user_id = :uid AND product_id = :pid";
        return JDBIConnector.get().withHandle(h ->
                h.createUpdate(sql)
                        .bind("uid", userId)
                        .bind("pid", productId)
                        .execute() > 0
        );
    }

    // 4. Kiểm tra sản phẩm đã được thích chưa
    public boolean checkExist(int userId, int productId) {
        String sql = "SELECT COUNT(*) FROM wishlist WHERE user_id = :uid AND product_id = :pid";

        Integer count = JDBIConnector.get().withHandle(h ->
                h.createQuery(sql)
                        .bind("uid", userId)
                        .bind("pid", productId)
                        .mapTo(Integer.class)
                        .one()
        );
        return count != null && count > 0;
    }
}