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

    private ProductDAO productDAO;

    @Override
    public void init() {
        // Khởi tạo DAO 1 lần khi Servlet chạy
        this.productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // --- 1. XỬ LÝ XÓA SẢN PHẨM (Giữ nguyên logic cũ của bạn) ---
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
            // Redirect để tránh lỗi lặp lại thao tác khi F5
            response.sendRedirect(request.getContextPath() + "/admin/products");
            return;
        }

        // --- 2. XỬ LÝ HIỂN THỊ DANH SÁCH & PHÂN TRANG ---

        // Cấu hình: Trang mặc định là 1, số lượng là 10
        int page = 1;
        int recordsPerPage = 10;

        // Lấy số trang từ URL (ví dụ: /admin/products?page=2)
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1; // Nếu nhập bậy thì về trang 1
            }
        }

        // Tính toán vị trí bắt đầu lấy trong DB (Offset)
        // Trang 1 -> offset 0, Trang 2 -> offset 10...
        int offset = (page - 1) * recordsPerPage;

        // --- GỌI DAO ---
        // Lấy danh sách 10 sản phẩm
        List<Product> list = productDAO.findAll(recordsPerPage, offset);

        // Đếm tổng số lượng sản phẩm đang có để tính số trang
        int totalRecords = productDAO.countAll();

        // Tính tổng số trang (Ví dụ: 25 sp / 10 = 2.5 -> Làm tròn lên thành 3 trang)
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        // --- GỬI DỮ LIỆU SANG JSP ---
        request.setAttribute("listProducts", list);  // Danh sách 10 sp
        request.setAttribute("currentPage", page);   // Trang hiện tại
        request.setAttribute("totalPages", totalPages); // Tổng số trang

        request.getRequestDispatcher("/admin/admin_product_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}