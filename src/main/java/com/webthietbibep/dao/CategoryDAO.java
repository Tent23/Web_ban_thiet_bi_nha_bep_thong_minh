package com.webthietbibep.dao;

import com.webthietbibep.model.Category;
import java.util.List;

public class CategoryDAO extends BaseDao {

    public List<Category> getAll() {
        String sql = "SELECT category_id, category_name, logo FROM categories";
        return get().withHandle(h ->
                h.createQuery(sql).mapToBean(Category.class).list()
        );
    }

    public List<Category> getTopCategories() {
        String sql = """
        SELECT c.category_id, c.category_name, c.logo, SUM(o.quantity) total_sold
        FROM order_items o
        JOIN products p ON p.product_id = o.product_id
        JOIN categories c ON c.category_id = p.category_id
        GROUP BY c.category_id
        ORDER BY total_sold DESC
        LIMIT 4
    """;

        return get().withHandle(h ->
                h.createQuery(sql).mapToBean(Category.class).list()
        );
    }

    public Category getCategory(int id) {
        String sql = "SELECT category_id, category_name, logo FROM categories WHERE category_id = :id";
        return get().withHandle(h ->
                h.createQuery(sql)
                        .bind("id", id)
                        .mapToBean(Category.class)
                        .findFirst().orElse(null)
        );
    }

    public int insert(Category c) {
        String sql = "INSERT INTO categories (category_name, logo) VALUES (:name, :logo)";
        return get().withHandle(h ->
                h.createUpdate(sql)
                        .bind("name", c.getCategory_name())
                        .bind("logo", c.getLogo())
                        .execute()
        );
    }

    public int update(Category c) {
        String sql = "UPDATE categories SET category_name = :name, logo = :logo WHERE category_id = :id";
        return get().withHandle(h ->
                h.createUpdate(sql)
                        .bind("name", c.getCategory_name())
                        .bind("logo", c.getLogo())
                        .bind("id", c.getCategory_id())
                        .execute()
        );
    }

    public int delete(int id) {
        String sql = "DELETE FROM categories WHERE category_id = :id";
        return get().withHandle(h ->
                h.createUpdate(sql).bind("id", id).execute()
        );
    }

    public boolean checkExist(int id){
        String sql = "SELECT COUNT(*) FROM categories WHERE category_id = :id";
        return get().withHandle(h->{
           return h.createQuery(sql).bind("id", id).mapTo(Integer.class).one() > 0;
        });
    }
}