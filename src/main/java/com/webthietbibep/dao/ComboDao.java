package com.webthietbibep.dao;

import com.webthietbibep.model.Combo;
import com.webthietbibep.model.ComboAdvance;
import com.webthietbibep.model.Product;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboDao extends BaseDao {

    public List<Combo> getListCombo() {
        return get().withHandle(h->{
            return h.createQuery("select * from combos where is_active = 1 ").mapToBean(Combo.class).list();
        });
    }




    public Combo getCombo(int id ) {
        return (Combo) get().withHandle(h->{
            return h.createQuery("select * from combos where id = :id ").bind("id",id).mapToBean(Combo.class).stream().findFirst().orElse(null);
        });
    }
    public List<ComboAdvance> getAdvanceCombo(int id) {
        return get().withHandle(h ->{
            return h.createQuery("select * from combo_advances where combo_id= :id ").bind("id",id).mapToBean(ComboAdvance.class).list();

        });
    }
    public List<Product> getListComboProduct(int id) {
        return get().withHandle(h ->{
            return h.createQuery("select p.product_id, p.category_id, p.name as product_name, p.description, p.price, p.stock_quantity, p.brand_id, p.image, p.create_at from products p join comboitems c on p.product_id = c.product_id where c.combo_id = :id ").bind("id",id).mapToBean(Product.class).list();

        });
    }


    public List<Combo> getListBaseCombo() {
        return get().withHandle(h->{
            return h.createQuery("select * from combos where is_active = 1 and tag in ('Combo Căn hộ','Combo An ninh') limit 2 ").mapToBean(Combo.class).list();
        });
    }

}
