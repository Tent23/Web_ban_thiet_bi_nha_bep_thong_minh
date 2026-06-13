package com.webthietbibep.dao;

import com.webthietbibep.db.JDBIConnector; // Import connector của bạn vào
import org.jdbi.v3.core.Jdbi;

public abstract class BaseDao {

    // Đổi hàm get() để lấy trực tiếp từ JDBIConnector
    protected Jdbi get(){
        return JDBIConnector.get();
    }
}