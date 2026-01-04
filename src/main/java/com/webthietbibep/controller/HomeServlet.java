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
        EcosystemService es = new EcosystemService();
        TesService ts = new TesService();
        BannerService bns = new BannerService();
        ComboService cs = new ComboService();
        CategoryService css = new CategoryService();
        ArcticleService as = new ArcticleService();

        // 1. Gọi DAO lấy danh sách
        List<Product> listP = ps.getBestSeller();
        List<Brand> listB = bs.getListBrand();
        List<Ecosystems> listE = es.getListEco();
        List<Testimonial> listT = ts.getListTes();
        List<Banner> listBN = bns.getListBanner();
        List<Combo> listC = cs.getListBaseCombo();
        List<Category> listCate = css.getAll();
        List<Article> listA = as.getNewArticle();
        // 2. Đẩy dữ liệu sang JSP
        request.setAttribute("listP", listP);
        request.setAttribute("listB", listB);
        request.setAttribute("listE", listE);
        request.setAttribute("listT", listT);
        request.setAttribute("listBN", listBN);
        request.setAttribute("listC", listC);
        request.setAttribute("listCate", listCate);
        request.setAttribute("listA", as.getNewArticle());
        // 3. Chuyển hướng về trang giao diện
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


}