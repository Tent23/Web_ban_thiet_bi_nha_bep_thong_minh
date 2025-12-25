package com.webthietbibep.dao;

import com.webthietbibep.model.Brand;
import com.webthietbibep.model.Product;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BrandDao extends BaseDao{
    public List<Brand> getAllBrands() {
        String sql = "SELECT * FROM brands";
        return get().withHandle(h ->
                h.createQuery(sql)
                        .mapToBean(Brand.class)
                        .list()
        );
    }
}
