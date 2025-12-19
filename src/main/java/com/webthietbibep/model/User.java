package com.webthietbibep.model;

import java.time.LocalDateTime;

public class User {
    int user_id;
    String username;
    String full_name;
    String email;
    String phone;
    String password_hash;
    LocalDateTime create_at;

    public User(int user_id,String username, String full_name, String email, String phone, String password_hash, LocalDateTime create_at) {
        this.user_id = user_id;
        this.username = username;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.password_hash = password_hash;
        this.create_at = create_at;
    }

    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
