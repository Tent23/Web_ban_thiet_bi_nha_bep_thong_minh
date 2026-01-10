package com.webthietbibep.dao;


import com.webthietbibep.model.OrderItem;

import java.util.List;

public class OrderItemDAO extends BaseDao {

    public List<OrderItem> getByOrder(int orderId) {
        return get().withHandle(h ->
                h.createQuery("""
                SELECT order_id,
                       product_id,
                       quantity,
                       price_at_purchase
                FROM order_items
                WHERE order_id = :id
            """)
                        .bind("id", orderId)
                        .mapToBean(OrderItem.class)
                        .list()
        );
    }
}

