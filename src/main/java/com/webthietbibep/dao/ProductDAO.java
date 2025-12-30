package com.webthietbibep.dao;

import com.webthietbibep.model.Product;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.Arrays;
import java.util.List;

public class ProductDAO extends BaseDao {

    // 1. Lấy tất cả sản phẩm
    public List<Product> getListProduct() {
        return get().withHandle(h ->
                h.createQuery("SELECT * FROM products ORDER BY product_id DESC")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    // 2. Lấy 1 sản phẩm theo ID (Dùng cho trang Edit)
    public Product getProduct(int id) {
        return get().withHandle(h ->
                h.createQuery("SELECT * FROM products WHERE product_id = :id")
                        .bind("id", id)
                        .mapToBean(Product.class)
                        .findFirst()
                        .orElse(null)
        );
    }

    // 3. Insert một sản phẩm (Dùng cho Form Admin)
    public int insert(Product product) {
        String sql = """
            INSERT INTO products (categoryid, productname, description, price, stockquantity, brand_id, image, created_at)
            VALUES (:categoryid, :productname, :description, :price, :stockquantity, :brand_id, :image, NOW())
        """;

        return get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(product)
                        .execute()
        );
    }

    // 4. Insert danh sách nhiều sản phẩm (Bulk Insert)
    public void insert(List<Product> products) {
        String sql = """
            INSERT INTO products (categoryid, productname, description, price, stockquantity, image, brand_id, created_at)
            VALUES (:categoryid, :productname, :description, :price, :stockquantity, :image, :brand_id, NOW())
        """;

        get().useHandle(h -> {
            PreparedBatch batch = h.prepareBatch(sql);
            for (Product p : products) {
                batch.bindBean(p).add();
            }
            batch.execute();
        });
    }

    // 5. Cập nhật sản phẩm (Quan trọng: Đã sửa product_id)
    public int update(Product product) {
        String sql = """
            UPDATE products 
            SET categoryid = :categoryid,
                productname = :productname,
                description = :description,
                price = :price,
                stockquantity = :stockquantity,
                brand_id = :brand_id,
                image = :image
            WHERE product_id = :product_id
        """;
        // product_id (trái): tên cột DB
        // :product_id (phải): tên getter trong Model (getProduct_id)

        return get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(product)
                        .execute()
        );
    }

    // 6. Xóa sản phẩm
    public void delete(int id) {
        get().useHandle(handle ->
                handle.createUpdate("DELETE FROM products WHERE product_id = :id")
                        .bind("id", id)
                        .execute()
        );
    }

    // 7. Lọc sản phẩm (Filter) - Hàm bị thiếu trước đó
    public List<Product> getProductsFilter(
            String priceRange,
            String sort,
            String[] brands,
            Integer categoryId
    ) {
        StringBuilder sql = new StringBuilder("""
            SELECT
                p.product_id, 
                p.categoryid,
                p.productname,
                p.description,
                p.price,
                p.stockquantity,
                p.brand_id,
                p.image,
                p.created_at
            FROM products p
            LEFT JOIN brands b ON p.brand_id = b.brand_id
            WHERE 1=1
        """);

        // ===== CATEGORY =====
        if (categoryId != null && categoryId > 0) {
            sql.append(" AND p.categoryid = :categoryId");
        }

        // ===== PRICE =====
        if (priceRange != null) {
            switch (priceRange) {
                case "1" -> sql.append(" AND p.price < 5000000");
                case "2" -> sql.append(" AND p.price BETWEEN 5000000 AND 10000000");
                case "3" -> sql.append(" AND p.price BETWEEN 10000000 AND 20000000");
                case "4" -> sql.append(" AND p.price > 20000000");
            }
        }

        // ===== BRAND =====
        if (brands != null && brands.length > 0) {
            sql.append(" AND p.brand_id IN (<brands>)");
        }

        // ===== SORT =====
        if ("price_asc".equals(sort)) {
            sql.append(" ORDER BY p.price ASC");
        } else if ("price_desc".equals(sort)) {
            sql.append(" ORDER BY p.price DESC");
        } else {
            sql.append(" ORDER BY p.created_at DESC");
        }

        return get().withHandle(h -> {
            var query = h.createQuery(sql.toString());

            if (categoryId != null && categoryId > 0) {
                query.bind("categoryId", categoryId);
            }
            if (brands != null && brands.length > 0) {
                // Chuyển mảng String[] thành List để bind vào <brands>
                query.bindList("brands", Arrays.asList(brands));
            }

            return query.mapToBean(Product.class).list();
        });
    }
    public List<Product> getBestSellers() {

        return get().withHandle(h ->{
            return h.createQuery("           SELECT p.product_id, p.category_id as categoryid, p.name as productname,p.description,p.price,p.stock_quantity as stockquantity,p.brand_id,p.image,p.create_at, SUM(o.quantity) AS TongSoLuongDaBan\n" +
                            "                            FROM order_items o JOIN products p ON p.product_id = o.product_id\n" +
                            "                            GROUP BY p.product_id, p.category_id, p.name,p.description,p.price,p.stock_quantity,p.brand_id,p.image,p.create_at\n" +
                            "                            ORDER BY SUM(o.quantity) DESC\n" +
                            "                            LIMIT 2 ")
                    .mapToBean(Product.class).list();
        });
    }
}