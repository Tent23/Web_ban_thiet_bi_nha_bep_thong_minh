package com.webthietbibep.dao;

import com.webthietbibep.model.Brand;

import java.util.List;

public class BrandDAO extends BaseDao{
    public List<Brand> getAll() {
        String sql = "SELECT * FROM brands";
        return get().withHandle(h ->
                h.createQuery(sql)
                        .mapToBean(Brand.class)
                        .list()
        );
    }

}
