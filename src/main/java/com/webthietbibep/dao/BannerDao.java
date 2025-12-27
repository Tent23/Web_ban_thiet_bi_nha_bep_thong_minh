package com.webthietbibep.dao;

import com.webthietbibep.model.Banner;
import com.webthietbibep.model.Brand;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerDao extends BaseDao {
 static Map<Integer, Banner> data = new HashMap<Integer,Banner>();
 static{
     data.put(1,new Banner(1,"Nâng Tầm Trải Nghiệm Bếp","Khám phá các giải pháp nhà bếp thông minh, tự động hóa toàn diện.","assets/images/banners/banner3.png", (byte) 1));
     data.put(2,new Banner(2,"An Toàn & Tiện Lợi","Hệ thống cảm biến và điều khiển từ xa.","assets/images/banners/banner4.jpg", (byte) 1));
     data.put(3,new Banner(3,"Tết Sum Vầy - Bếp Đong Đầy", "Ưu đãi lên đến 50% trọn bộ thiết bị bếp cao cấp đón Xuân.","assets/images/banners/banner2.jpg", (byte) 1));
 }
    public void insert(List<Banner> banners){
        get().useHandle(h ->{
            PreparedBatch bacth = h.prepareBatch("insert into banners values (:id , :title, :description, :image,:isActive)");
            banners.forEach(brand -> {
                bacth.bindBean(brand).add();
            });
            bacth.execute();
        });
    }
    public List<Banner> getListBanner() {
        return  get().withHandle(h ->{
            return h.createQuery("select * from banners where isActive = 1").mapToBean(Banner.class).list();
        });

    }

//    public static void main(String[] args) {
//        BannerDao dao = new BannerDao();
//        List<Banner> l = new ArrayList<>(data.values());
//        dao.insert(l);
//    }
}
