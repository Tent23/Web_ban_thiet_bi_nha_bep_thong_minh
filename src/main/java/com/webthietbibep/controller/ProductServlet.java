package com.webthietbibep.controller;

import com.webthietbibep.dao.BrandDao;
import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();
    private final BrandDao brandDAO = new BrandDao();
    private static final int PAGE_SIZE = 7;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String categoryIdStr = req.getParameter("categoryId");
        String priceRange = req.getParameter("priceRange");
        String sort = req.getParameter("sort");
        String[] brands = req.getParameterValues("brand");
        String pageStr = req.getParameter("page");

        Integer categoryId = null;
        if (categoryIdStr != null && !categoryIdStr.isBlank()) {
            categoryId = Integer.parseInt(categoryIdStr);
        }
        int page =1;
        if(pageStr!=null){
            page = Integer.parseInt(pageStr);
        }

        List<Product> products = productDAO.getProductsFilter(priceRange, sort,brands,categoryId,page,PAGE_SIZE);

        int totalProducts = productDAO.countProducts(priceRange, brands, categoryId);
        int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
        req.setAttribute("categoryId", categoryId);
        req.setAttribute("products", products);
        req.setAttribute("brands", brands);
        req.setAttribute("priceRange", priceRange);
        req.setAttribute("sort", sort);
        req.setAttribute("brandList", brandDAO.getAllBrands());
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}
