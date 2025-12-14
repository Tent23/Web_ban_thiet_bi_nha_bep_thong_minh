package com.webthietbibep.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/web_nha_bep";
        String user = "root";
        String pass = "";
        System.out.println("Đang kiểm tra kết nối...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);
            System.out.println(" KẾT NỐI THÀNH CÔNG!");
            System.out.println("Database: " + connection.getCatalog());

            connection.close();

        } catch (ClassNotFoundException e) {
            System.err.println(" LỖI: Không tìm thấy thư viện MySQL Driver!");
            System.err.println("Bạn cần kiểm tra lại file pom.xml hoặc thư mục lib.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println(" LỖI: Không thể kết nối tới Database!");
            System.err.println("Nguyên nhân có thể: Sai tên DB, sai User/Pass, hoặc chưa bật MySQL Server.");
            System.err.println("Chi tiết lỗi: " + e.getMessage());
        }
    }
}