package com.webthietbibep.context;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBContext {

    public Connection getConnection() {
        try {
            // 1. Đọc file properties
            Properties props = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties");

            if (input == null) {
                // Fallback nếu không tìm thấy file properties
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/web_nha_bep", "root", "");
            }
            props.load(input);

            // 2. Lấy thông tin
            String url = "jdbc:mysql://" + props.getProperty("db.host") + ":"
                    + props.getProperty("db.port") + "/"
                    + props.getProperty("db.dbname") + "?"
                    + props.getProperty("db.option");

            String user = props.getProperty("db.username");
            String pass = props.getProperty("db.password");

            // 3. Kết nối
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = new DBContext().getConnection();
        if(conn != null) System.out.println("Kết nối MySQL thành công!");
        else System.out.println("Kết nối thất bại!");
    }
}