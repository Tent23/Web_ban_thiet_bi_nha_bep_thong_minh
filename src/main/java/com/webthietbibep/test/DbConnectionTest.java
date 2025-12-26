package com.webthietbibep.test;

import org.jdbi.v3.core.Jdbi;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DbConnectionTest {

    public static void main(String[] args) {
        try {
            // 1. Load db.properties
            Properties props = new Properties();
            InputStream is = DbConnectionTest.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (is == null) {
                System.err.println("❌ Không tìm thấy file db.properties");
                return;
            }

            props.load(is);

            // 2. Tạo JDBC URL
            String url = "jdbc:mysql://" +
                    props.getProperty("db.host") + ":" +
                    props.getProperty("db.port") + "/" +
                    props.getProperty("db.dbname") + "?" +
                    props.getProperty("db.option");

            String user = props.getProperty("db.username");
            String pass = props.getProperty("db.password");

            // 3. Tạo JDBI
            Jdbi jdbi = Jdbi.create(url, user, pass);

            // 4. Test kết nối
            jdbi.useHandle(handle -> {
                Connection conn = handle.getConnection();
                System.out.println("✅ KẾT NỐI DATABASE THÀNH CÔNG");
                System.out.println("➡ URL: " + conn.getMetaData().getURL());
                System.out.println("➡ User: " + conn.getMetaData().getUserName());
            });

        } catch (Exception e) {
            System.err.println("❌ KẾT NỐI DATABASE THẤT BẠI");
            e.printStackTrace();
        }
    }
}
