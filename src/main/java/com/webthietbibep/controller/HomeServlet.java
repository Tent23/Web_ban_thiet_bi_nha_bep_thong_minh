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

@WebServlet(name = "HomeServlet", urlPatterns = {"/trang-chu", ""})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Gọi DAO lấy danh sách sản phẩm bán chạy
        ProductDAO dao = new ProductDAO();
        List<Product> listBestSellers = dao.getBestSellers();

        // 2. Đẩy dữ liệu sang JSP
        request.setAttribute("listP", listBestSellers);

        // 3. Chuyển hướng về trang giao diện
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
}