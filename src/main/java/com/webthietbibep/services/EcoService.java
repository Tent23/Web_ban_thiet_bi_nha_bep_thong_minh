package com.webthietbibep.services;

import com.webthietbibep.dao.EcoDao;
import com.webthietbibep.model.Ecosystems;

import java.util.List;

public class EcoService {
    EcoDao edao =  new EcoDao();
    public List<Ecosystems> getListEco() {
        return edao.getListEco();
    }

}
