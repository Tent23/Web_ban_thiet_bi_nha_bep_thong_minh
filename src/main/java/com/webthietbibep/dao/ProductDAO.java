package com.webthietbibep.dao;

import com.webthietbibep.model.Product;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO extends BaseDao{
    static Map<Integer,Product> data = new HashMap<>();

    static {
    data.put(1,new Product(1,"Bếp từ Bosch PXY875","xxx",25000000,"assets/images/products/beptu-1.jpg",1,1,"c"));
    data.put(2,new Product(2,"Robot Xiaomi Vacuum X10","xxx",12500000,"assets/images/products/robot-1.jpg",1,1,"c"));
        data.put(3,new Product(3,"Bếp từ đôi Chefs","xxx",8900000,"assets/images/products/beptu-2.jpg",1,1,"c"));
    }

    public void insertProduct(List<Product> products){

        get().useHandle(h -> {
            PreparedBatch batch = h.prepareBatch("insert into products values(:id , :name,:description,:price,:image,:category_id,:brand_id,:slug)");
           products.forEach(product -> {
               batch.bindBean(product).add();
           });
           batch.execute();
        });
    }

    public List<Product> getBestSellers() {

        return null;
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

//    public static void main(String[] args) {
//        ProductDAO dao = new ProductDAO();
//        List<Product> list = dao.getListProduct();
//        dao.insertProduct(list);
//    }
}