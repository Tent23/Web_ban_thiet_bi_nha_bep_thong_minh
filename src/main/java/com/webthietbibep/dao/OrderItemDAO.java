package com.webthietbibep.dao;


import com.webthietbibep.model.OrderItem;

import java.util.List;

public class OrderItemDAO extends BaseDao {

    public List<OrderItem> getByOrder(int orderId) {
        return get().withHandle(h ->
                h.createQuery("""
                SELECT order_id AS orderId,
                       product_id AS productId,
                       quantity,
                       price_at_purchase AS priceAtPurchase
                FROM order_items
                WHERE order_id = :id
            """)
                        .bind("id", orderId)
                        .mapToBean(OrderItem.class)
                        .list()
        );
    }
}

