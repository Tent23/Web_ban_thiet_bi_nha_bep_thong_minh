package com.webthietbibep.controller;

import com.webthietbibep.dao.CategoryDAO;
import com.webthietbibep.dao.ComboDAO;
import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Category;
import com.webthietbibep.model.Combo;
import com.webthietbibep.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("categories", CategoryDAO.getAll());
     //   request.setAttribute("bestProducts", ProductDAO.getBestSeller());
        request.setAttribute("combos", ComboDAO.getAll());
        List<Product> products = ProductDAO.getAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }

}
