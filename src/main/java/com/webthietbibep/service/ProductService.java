package com.webthietbibep.service;

import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Product;

import java.util.List;

public class ProductService {
    ProductDAO pdao = new ProductDAO();


    public List<Product> getBestSeller() {
        return pdao.getBestSellers();
    }

    public List<Product> getListProduct() {
        return pdao.getListProduct();
    }
}
