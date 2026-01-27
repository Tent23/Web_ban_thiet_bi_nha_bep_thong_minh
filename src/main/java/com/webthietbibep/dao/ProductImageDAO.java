package com.webthietbibep.dao;

import com.webthietbibep.model.ProductImage;

import java.util.List;

public class ProductImageDAO extends BaseDao {

    public List<ProductImage> getByProductId(int productId) {
        String sql = """
            SELECT * FROM product_images
            WHERE product_id = :pid
            ORDER BY sort_order ASC
        """;

        return get().withHandle(h ->
                h.createQuery(sql)
                        .bind("pid", productId)
                        .mapToBean(ProductImage.class)
                        .list()
        );
    }
}
