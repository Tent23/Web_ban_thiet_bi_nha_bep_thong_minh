package com.webthietbibep.dao;

import com.webthietbibep.model.Brand;
import com.webthietbibep.model.Product;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrandDao extends BaseDao{
    static Map<Integer, Brand> data = new HashMap<>();
    static{
        data.put(1, new Brand(1,"Xiaomi","https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Xiaomi_logo_%282021-%29.svg/2048px-Xiaomi_logo_%282021-%29.svg.png"));
        data.put(2, new Brand(2,"Sunhouse","https://www.sonicpost.net/template/sonic/css/images/oho.png"));
        data.put(3, new Brand(3,"Mitsubishi","assets/images/products/LogoMitsubishi.jpg"));
        data.put(4, new Brand(4,"Tesla","assets/images/products/logoTesla.png"));
        data.put(5, new Brand(5,"Panasonic","assets/images/products/LogoPanasonic.jpg"));



    }
    public void insert(List<Brand> brands){
        get().useHandle(h ->{
            PreparedBatch bacth = h.prepareBatch("insert into brands values (:id , :name , :image)");
            brands.forEach(brand -> {
                bacth.bindBean(brand).add();
            });
            bacth.execute();
        });
    }

    public List<Brand> getListBrand() {
      return  get().withHandle(h ->{
            return h.createQuery("select * from brands").mapToBean(Brand.class).list();
        });

    }

    public static void main(String[] args) {
        BrandDao dao = new BrandDao();
        List<Brand> brands = new ArrayList<>(data.values());
        dao.insert(brands);
    }
}
