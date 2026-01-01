package com.webthietbibep.controller;

import com.webthietbibep.model.Article;
import com.webthietbibep.services.ArcticleService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArcticleController", value = "/arcticle")
public class ArcticleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = request.getParameter("filter");
        ArcticleService as = new ArcticleService();
   if(filter == null) filter = "new";

        List<Article> listA = as.getFilterArticle(filter);
        List<Article> listHA = as.getListHotArticle();

        request.setAttribute("listA", listA);
        request.setAttribute("listHA", listHA);
        request.setAttribute("filter", filter);
        request.getRequestDispatcher("/goctuvan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}