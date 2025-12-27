package com.webthietbibep.controller;

import com.webthietbibep.model.OrderItem;
import com.webthietbibep.model.Warranty;
import com.webthietbibep.service.WarrantyService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BaoHanhContronller", value = "/BaoHanh")
public class BaoHanhContronller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WarrantyService wp = new WarrantyService();
        String pra = request.getParameter("pra");

        String type  = request.getParameter("Tra_Cuu_theo");
        if(pra != null && type != null) {
            List<Warranty> listWrranty = wp.getSearchListWarranty(type, pra);
            if (listWrranty.isEmpty()) {
                request.setAttribute("error", "Kết quả tìm kiếm không tồn tại ");
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