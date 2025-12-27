package com.webthietbibep.service;

import com.webthietbibep.dao.WarrantyDao;
import com.webthietbibep.model.Warranty;

import java.util.List;

public class WarrantyService {
    WarrantyDao wdao = new WarrantyDao();

    public List<Warranty> getSearchListWarranty(String type, String pra) {
        return wdao.getSearchListWarranty( type ,  pra);
    }
}
