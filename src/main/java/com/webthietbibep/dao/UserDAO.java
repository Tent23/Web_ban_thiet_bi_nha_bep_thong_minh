package com.webthietbibep.dao;

import com.webthietbibep.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

public class UserDAO extends BaseDao {

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

    // 3. Thêm mới User (Admin tạo) - Cập nhật để thêm các trường khóa công khai
    public void insert(User user) {
        get().useHandle(handle ->
                handle.createUpdate("INSERT INTO users (username, full_name, email, phone, password_hash, role, create_at, verify_token, is_verified, publicKey, publicKeyId, publicKeyCreationDate, publicKeyRevocationDate) " +
                                "VALUES (:username, :fullName, :email, :phone, :pass, :role, :createAt, :token, :verified, :publicKey, :publicKeyId, :publicKeyCreationDate, :publicKeyRevocationDate)")
                        .bind("username", user.getUsername())
                        .bind("fullName", user.getFull_name())
                        .bind("email", user.getEmail())
                        .bind("phone", user.getPhone())
                        .bind("pass", user.getPassword_hash())
                        .bind("role", user.getRole())
                        .bind("createAt", user.getCreate_at())
                        .bind("token", user.getVerify_token())
                        .bind("verified", user.isIs_verified())
                        .bind("publicKey", user.getPublicKey())
                        .bind("publicKeyId", user.getPublicKeyId())
                        .bind("publicKeyCreationDate", user.getPublicKeyCreationDate())
                        .bind("publicKeyRevocationDate", user.getPublicKeyRevocationDate())
                        .execute()
        );
    }

    // 4. Update User (Admin cập nhật - bao gồm cả Role và Password nếu có) - Cập nhật để thêm các trường khóa công khai
    public void update(User user) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE users SET full_name = :fullName, email = :email, phone = :phone, role = :role ");
        if (user.getPassword_hash() != null && !user.getPassword_hash().isEmpty()) {
            sqlBuilder.append(", password_hash = :pass ");
        }
        if (user.getPublicKey() != null && !user.getPublicKey().isEmpty()) {
            sqlBuilder.append(", publicKey = :publicKey ");
        }
        if (user.getPublicKeyId() != null && !user.getPublicKeyId().isEmpty()) {
            sqlBuilder.append(", publicKeyId = :publicKeyId ");
        }
        if (user.getPublicKeyCreationDate() != null) {
            sqlBuilder.append(", publicKeyCreationDate = :publicKeyCreationDate ");
        }
        // publicKeyRevocationDate can be explicitly set to null or a date
        sqlBuilder.append(", publicKeyRevocationDate = :publicKeyRevocationDate ");

        sqlBuilder.append("WHERE user_id = :id");

        String finalSql = sqlBuilder.toString();
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
            if (user.getPublicKey() != null && !user.getPublicKey().isEmpty()) {
                update.bind("publicKey", user.getPublicKey());
            }
            if (user.getPublicKeyId() != null && !user.getPublicKeyId().isEmpty()) {
                update.bind("publicKeyId", user.getPublicKeyId());
            }
            if (user.getPublicKeyCreationDate() != null) {
                update.bind("publicKeyCreationDate", user.getPublicKeyCreationDate());
            }
            update.bind("publicKeyRevocationDate", user.getPublicKeyRevocationDate()); // Bind even if null

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
        public User getById(int id) {
            String sql = "SELECT * FROM users WHERE user_id = :id";

            return get().withHandle(h ->
                    h.createQuery(sql)
                            .bind("id", id)
                            .mapToBean(User.class)
                            .findOne()
                            .orElse(null)
            );
        }
    public String getPasswordHashById(int userId) {
        return get().withHandle(handle ->
                handle.createQuery("SELECT password_hash FROM users WHERE user_id = :id")
                        .bind("id", userId)
                        .mapTo(String.class)
                        .one()
        );
    }
    public void updatePassword(int userId, String newPasswordHash) {
        get().useHandle(handle ->
                handle.createUpdate("""
                UPDATE users
                SET password_hash = :pass
                WHERE user_id = :id
            """)
                        .bind("pass", newPasswordHash)
                        .bind("id", userId)
                        .execute()
        );
    }

    public User findByToken(String token) {
        return get().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE verify_token = :token")
                        .bind("token", token)
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public void verifyUser(int userId) {
        get().useHandle(handle ->
                handle.createUpdate("""
                UPDATE users
                SET is_verified = TRUE,
                    verify_token = NULL
                WHERE user_id = :id
            """)
                        .bind("id", userId)
                        .execute()
        );
    }

    public boolean verifyByToken(String token) {
        return get().withHandle(handle ->
                handle.createUpdate("""
            UPDATE users
            SET is_verified = true, verify_token = NULL
            WHERE verify_token = :token
        """)
                        .bind("token", token)
                        .execute()
        ) > 0;
    }

    public void updatePublicKeyInfo(int userId, String publicKey, String publicKeyId, LocalDateTime publicKeyCreationDate, LocalDateTime publicKeyRevocationDate) {
        get().useHandle(handle ->
                handle.createUpdate("""
                    UPDATE users
                    SET publicKey = :publicKey,
                        publicKeyId = :publicKeyId,
                        publicKeyCreationDate = :publicKeyCreationDate,
                        publicKeyRevocationDate = :publicKeyRevocationDate
                    WHERE user_id = :id
                """)
                        .bind("publicKey", publicKey)
                        .bind("publicKeyId", publicKeyId)
                        .bind("publicKeyCreationDate", publicKeyCreationDate)
                        .bind("publicKeyRevocationDate", publicKeyRevocationDate)
                        .bind("id", userId)
                        .execute()
        );
    }

    public void revokePublicKey(int userId, LocalDateTime revocationDate) {
        get().useHandle(handle ->
                handle.createUpdate("""
                    UPDATE users
                    SET publicKeyRevocationDate = :revocationDate
                    WHERE user_id = :id
                """)
                        .bind("revocationDate", revocationDate)
                        .bind("id", userId)
                        .execute()
        );
    }
}
