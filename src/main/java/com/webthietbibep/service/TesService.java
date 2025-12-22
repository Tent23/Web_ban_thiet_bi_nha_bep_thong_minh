package com.webthietbibep.service;

import com.webthietbibep.dao.TesDao;
import com.webthietbibep.model.Testimonial;

import java.util.List;

public class TesService {
    TesDao tdao = new TesDao();

    public List<Testimonial> getListTes() {
        return tdao.getListes();
    }
}
