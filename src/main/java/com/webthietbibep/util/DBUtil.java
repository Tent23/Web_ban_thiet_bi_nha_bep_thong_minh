package com.webthietbibep.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBUtil {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = DBUtil.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            props.load(is);
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://"
                    + props.getProperty("db.host") + ":"
                    + props.getProperty("db.port") + "/"
                    + props.getProperty("db.dbname") + "?"
                    + props.getProperty("db.option");

            return DriverManager.getConnection(
                    url,
                    props.getProperty("db.username"),
                    props.getProperty("db.password")
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
