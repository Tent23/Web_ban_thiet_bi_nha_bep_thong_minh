package com.webthietbibep.dao;

import com.webthietbibep.model.Brand;
import com.webthietbibep.model.Combo;
import com.webthietbibep.model.Product;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboDao extends BaseDao {
    static Map<Integer,Combo> data=new HashMap<>();
    static{
//        data.put(1,new Combo(1,"Combo Bếp Tiêu Chuẩn ", "Combo Căn hộ","Giải pháp cơ bản cho căn hộ nhỏ, đủ các thiết bị cần thiết.",30000000.00,25000000.00,"assets/images/banners/combocanho.png"," Bếp từ 2 vùng nấu Bosch", "Máy hút mùi âm tủ TTB","Robot hút bụi cơ bản","Tặng Lắp đặt Miễn phí));
//        data.put(2,new Combo(2,"Combo Bếp Luxury ","Combo Cao cấp","Trọn bộ thiết bị thông minh, tích hợp hệ sinh thái HomeKit/Google Home.",70000000.00,55000000.00,"assets/images/banners/combocaocap.png","Bếp từ 4 vùng nấu Hafele (có FlexZone)"," Máy rửa bát âm tủ Electrolux"," Tủ lạnh thông minh Samsung"," Tặng Gói Bảo hành Vàng 2 năm"));
//        data.put(3,new Combo(3,"Combo Cảm biến Bếp ","Combo An ninh","Đảm bảo an toàn tuyệt đối với hệ thống cảm biến thông minh",35500000.00,30000000.00,"assets/images/banners/comboanninh.png","Cảm biến Rò rỉ Gas","Cảm biến Báo khói/Nhiệt độ","Camera giám sát Wifi (Chịu nhiệt)","Tặng Ổ cắm thông minh Xiaomi"));
    }
    public List<Combo> getListCombo() {
        return get().withHandle(h->{
            return h.createQuery("select * from combos ").mapToBean(Combo.class).list();
        });
    }
    public void insert(List<Combo> combos){
        get().useHandle(h ->{
            PreparedBatch bacth = h.prepareBatch("insert into combos values (:id , :name ,:tag,:content,:baseprice,:discountprice, :image,:advance1,:advance2,:advance3,:gift)");
            combos.forEach(c -> {
                bacth.bindBean(c).add();
            });
            bacth.execute();
        });
    }



    public Combo getCombo(int id ) {
        return (Combo) get().withHandle(h->{
            return h.createQuery("select * from combos where id = :id ").bind("id",id).mapToBean(Combo.class).stream().findFirst().orElse(null);
        });
    }
    public List<Product> getListComboProduct(int id) {
        return get().withHandle(h ->{
            return h.createQuery("select p.* from products p join comboitems c on p.id = c.product_id where c.combo_id = :id ").bind("id",id).mapToBean(Product.class).list();

        });
    }
    public static void main(String[] args) {
        ComboDao dao = new ComboDao();
//        List<Combo> lisrC = new ArrayList<>(data.values());
//        dao.insert(lisrC);
        System.out.println(dao.getListComboProduct(1).size());
    }

}
