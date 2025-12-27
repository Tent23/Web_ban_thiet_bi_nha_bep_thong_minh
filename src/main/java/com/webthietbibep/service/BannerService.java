package com.webthietbibep.service;

import com.webthietbibep.dao.BannerDao;
import com.webthietbibep.model.Banner;

import java.util.List;

public class BannerService {
    BannerDao bdao = new BannerDao();

    public List<Banner> getListBanner() {
        return bdao.getListBanner();
    }
}
