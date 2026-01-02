package com.webthietbibep.dao;

import com.webthietbibep.model.Category;
import java.util.List;

public class CategoryDAO extends BaseDao {

    // 1. Lấy tất cả danh mục (Bao gồm cả logo)
    public List<Category> getAll() {
        // JDBI sẽ tự map cột 'category_id', 'category_name', 'logo' vào class Category
        String sql = "SELECT category_id, category_name, logo FROM categories";
        return get().withHandle(h ->
                h.createQuery(sql)
                        .mapToBean(Category.class)
                        .list()
        );
    }

    // 2. Lấy 1 danh mục theo ID (Dùng cho chức năng Sửa)
    public Category getCategory(int id) {
        String sql = "SELECT category_id, category_name, logo FROM categories WHERE category_id = :id";
        return get().withHandle(h ->
                h.createQuery(sql)
                        .bind("id", id)
                        .mapToBean(Category.class)
                        .findFirst()
                        .orElse(null)
        );
    }

    // 3. Thêm mới danh mục (Có thêm logo)
    public int insert(Category c) {
        String sql = "INSERT INTO categories (category_name, logo) VALUES (:category_name, :logo)";
        return get().withHandle(h ->
                h.createUpdate(sql)
                        .bind("category_name", c.getCategory_name())
                        .bind("logo", c.getLogo()) // Lưu đường dẫn ảnh
                        .execute()
        );
    }

    // 4. Cập nhật danh mục
    public int update(Category c) {
        String sql = "UPDATE categories SET category_name = :category_name, logo = :logo WHERE category_id = :category_id";
        return get().withHandle(h ->
                h.createUpdate(sql)
                        .bind("category_name", c.getCategory_name())
                        .bind("logo", c.getLogo()) // Cập nhật ảnh mới
                        .bind("category_id", c.getCategory_id())
                        .execute()
        );
    }

    // 5. Xóa danh mục
    public int delete(int id) {
        String sql = "DELETE FROM categories WHERE category_id = :id";
        return get().withHandle(h ->
                h.createUpdate(sql)
                        .bind("id", id)
                        .execute()
        );
    }
}