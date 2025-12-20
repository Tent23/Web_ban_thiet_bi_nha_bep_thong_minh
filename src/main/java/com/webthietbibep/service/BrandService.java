package com.webthietbibep.service;

import com.webthietbibep.dao.BrandDao;
import com.webthietbibep.model.Brand;

import java.util.List;

public class BrandService {
    BrandDao bdao = new BrandDao();

    public List<Brand> getListBrand() {
        return bdao.getListBrand();
    }
}
