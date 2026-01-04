package com.webthietbibep.controller;

import com.webthietbibep.dao.BrandDAO;
import com.webthietbibep.model.Brand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminBrandServlet", urlPatterns = {"/admin/brands"})
public class AdminBrandServlet extends HttpServlet {

    // Khởi tạo DAO để làm việc với Database
    private final BrandDAO brandDAO = new BrandDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteBrand(request, response);
                    break;
                default:
                    listBrands(request, response);
                    break;
            }
        } catch (Exception e) {
            // Log lỗi ra console server để debug nếu có sự cố
            e.printStackTrace();
            // Chuyển hướng về trang danh sách nếu lỗi (hoặc trang 404 tùy ý)
            response.sendRedirect(request.getContextPath() + "/admin/brands?message=error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đảm bảo xử lý tiếng Việt đúng khi nhận từ form
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "insert":
                    insertBrand(request, response);
                    break;
                case "update":
                    updateBrand(request, response);
                    break;
                default:
                    listBrands(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/brands?message=error");
        }
    }

    // --- CÁC HÀM XỬ LÝ LOGIC ---

    // 1. Hiển thị danh sách (Lấy từ DB)
    private void listBrands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> list = brandDAO.getAll(); // Gọi DAO lấy dữ liệu thật
        request.setAttribute("brands", list);
        request.getRequestDispatcher("/admin/brand-list.jsp").forward(request, response);
    }

    // 2. Hiển thị form thêm mới
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/brand-form.jsp").forward(request, response);
    }

    // 3. Hiển thị form sửa (Lấy thông tin cũ từ DB đổ vào form)
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Brand existingBrand = brandDAO.getById(id);

            if (existingBrand != null) {
                request.setAttribute("brand", existingBrand);
                request.getRequestDispatcher("/admin/brand-form.jsp").forward(request, response);
            } else {
                // Nếu không tìm thấy ID thì quay về danh sách
                response.sendRedirect(request.getContextPath() + "/admin/brands");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/brands");
        }
    }

    // 4. Xử lý thêm mới vào DB
    private void insertBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("brand_name");
        String logo = request.getParameter("logo_url");

        Brand newBrand = new Brand();
        newBrand.setBrand_name(name);
        newBrand.setLogo_url(logo);

        brandDAO.insert(newBrand); // Lưu thật vào DB

        response.sendRedirect(request.getContextPath() + "/admin/brands?message=saved");
    }

    // 5. Xử lý cập nhật vào DB
    private void updateBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("brand_id"));
        String name = request.getParameter("brand_name");
        String logo = request.getParameter("logo_url");

        Brand brand = new Brand(id, logo, name);

        brandDAO.update(brand); // Cập nhật thật vào DB

        response.sendRedirect(request.getContextPath() + "/admin/brands?message=saved");
    }

    // 6. Xử lý xóa khỏi DB
    private void deleteBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            brandDAO.delete(id); // Xóa thật khỏi DB
            response.sendRedirect(request.getContextPath() + "/admin/brands?message=deleted");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/brands");
        }
    }
}