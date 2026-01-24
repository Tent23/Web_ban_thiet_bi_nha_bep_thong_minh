package com.webthietbibep.controller;

import com.webthietbibep.model.Ecosystems;
import com.webthietbibep.services.EcoService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EcosystemServlet", value = "/ecos-list")
public class EcosystemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       EcoService es = new EcoService();
       List<Ecosystems> listE = es.getListEco();
       request.setAttribute("listE", listE);
       request.getRequestDispatcher("Ecosystem.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}