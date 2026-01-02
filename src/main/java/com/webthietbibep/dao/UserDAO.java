package com.webthietbibep.dao;

import com.webthietbibep.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO extends BaseDao {
    public User findById(int userId) {
        return get().withHandle(handle ->
                handle.createQuery("""
                    SELECT user_id, username, full_name, email, phone, password_hash, create_at, role
                    FROM users
                    WHERE user_id = :id
                """)
                        .bind("id", userId)
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null)
        );
    }

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
}