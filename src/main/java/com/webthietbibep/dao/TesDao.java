package com.webthietbibep.dao;

import com.webthietbibep.model.Testimonial;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesDao extends BaseDao {


    public List<Testimonial> getListes() {
        return get().withHandle(h->{
            return h.createQuery("select testimonial_id as id, content,author_name, author_loc as author_location, is_approved from testimonials ").mapToBean(Testimonial.class).list();
        });
    }


}
