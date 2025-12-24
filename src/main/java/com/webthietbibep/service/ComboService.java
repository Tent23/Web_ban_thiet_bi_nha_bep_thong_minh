package com.webthietbibep.service;

import com.webthietbibep.dao.ComboDao;
import com.webthietbibep.model.Combo;

import java.util.List;

public class ComboService {
    ComboDao cdao = new ComboDao();

    public List<Combo> getListCombo() {
        return cdao.getListCombo();
    }
}
