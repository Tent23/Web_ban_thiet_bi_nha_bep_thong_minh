package com.webthietbibep.controller;

import com.webthietbibep.model.Article;
import com.webthietbibep.services.ArcticleService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DetailArticleontroller", value = "/detail-article")
public class DetailArticleontroller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id  = Integer.parseInt(request.getParameter("id"));
        ArcticleService a = new ArcticleService();
        Article art = a.getArticleById(id);
        List<Article> hot = a.getListHotArticle();
        request.setAttribute("hotarticle", hot);
        request.setAttribute("article", art);

        request.getRequestDispatcher("detail-article.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}