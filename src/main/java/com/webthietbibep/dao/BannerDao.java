package com.webthietbibep.dao;

import com.webthietbibep.model.Banner;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerDao extends BaseDao {

    public List<Banner> getListBanner() {
        return  get().withHandle(h ->{
            return h.createQuery("select banner_id as id , title, description,image_url as image, link_url, sort_order, is_active from banners where is_active = 1 order by sort_order").mapToBean(Banner.class).list();
        });

    }


}
