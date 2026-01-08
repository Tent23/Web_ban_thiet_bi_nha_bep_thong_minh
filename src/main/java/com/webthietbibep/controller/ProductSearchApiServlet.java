package com.webthietbibep.controller;

import com.webthietbibep.model.Product;
import com.webthietbibep.service.EcosystemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// Đường dẫn này phải khớp với đường dẫn trong fetch() ở file JSP
@WebServlet("/api/product-search")
public class ProductSearchApiServlet extends HttpServlet {

    private EcosystemService service = new EcosystemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Cấu hình trả về JSON và tiếng Việt
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 2. Lấy từ khóa tìm kiếm
        String query = req.getParameter("q");

        try (PrintWriter out = resp.getWriter()) {
            if (query == null || query.trim().isEmpty()) {
                out.print("[]"); // Trả về mảng rỗng nếu không có từ khóa
                return;
            }

            // 3. Gọi Service để tìm sản phẩm
            List<Product> products = service.searchProductsByName(query);

            // 4. Chuyển List<Product> thành chuỗi JSON thủ công
            // (Cách này giúp bạn không cần cài thêm thư viện Gson/Jackson)
            StringBuilder json = new StringBuilder();
            json.append("[");

            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                json.append("{");
                json.append("\"product_id\":").append(p.getProduct_id()).append(",");
                // Xử lý dấu ngoặc kép trong tên để tránh lỗi JSON
                String safeName = p.getProduct_name().replace("\"", "\\\"");
                json.append("\"product_name\":\"").append(safeName).append("\"");
                json.append("}");

                // Thêm dấu phẩy nếu không phải phần tử cuối
                if (i < products.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");

            // 5. Trả kết quả về cho JSP
            out.print(json.toString());

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500); // Báo lỗi server
        }
    }
}