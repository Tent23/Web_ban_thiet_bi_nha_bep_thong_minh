package com.webthietbibep.controller;

import com.webthietbibep.model.Warranty;
import com.webthietbibep.services.WarrantyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BaoHanhContronller", value = "/BaoHanh")
public class WarrantyContronller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WarrantyService wp = new WarrantyService();
        String pra = request.getParameter("pra");

        String type  = request.getParameter("Tra_Cuu_theo");
        if(pra != null && type != null) {
            List<Warranty> listWrranty = wp.getSearchListWarranty(type, pra);
            if (listWrranty.isEmpty()) {
                request.setAttribute("error", "Kết quả tìm kiếm không tồn tại ! ");
            } else {
                request.setAttribute("listWrranty", listWrranty);
            }

        }
        request.setAttribute("pra", pra);
        request.setAttribute("type", type);
        request.getRequestDispatcher("BaoHanh.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}