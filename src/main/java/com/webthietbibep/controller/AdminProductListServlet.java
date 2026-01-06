package com.webthietbibep.controller;

import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProductListServlet", value = "/admin/products")
public class AdminProductListServlet extends HttpServlet {

    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // --- XỬ LÝ XÓA ---
        if ("delete".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    productDAO.delete(id);
                    request.getSession().setAttribute("msg", "Xóa sản phẩm thành công!");
                } catch (Exception e) {
                    request.getSession().setAttribute("error", "Lỗi khi xóa: " + e.getMessage());
                }
            }
            response.sendRedirect(request.getContextPath() + "/admin/products");
            return;
        }

        // --- HIỂN THỊ DANH SÁCH ---
        List<Product> list = productDAO.getListProduct();
        request.setAttribute("listProducts", list);

        request.getRequestDispatcher("/admin/admin_product_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}