package com.webthietbibep.dao;

import com.webthietbibep.model.Warranty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarrantyDao extends BaseDao {
    static Map<Integer,Warranty> data = new HashMap<>();

    public List<Warranty> getSearchListWarranty(String type , String pra) {
        return get().withHandle(h ->{
            if(type == null){
                return new ArrayList<>();
            }
            else if(type.equals("phone")) {
                return h.createQuery("select w.series , p.product_name as productName,w.product_id as productId,u.full_name as customerName, w.customer_id as customerId,w.order_id as orderId , u.phone as phoneNumber , w.purchase_date as purchaseDate, w.warranty_time  \n" +
                        "                        from warranties w join users u on u.user_id = w.customer_id \n" +
                        "                                                 join products p on p.product_id = w.product_id\n" +
                        "                                                 \n" +
                        "                        where u.phone = :pra; ").bind("pra", pra.trim()).mapToBean(Warranty.class).list();
            }
            else if(type.equals("customer")) {
                return h.createQuery("select w.series , p.product_name as productName,w.product_id as productId,u.full_name as customerName, w.customer_id as customerId,w.order_id as orderId , u.phone as phoneNumber , w.purchase_date as purchaseDate, w.warranty_time  \n" +
                        "from warranties w join users u on u.user_id = w.customer_id \n" +
                        "join products p on p.product_id = w.product_id\n" +
                        "\n" +
                        "where w.customer_id = :pra;").bind("pra",pra.trim()).mapToBean(Warranty.class).list();
            }
            else{

                return h.createQuery("select w.series , p.product_name as productName,w.product_id as productId,u.full_name as customerName, w.customer_id as customerId,w.order_id as orderId , u.phone as phoneNumber , w.purchase_date as purchaseDate, w.warranty_time  \n" +
                        "from warranties w join users u on u.user_id = w.customer_id \n" +
                        "join products p on p.product_id = w.product_id\n" +
                        "\n" +
                        "where w.series = :pra;").bind("pra",pra.trim()).mapToBean(Warranty.class).list();
            }
        });
    }
}
