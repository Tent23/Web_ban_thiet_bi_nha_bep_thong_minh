package com.webthietbibep.dao;

import com.webthietbibep.model.Order;

import java.util.List;

public class OrdersDAO extends BaseDao {
    public int insert(Order o) {
        String sql = """
        INSERT INTO orders
        (user_id, address_id, total_amount, status, payment_method, note)
        VALUES (:uid, :aid, :total, 'CHO_XAC_NHAN', :pm, :note)
    """;

        return get().withHandle(h ->
                h.createUpdate(sql)
                        .bind("uid", o.getUser_id())
                        .bind("aid", o.getAddress_id())
                        .bind("total", o.getTotal_amount())
                        .bind("pm", o.getPayment_method())
                        .bind("note", o.getNote())
                        .executeAndReturnGeneratedKeys("order_id")
                        .mapTo(int.class)
                        .one()
        );
    }


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
    public void cancelOrder(int orderId, int userId) {
        get().useHandle(h ->
                h.createUpdate("""
            UPDATE orders
            SET status = 'DA_HUY'
            WHERE order_id = :oid
              AND user_id = :uid
              AND status = 'CHO_XAC_NHAN'
        """)
                        .bind("oid", orderId)
                        .bind("uid", userId)
                        .execute()
        );
    }
    public List<Order> getOrdersByUserAndStatus(int userId, String status) {
        String sql = """
        SELECT *
        FROM orders
        WHERE user_id = :uid
          AND status = :status
        ORDER BY created_at DESC
    """;

        return get().withHandle(h ->
                h.createQuery(sql)
                        .bind("uid", userId)
                        .bind("status", status)
                        .mapToBean(Order.class)
                        .list()
        );
    }

}
