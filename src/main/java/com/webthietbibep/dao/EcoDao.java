package com.webthietbibep.dao;

import com.webthietbibep.model.Ecosystems;
import com.webthietbibep.model.Product;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcoDao extends BaseDao {
   static Map<Integer,Ecosystems> data = new HashMap<Integer,Ecosystems>();
    static{
        data.put(1,new Ecosystems(1,"Google Home","https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/1024px-Google_%22G%22_logo.svg.png"));
        data.put(2,new Ecosystems(2,"Apple HomeKit","https://diendantructuyen.com/wp-content/uploads/2024/09/apple-logo-vector.jpg"));
        data.put(3,new Ecosystems(3,"Amazon Alexa","https://images.icon-icons.com/1195/PNG/512/1490889698-amazon_82521.png"));
        data.put(4,new Ecosystems(4,"Mi Home","https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Xiaomi_logo_%282021-%29.svg/2048px-Xiaomi_logo_%282021-%29.svg.png"));
    }
    public void insertEco(List<Ecosystems> ecos){

        get().useHandle(h -> {
            PreparedBatch batch = h.prepareBatch("insert into ecosystems values (:id , :name , :image);");
            ecos.forEach(e -> {
                batch.bindBean(e).add();
            });
            batch.execute();
        });
    }
    public List<Ecosystems> getListEco() {
        return get().withHandle(h->{
            return h.createQuery("select * from ecosystems").mapToBean(Ecosystems.class).list();
        });
    }

    public static void main(String[] args) {
        EcoDao dao = new EcoDao();
        List<Ecosystems> ecos = new ArrayList<>(data.values());
        dao.insertEco(ecos);
    }
}
