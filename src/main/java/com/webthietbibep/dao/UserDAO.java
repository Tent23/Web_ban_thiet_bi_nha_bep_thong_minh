package com.webthietbibep.dao;

import com.webthietbibep.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class UserDAO extends BaseDao {

    // Update profile (KHÔNG update username)
    public void updateProfile(User user) {
        get().useHandle(handle ->
                handle.createUpdate("""
                    UPDATE users
                    SET full_name = :fullName,
                        email     = :email,
                        phone     = :phone
                    WHERE user_id = :id
                """)
                        .bind("fullName", user.getFull_name())
                        .bind("email", user.getEmail())
                        .bind("phone", user.getPhone())
                        .bind("id", user.getUser_id())
                        .execute()
        );
    }
    // 1. Lấy danh sách tất cả user
    public List<User> findAll() {
        return get().withHandle(handle ->
                handle.createQuery("SELECT * FROM users ORDER BY user_id DESC")
                        .mapToBean(User.class)
                        .list()
        );
    }

    // 2. Tìm theo ID (Đã có - giữ nguyên)
    public User findById(int userId) {
        return get().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE user_id = :id")
                        .bind("id", userId)
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null)
        );
    }

    // 3. Thêm mới User (Admin tạo)
    public void insert(User user) {
        get().useHandle(handle ->
                handle.createUpdate("INSERT INTO users (username, full_name, email, phone, password_hash, role, create_at) " +
                                "VALUES (:username, :fullName, :email, :phone, :pass, :role, :createAt)")
                        .bind("username", user.getUsername())
                        .bind("fullName", user.getFull_name())
                        .bind("email", user.getEmail())
                        .bind("phone", user.getPhone())
                        .bind("pass", user.getPassword_hash()) // Lưu ý: Nên mã hóa password trước khi truyền vào đây
                        .bind("role", user.getRole())
                        .bind("createAt", user.getCreate_at())
                        .execute()
        );
    }

    // 4. Update User (Admin cập nhật - bao gồm cả Role và Password nếu có)
    public void update(User user) {
        // Logic: Nếu password rỗng thì không update cột password
        String sql = "UPDATE users SET full_name = :fullName, email = :email, phone = :phone, role = :role ";
        if (user.getPassword_hash() != null && !user.getPassword_hash().isEmpty()) {
            sql += ", password_hash = :pass ";
        }
        sql += "WHERE user_id = :id";

        String finalSql = sql;
        get().useHandle(handle -> {
            var update = handle.createUpdate(finalSql)
                    .bind("fullName", user.getFull_name())
                    .bind("email", user.getEmail())
                    .bind("phone", user.getPhone())
                    .bind("role", user.getRole())
                    .bind("id", user.getUser_id());

            if (user.getPassword_hash() != null && !user.getPassword_hash().isEmpty()) {
                update.bind("pass", user.getPassword_hash());
            }
            update.execute();
        });
    }

    // 5. Xóa User
    public void delete(int userId) {
        get().useHandle(handle ->
                handle.createUpdate("DELETE FROM users WHERE user_id = :id")
                        .bind("id", userId)
                        .execute()
        );
    }

    // 6. Kiểm tra username tồn tại (để validate khi tạo mới)
    public boolean existsUsername(String username) {
        return get().withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM users WHERE username = :username")
                        .bind("username", username)
                        .mapTo(Integer.class)
                        .one() > 0
        );
    }
}