package com.webthietbibep.controller.admin;

import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminAddProductServlet", value = "/admin/product/add")
public class AdminAddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hiển thị form
        request.getRequestDispatcher("/admin/admin_add_product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Set encoding ngay lập tức để nhận tiếng Việt và tránh lỗi null do định dạng
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // 2. Lấy dữ liệu
            String name = request.getParameter("product_name");
            String desc = request.getParameter("description");
            String image = request.getParameter("image");
            String status = request.getParameter("status");
            String priceStr = request.getParameter("price");
            String stockStr = request.getParameter("stock_quantity");
            String sku = request.getParameter("sku");
            String catIdStr = request.getParameter("category_id");

            // Debug ra console để kiểm tra (Sẽ thấy chữ tiếng Việt rõ ràng)
            System.out.println("--- LOG NHẬN DỮ LIỆU ---");
            System.out.println("Tên SP: " + name);
            System.out.println("Giá: " + priceStr);

            // 3. Ép kiểu và gán vào Model
            Product p = new Product();
            p.setProduct_name(name);
            p.setDescription(desc);
            p.setImage(image);
            p.setStatus(status);
            p.setSku(sku);
            p.setPrice(Double.parseDouble(priceStr != null ? priceStr : "0"));
            p.setStock_quantity(Integer.parseInt(stockStr != null ? stockStr : "0"));
            p.setCategory_id(Integer.parseInt(catIdStr != null ? catIdStr : "1"));

            // 4. Lưu vào Database
            boolean isSaved = ProductDAO.insertProduct(p);

            if (isSaved) {
                System.out.println(">>> LƯU DB THÀNH CÔNG!");
                // Chuyển hướng về trang thành công (hoặc chính nó với tham số success)
                response.sendRedirect(request.getContextPath() + "/admin/product/add?msg=success");
            } else {
                throw new Exception("Lỗi SQL: Không thể chèn dữ liệu vào bảng.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi xử lý: " + e.getMessage());
            request.getRequestDispatcher("/admin/admin_add_product.jsp").forward(request, response);
        }
    }
}