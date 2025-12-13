package com.webthietbibep.controller;

import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/index")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Gọi DAO lấy danh sách sản phẩm bán chạy
        ProductDAO dao = new ProductDAO();
        List<Product> listBestSellers = dao.getBestSellers();

        // 2. Đẩy dữ liệu sang JSP
        request.setAttribute("listP", listBestSellers);

        // 3. Chuyển hướng về trang giao diện
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}