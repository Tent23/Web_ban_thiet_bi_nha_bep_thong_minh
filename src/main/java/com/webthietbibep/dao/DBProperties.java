package com.webthietbibep.dao;

public class DBProperties {
    // 1. Thử đổi localhost thành 127.0.0.1 (Windows đôi khi bị lỗi với localhost)
    public static String host() { return "127.0.0.1"; }

    // 2. Kiểm tra XAMPP xem Port là 3306 hay số khác (ví dụ 3307, 8889)
    public static String port() { return "3306"; }

    // 3. QUAN TRỌNG: Tên database phải khớp 100% với tên bạn tạo trong MySQL
    public static String dbname() { return "web_nha_bep"; }

    public static String username() { return "root"; }

    public static String password() { return ""; }

    public static String option() { return "useUnicode=true&characterEncoding=UTF-8"; }
}