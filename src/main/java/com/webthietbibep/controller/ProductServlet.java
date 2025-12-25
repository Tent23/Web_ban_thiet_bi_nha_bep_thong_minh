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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String priceRange = req.getParameter("priceRange");
        String sort = req.getParameter("sort");
        String[] brands = req.getParameterValues("brand");

        // ==== DAO QUERY ====
        List<Product> products = productDAO.getProductsFilter(priceRange, sort,brands);

        // ==== SET ATTRIBUTE ====
        req.setAttribute("products", products);
        req.setAttribute("brands", brands);
        req.setAttribute("priceRange", priceRange);
        req.setAttribute("sort", sort);
        req.setAttribute("brandList", brandDAO.getAllBrands());

        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}
