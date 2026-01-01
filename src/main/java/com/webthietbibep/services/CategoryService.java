package com.webthietbibep.services;

import com.webthietbibep.dao.CategoryDAO;
import com.webthietbibep.model.Category;

import java.util.List;

public class CategoryService {
    CategoryDAO cd = new CategoryDAO();
    public List<Category> getALL(){
        return cd.getAll();
    }
}
