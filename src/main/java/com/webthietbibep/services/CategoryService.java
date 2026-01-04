package com.webthietbibep.services;

import com.webthietbibep.dao.CategoryDAO;
import com.webthietbibep.model.Category;

import java.util.List;

public class CategoryService {
    CategoryDAO cDAO = new CategoryDAO();
    public List<Category> getAll() {
        return cDAO.getAll();
    }
}
