package com.webthietbibep.controller;

import com.webthietbibep.model.Combo;
import com.webthietbibep.service.ComboService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListComboContronller", value = "/listcombo")
public class ListComboContronller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComboService cp = new ComboService();

        List<Combo> lisrC = cp.getListCombo();

        request.setAttribute("listC", lisrC);
        request.getRequestDispatcher("giaiphapvacombo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}