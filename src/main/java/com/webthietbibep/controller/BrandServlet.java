package com.webthietbibep.controller;

import com.webthietbibep.dao.BrandDAO;
import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Brand;
import com.webthietbibep.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand")
public class BrandServlet extends HttpServlet {

    private final BrandDAO brandDAO = new BrandDAO();
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Lấy tham số ID dưới dạng chuỗi trước
        String idParam = req.getParameter("id");

        // 2. Kiểm tra nếu ID bị null hoặc rỗng -> Quay về trang chủ
        if (idParam == null || idParam.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        try {
            // 3. Cố gắng chuyển đổi sang số nguyên
            int brandId = Integer.parseInt(idParam);

            // 4. Gọi DAO lấy thông tin thương hiệu
            Brand brand = brandDAO.getById(brandId);

            // 5. Nếu không tìm thấy thương hiệu nào khớp với ID
            if (brand == null) {
                // Có thể gửi lỗi 404 hoặc quay về trang chủ
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Thương hiệu không tồn tại");
                return;
            }

            // 6. Lấy danh sách sản phẩm thuộc thương hiệu đó
            List<Product> products = productDAO.getByBrandId(brandId);

            // 7. Đẩy dữ liệu sang JSP
            req.setAttribute("brand", brand);
            req.setAttribute("products", products); // Danh sách sản phẩm của brand này

            // Forward sang trang giao diện hiển thị chi tiết Brand
            req.getRequestDispatcher("brand.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            // 8. Nếu ID không phải là số (VD: ?id=abc) -> Quay về trang chủ
            System.out.println("Lỗi format ID Brand: " + e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/home");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}