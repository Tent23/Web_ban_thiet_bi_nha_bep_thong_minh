package com.webthietbibep.dao;

import com.webthietbibep.db.JDBIConnector;
import com.webthietbibep.model.ProductFeature;

import java.util.List;

public class ProductFeatureDAO {

    public List<ProductFeature> getByProductId(int productId) {
        return JDBIConnector.get().withHandle(handle ->
                handle.createQuery(
                                "SELECT * FROM product_features WHERE product_id = :pid"
                        )
                        .bind("pid", productId)
                        .map((rs, ctx) -> {
                            ProductFeature f = new ProductFeature();
                            f.setFeatureId(rs.getInt("feature_id"));
                            f.setProductId(rs.getInt("product_id"));
                            f.setFeatureText(rs.getString("feature_text"));
                            return f;
                        })
                        .list()
        );
    }
}
