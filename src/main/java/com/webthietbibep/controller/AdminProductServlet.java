package com.webthietbibep.controller;

import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/product-save")
public class AdminProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        // Khởi tạo DAO (BaseDao sẽ tự lo phần connect bên trong)
        this.productDAO = new ProductDAO();
    }

    // GET: Hiển thị trang Form (Thêm mới hoặc Edit)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String idStr = req.getParameter("id");

        Product p = new Product(); // Mặc định là thêm mới (ID = 0)

        // Nếu action là edit -> Lấy dữ liệu cũ từ DB đổ vào form
        if ("edit".equals(action) && idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Product existP = productDAO.getProduct(id);
                if (existP != null) p = existP;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        req.setAttribute("product", p);
        req.getRequestDispatcher("/admin/admin_add_product.jsp").forward(req, resp);
    }

    // POST: Xử lý nút Lưu (Save)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // Để nhận tiếng Việt không lỗi

        Product p = new Product();
        try {
            // 1. Lấy product_id từ input hidden
            String idStr = req.getParameter("product_id");
            p.setProduct_id((idStr == null || idStr.isEmpty()) ? 0 : Integer.parseInt(idStr));

            // 2. Lấy các thông tin khác
            p.setProduct_name(req.getParameter("product_name"));
            p.setDescription(req.getParameter("description"));
            p.setImage(req.getParameter("image"));

            // Xử lý số (tránh lỗi null)
            p.setPrice(parseDouble(req.getParameter("price")));
            p.setStock_quantity(parseInt(req.getParameter("stock_quantity")));
            p.setCategory_id(parseInt(req.getParameter("category_id")));
            p.setBrand_id(parseInt(req.getParameter("brand_id")));

            // 3. Gọi DAO
            if (p.getProduct_id() > 0) {
                productDAO.update(p); // Có ID -> Cập nhật
            } else {
                productDAO.insert(p); // ID = 0 -> Thêm mới
            }

            // 4. Thành công -> Về trang danh sách
            resp.sendRedirect(req.getContextPath() + "/products");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500, "Lỗi lưu sản phẩm: " + e.getMessage());
        }
    }

    // Hàm phụ trợ convert an toàn
    private double parseDouble(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0; }
    }
    private int parseInt(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }
}