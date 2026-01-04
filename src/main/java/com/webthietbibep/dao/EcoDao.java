package com.webthietbibep.dao;

import com.webthietbibep.model.Ecosystems;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcoDao extends BaseDao {


    public List<Ecosystems> getListEco() {
        return get().withHandle(h->{
            return h.createQuery("select * from ecosystems").mapToBean(Ecosystems.class).list();
        });
    }


}
