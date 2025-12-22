package com.webthietbibep.dao;

import com.webthietbibep.model.Brand;
import com.webthietbibep.model.Showroom;
import com.webthietbibep.model.Testimonial;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowroomDao extends  BaseDao{
    static Map<Integer,Showroom> data =new HashMap<Integer,Showroom>();
    static{
        data.put(1,new Showroom(1,"Showroom tại Hà Nội","showroom-hn@malloca.com","https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d3724.7390780574183!2d105.85677627503074!3d21.003093780639905!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1zIDQxIFRoYW5oIE5ow6BuIC0gUS4gSGFpIELDoCBUcsawbmcgLSBUUC4gSMOgIE7hu5lpICjEkOG7kWkgZGnhu4duIEJWIFRoYW5oIE5ow6BuKQ!5e0!3m2!1svi!2s!4v1762965982993!5m2!1svi!2s\" width=\"100%\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade","1900.5679 (Kinh doanh)","Số 10 Chương Dương Độ","Phường Hồng Hà","","Thành phố Hà Nội","assets/images/banners/ShowroomHN1 .png","assets/images/banners/ShowroomHN2.png","assets/images/banners/ShowroomHN3 .png","08:00 - 21:00 (Tất cả các ngày)","Hà Nội là thủ đô của Việt Nam, đồng thời là thành phố đông dân thứ hai cả nước với hơn 8 triệu dân, chính vì vậy mà nhu cầu sử dụng thiết bị bếp ở nơi đây là không có gì để bàn cãi."));
        data.put(2,new Showroom(2,"Showroom tại TP.HCM","Showroom-q9@malloca.com","https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.447171783424!2d106.697334!3d10.776991!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f38f9ed8cb5%3A0x1c2250f14890501a!2zQ2jhu6MgQuG6v24gVGjDoG5o!5e0!3m2!1svi!2s!4v1678888888888!5m2!1svi!2s\" width=\"100%\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade","1900.5678 (Kinh doanh)","39 Nguyễn Cơ Thạch"," Phường An Khánh","","TP. Hồ Chí Minh","assets/images/banners/showroomHCM1 .png","assets/images/banners/showroomHCM2.png","assets/images/banners/showroomHCM3 .png"," 08:00 - 21:00 (Tất cả các ngày)","Thành phố Hồ Chí minh là thành phố nhộn nhịp và đông đúc nhất cả nước hiện nay với hơn 9 triệu dân. Sống trong một thành phố hoa lệ và phát triển nhất cả nước, chắc chắn nhu cầu của người tiêu dùng là rất lớn. Vậy nên không quá khó để bạn có thể tìm được những showroom thiết bị nhà bếp tại TP Hồ Chí Minh."));
    }
    public List<Showroom> getListShowroom() {
        return get().withHandle(h->{
            return h.createQuery("select * from showrooms ").mapToBean(Showroom.class).list();
        });
    }
    public void insert(List<Showroom> showrooms){
        get().useHandle(h ->{
            PreparedBatch bacth = h.prepareBatch("insert into showrooms values (:id , :name ,:email, :map_url,:phone,:street , :ward, :district , :city, :image1, :image2, :image3, :time , :content )");
            showrooms.forEach(s -> {
                bacth.bindBean(s).add();
            });
            bacth.execute();
        });
    }

    public static void main(String[] args) {
        ShowroomDao dao = new ShowroomDao();
        List<Showroom> showrooms = new ArrayList<>(data.values());
        dao.insert(showrooms);
    }

}
