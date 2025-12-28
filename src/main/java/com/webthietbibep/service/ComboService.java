package com.webthietbibep.service;

import com.webthietbibep.dao.ComboDao;
import com.webthietbibep.model.Combo;
import com.webthietbibep.model.Product;

import java.util.List;

public class ComboService {
    ComboDao cdao = new ComboDao();

    public List<Combo> getListCombo() {
        return cdao.getListCombo();
    }

    public Combo getCombo(int id) {
        return cdao.getCombo(id);
    }

    public List<Product> getListComboProduct(int id) {
        return cdao.getListComboProduct(id);
    }
}
