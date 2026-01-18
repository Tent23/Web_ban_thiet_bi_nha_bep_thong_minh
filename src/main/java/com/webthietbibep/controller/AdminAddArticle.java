package com.webthietbibep.controller;

import com.webthietbibep.model.Article;
import com.webthietbibep.services.ArcticleService;
import com.webthietbibep.services.CategoryService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@WebServlet(name = "AdminAddArticle", value = "/admin/add-article")
public class AdminAddArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/admin_content_form.jsp").forward(request, response);
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String tip = request.getParameter("tip");
        String summary = request.getParameter("summary");
        String body = request.getParameter("body");
        String image = request.getParameter("image");
        String is_active = request.getParameter("is_active");
        String cateRaw = request.getParameter("cate");

        Article a = new Article();
        a.setTitle(title);
        a.setAuthor(author);
        a.setTip(tip);
        a.setContent(summary);
        a.setBody(body);
        a.setImage(image);

        int cateId = (cateRaw != null && !cateRaw.isEmpty()) ? Integer.parseInt(cateRaw) : 0;
        a.setCategory_id(cateId);
        a.setIs_active((byte) ("1".equals(is_active) ? 1 : 0));

        // 3. Kiểm tra ID tồn tại
        CategoryService cs = new CategoryService();
        boolean check = cs.checkExist(cateId);

        if (check) {
            // Nếu OK: Set ngày và Lưu
            a.setCreate_date(new java.sql.Date(System.currentTimeMillis()).toLocalDate());
            if (author != null) a.setAuthor(author.toUpperCase());

            ArcticleService as = new ArcticleService();
            as.addArticle(a);
            response.sendRedirect(request.getContextPath() + "/admin/content");
        } else {

            request.setAttribute("oldArticle", a);
            request.setAttribute("errorMessage", "ID danh mục :" + cateId + " không tồn tại!");


            request.getRequestDispatcher("/admin/admin_content_form.jsp").forward(request, response);
        }
    }
}