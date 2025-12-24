package com.webthietbibep.controller.admin;

import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/admin/add-product")
@MultipartConfig
public class AdminAddProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 1. Lấy dữ liệu từ form
        String productName = request.getParameter("product_name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock_quantity"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));

        // 2. Xử lý upload ảnh
        Part imagePart = request.getPart("image");
        String fileName = imagePart.getSubmittedFileName();

        String uploadPath = getServletContext().getRealPath("/assets/images/products");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        imagePart.write(uploadPath + File.separator + fileName);

        String imagePath = "assets/images/products/" + fileName;

        // 3. Tạo Product object
        Product product = new Product();
        product.setProduct_name(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock_quantity(stock);
        product.setCategory_id(categoryId);
        product.setImage(imagePath);
        product.setCreate_at(LocalDateTime.now());

        // 4. Insert DB
        productDAO.insert((List<Product>) product);

        // 5. Redirect về danh sách sản phẩm
        response.sendRedirect(request.getContextPath() + "/admin/products");
    }
}
