package com.webthietbibep.dao;

import com.webthietbibep.model.Product;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO extends BaseDao{
    static Map<Integer,Product> data = new HashMap<>();

    static {
    data.put(1,new Product(1,"Bếp từ Bosch PXY875","xxx",25000000,"assets/images/products/beptu-1.jpg",1,1));
    data.put(2,new Product(2,"Robot Xiaomi Vacuum X10","xxx",12500000,"assets/images/products/robot-1.jpg",1,1));
        data.put(3,new Product(3,"Bếp từ đôi Chefs","xxx",8900000,"assets/images/products/beptu-2.jpg",1,1));
    }

    public void insertProduct(List<Product> products){

        get().useHandle(h -> {
            PreparedBatch batch = h.prepareBatch("insert into products values(:id , :name,:description,:price,:image,:category_id,:brand_id)");
           products.forEach(product -> {
               batch.bindBean(product).add();
           });
           batch.execute();
        });
    }

    public List<Product> getBestSellers() {

        return get().withHandle(h ->{
            return h.createQuery("SELECT p.*, SUM(o.quantity) AS TongSoLuongDaBan\n" +
                            " FROM orderitems o JOIN products p ON p.id = o.product_id\n" +
                            "GROUP BY p.id,p.name,p.description,p.price,p.image,p.category_id,p.brand_id\n" +
                            "ORDER BY SUM(o.quantity) DESC\n" +
                            "LIMIT 2 ")
                   .mapToBean(Product.class).list();
        });
    }

    public List<Product> getListProduct() {
       return get().withHandle(h ->{
           return h.createQuery("select * from products ").mapToBean(Product.class).list();

        });
    }

    public Product getProduct(int id ){
        return get().withHandle(h ->{
            return h.createQuery("select * from products where id = :id ").bind("id",id).mapToBean(Product.class).stream().findFirst().orElse(null);

        });
    }



}