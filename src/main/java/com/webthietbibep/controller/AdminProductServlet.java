package com.webthietbibep.controller;

import com.webthietbibep.dao.BrandDAO;
import com.webthietbibep.dao.CategoryDAO;
import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AdminProductServlet", urlPatterns = {"/admin/product-save"})
public class AdminProductServlet extends HttpServlet {

    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private BrandDAO brandDAO;

    @Override
    public void init() {
        this.productDAO = new ProductDAO();
        this.categoryDAO = new CategoryDAO(); // Khởi tạo DAO
        this.brandDAO = new BrandDAO();       // Khởi tạo DAO
    }

    // --- DO GET: HIỂN THỊ FORM VÀ DANH SÁCH DROPDOWN ---
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String idStr = req.getParameter("id");

        Product p = new Product();

        // 1. Nếu là sửa (edit) thì load thông tin sản phẩm cũ
        if ("edit".equals(action) && idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Product existP = productDAO.getProduct(id);
                if (existP != null) {
                    p = existP;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // 2. Gửi object product sang JSP
        req.setAttribute("product", p);

        // 3. --- QUAN TRỌNG: LẤY LIST DANH MỤC & THƯƠNG HIỆU GỬI SANG JSP ---
        req.setAttribute("listCategories", categoryDAO.getAll());
        req.setAttribute("listBrands", brandDAO.getAll());
        // --------------------------------------------------------------------

        req.getRequestDispatcher("/admin/admin_add_product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Product p = new Product();

        try {
            String idStr = req.getParameter("product_id");
            p.setProduct_id((idStr == null || idStr.isEmpty()) ? 0 : Integer.parseInt(idStr));

            p.setProduct_name(req.getParameter("product_name"));
            p.setDescription(req.getParameter("description"));
            p.setImage(req.getParameter("image"));
            p.setPrice(parseDouble(req.getParameter("price")));
            p.setStock_quantity(parseInt(req.getParameter("stock_quantity")));

            // Lấy giá trị ID từ Dropdown
            p.setCategory_id(parseInt(req.getParameter("category_id")));
            p.setBrand_id(parseInt(req.getParameter("brand_id")));

            if (p.getProduct_id() > 0) {
                productDAO.update(p);
                session.setAttribute("msg", "Cập nhật thành công!");
            } else {
                productDAO.insert(p);
                session.setAttribute("msg", "Thêm mới thành công!");
            }
            resp.sendRedirect(req.getContextPath() + "/products");

        } catch (Exception e) {
            e.printStackTrace();
            // Nếu lỗi, phải load lại list để hiển thị lại form
            req.setAttribute("listCategories", categoryDAO.getAll());
            req.setAttribute("listBrands", brandDAO.getAll());
            req.setAttribute("error", "Lỗi: " + e.getMessage());
            req.getRequestDispatcher("/admin/admin_add_product.jsp").forward(req, resp);
        }
    }

    private double parseDouble(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0; }
    }
    private int parseInt(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }
}