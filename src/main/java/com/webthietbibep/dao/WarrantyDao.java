package com.webthietbibep.dao;

import com.webthietbibep.model.Product;
import com.webthietbibep.model.Warranty;
import org.jdbi.v3.core.statement.PreparedBatch;

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
                return h.createQuery("select w.series , p.name as productName,w.product_id as productId,u.name as customerName, w.customer_id as customerId,w.order_id as orderId , u.sdt as phoneNumber , w.puchase_date as purchaseDate, w.wrranty_date as warrantyMounth  \n" +
                        "from waranties w join users u on u.id = w.customer_id \n" +
                        "                          join products p on p.id = w.product_id\n" +
                        "                          \n" +
                        "where u.sdt = :pra     ").bind("pra", pra.trim()).mapToBean(Warranty.class).list();
            }
            else{

                return h.createQuery("select w.series , p.name as productName,w.product_id as productId,u.name as customerName, w.customer_id as customerId,w.order_id as orderId , u.sdt as phoneNumber , w.puchase_date as purchaseDate, w.wrranty_date as warrantyMounth  \n" +
                        "from waranties w join users u on u.id = w.customer_id \n" +
                        "                          join products p on p.id = w.product_id\n" +
                        "                          \n" +
                        "where w.series = :pra    ").bind("pra",pra.trim()).mapToBean(Warranty.class).list();
            }
        });
    }
}
