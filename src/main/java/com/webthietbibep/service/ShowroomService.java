package com.webthietbibep.service;

import com.webthietbibep.dao.ShowroomDao;
import com.webthietbibep.model.Showroom;

import java.util.List;

public class ShowroomService {
    ShowroomDao sdao = new ShowroomDao();

    public List<Showroom> getListShowroom() {
        return sdao.getListShowroom();
    }
}
