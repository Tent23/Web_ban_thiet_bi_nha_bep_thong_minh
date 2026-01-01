package com.webthietbibep.controller;

import com.webthietbibep.model.*;

import com.webthietbibep.services.*;
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
        EcoService es = new EcoService();
        TesService ts = new TesService();
        BannerService bns = new BannerService();
        ComboService cs = new ComboService();

        // 1. Gọi DAO lấy danh sách
        List<Product> listP = ps.getBestSeller();
        List<Brand> listB = bs.getListBrand();
        List<Ecosystems> listE = es.getListEco();
        List<Testimonial> listT = ts.getListTes();
        List<Banner> listBN = bns.getListBanner();
        List<Combo> listC = cs.getBaseCombo();
        // 2. Đẩy dữ liệu sang JSP
        request.setAttribute("listP", listP);
        request.setAttribute("listB", listB);
        request.setAttribute("listE", listE);
        request.setAttribute("listT", listT);
        request.setAttribute("listBN", listBN);
        request.setAttribute("listC", listC);

        // 3. Chuyển hướng về trang giao diện
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


}