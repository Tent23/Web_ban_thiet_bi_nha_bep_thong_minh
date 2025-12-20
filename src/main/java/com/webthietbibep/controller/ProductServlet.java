package com.webthietbibep.controller;

import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"/products"})
public class ProductServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        System.out.println("=== PRODUCT SERVLET CALLED ===");
//
//        List<Product> products = ProductDAO.getAll();
//        System.out.println("PRODUCT SIZE = " + products.size());
//
//        request.setAttribute("products", products);
//        request.getRequestDispatcher("/products.jsp").forward(request, response);
//    }
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {

    response.setContentType("text/plain");
    response.getWriter().println("HELLO PRODUCT SERVLET");
}


}
