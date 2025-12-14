package com.webthietbibep.dao;

import com.webthietbibep.model.Product;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.JdbiException;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO extends BaseDao{
    static Map<Integer,Product> data = new HashMap<>();

    static {
    data.put(1,new Product(1,"Bep","xxx",500.0,"assets/images/products/beptu-1.jpg",1,1,"c"));

    }

    public void insertProduct(List<Product> products){
        Jdbi jdbi = get();
        jdbi.useHandle(h -> {
            PreparedBatch batch = h.prepareBatch("insert into products values(?,?,?,?)");
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
        return new ArrayList<>(data.values());
    }
}