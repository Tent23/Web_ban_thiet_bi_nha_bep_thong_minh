package com.webthietbibep.dao;

import com.webthietbibep.model.Ecosystems;
import com.webthietbibep.model.Product;
import com.webthietbibep.model.Testimonial;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesDao extends BaseDao {
    static Map<Integer,Testimonial> data = new HashMap<Integer,Testimonial>();
    static{
        data.put(1,new Testimonial(1,"Dịch vụ lắp đặt rất chuyên nghiệp các bạn kỹ thuật viên hộ trợ cài app rất nhiệt tình.","Chị An","Quận 7",true));
        data.put(2,new Testimonial(2,"Sản phẩm chính hãng, dùng rất ưng ý. Sẽ tiếp tục ủng hộ shop. ","Anh Bách","Hà Nội ",true));
    }
    public void insertTes(List<Testimonial> tess){

        get().useHandle(h -> {
            PreparedBatch batch = h.prepareBatch("insert into testimonials values (:id,:content,:author_name, :author_location,:is_approved)");
            tess.forEach(tes -> {
                batch.bindBean(tes).add();
            });
            batch.execute();
        });
    }
    public List<Testimonial> getListes() {
        return get().withHandle(h->{
            return h.createQuery("select * from testimonials ").mapToBean(Testimonial.class).list();
        });
    }

    public static void main(String[] args) {
        TesDao dao = new TesDao();
        List<Testimonial> testimonials = new ArrayList<Testimonial>(data.values());
        dao.insertTes(testimonials);
    }
}
