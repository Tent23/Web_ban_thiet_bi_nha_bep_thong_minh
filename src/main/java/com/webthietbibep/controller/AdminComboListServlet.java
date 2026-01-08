package com.webthietbibep.controller;

import com.webthietbibep.dao.ComboDao;
import com.webthietbibep.model.Combo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminComboListServlet", value = "/admin/combo-list")
public class AdminComboListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComboDao dao = new ComboDao();

        // Gọi hàm lấy tất cả combo (nhớ đảm bảo DAO đã có hàm này như code trước tôi gửi)
        List<Combo> list = dao.getAllCombosForAdmin();

        request.setAttribute("listCombos", list);
        request.getRequestDispatcher("/admin/admin_combo_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}