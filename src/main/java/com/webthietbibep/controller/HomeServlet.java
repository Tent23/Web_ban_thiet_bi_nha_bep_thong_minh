package com.webthietbibep.controller;

import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Brand;
import com.webthietbibep.model.Product;

import com.webthietbibep.service.BrandService;
import com.webthietbibep.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/Home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        BrandService bs = new BrandService();

        // 1. Gọi DAO lấy danh sách
        List<Product> listP = ps.getBestSeller();
        List<Brand> listB = bs.getListBrand();

        // 2. Đẩy dữ liệu sang JSP
        request.setAttribute("listP", listP);
        request.setAttribute("listB", listB);

        // 3. Chuyển hướng về trang giao diện
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


}