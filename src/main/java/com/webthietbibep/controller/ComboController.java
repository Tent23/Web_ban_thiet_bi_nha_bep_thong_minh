package com.webthietbibep.controller;

import com.webthietbibep.model.Combo;
import com.webthietbibep.model.Product;
import com.webthietbibep.service.ComboService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ComboController ", value = "/combo")
public class ComboController extends HttpServlet {
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =  Integer.parseInt(request.getParameter("id"));
       ComboService cp = new ComboService();
        Combo cb = cp.getCombo(id);

        cb.setProducts(cp.getListComboProduct(id));






        request.setAttribute("c", cb);

        request.getRequestDispatcher("chitietCombo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}