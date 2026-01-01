package com.webthietbibep.dao;

import com.webthietbibep.model.Category;
import java.util.List;

public class CategoryDAO extends BaseDao {
    // Lấy tất cả danh mục
    public List<Category> getAll() {
        return get().withHandle(h ->
                h.createQuery("SELECT * FROM categories")
                        .mapToBean(Category.class)
                        .list()
        );
    }
}