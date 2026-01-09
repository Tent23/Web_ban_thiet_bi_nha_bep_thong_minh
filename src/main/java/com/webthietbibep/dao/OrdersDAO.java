package com.webthietbibep.dao;

import com.webthietbibep.model.Order;

import java.util.List;

public class OrdersDAO extends BaseDao {

    public List<Order> getOrdersByUser(int userId) {
        String sql = """
            SELECT 
                order_id,
                user_id,
                address_id,
                total_amount,
                status,
                payment_method,
                created_at,
                note,
                voucher_id
            FROM orders
            WHERE user_id = :uid
            ORDER BY created_at DESC
        """;

        return get().withHandle(h ->
                h.createQuery(sql)
                        .bind("uid", userId)
                        .mapToBean(Order.class)
                        .list()
        );
    }
}
