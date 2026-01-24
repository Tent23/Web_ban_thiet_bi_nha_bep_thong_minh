package com.webthietbibep.controller;

import com.webthietbibep.dao.BrandDAO;
import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Brand;

import com.webthietbibep.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand")
public class BrandServlet extends HttpServlet {

    private final BrandDAO brandDAO = new BrandDAO();
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int brandId = Integer.parseInt(req.getParameter("id"));
        Brand brand = brandDAO.getById(brandId);

        if (brand == null) {
            resp.sendError(404);
            return;
        }

        List<Product> products = productDAO.getByBrandId(brandId);

        req.setAttribute("brand", brand);
        req.setAttribute("products", products);
        req.getRequestDispatcher("brand.jsp").forward(req, resp);
    }
}

