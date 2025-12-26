package com.webthietbibep.util;

import org.jdbi.v3.core.Jdbi;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;

public class JdbiConnector {
    private static Jdbi jdbi;

    // Phương thức get() mà trình biên dịch đang báo thiếu
    public static Jdbi get() {
        if (jdbi == null) {
            makeConnect();
        }
        return jdbi;
    }

    private static void makeConnect() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost:3306/web_nha_bep");
            dataSource.setUser("root");
            dataSource.setPassword("");
            dataSource.setUseSSL(false);
            dataSource.setAllowPublicKeyRetrieval(true);

            jdbi = Jdbi.create(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể kết nối đến Database!");
        }
    }
}