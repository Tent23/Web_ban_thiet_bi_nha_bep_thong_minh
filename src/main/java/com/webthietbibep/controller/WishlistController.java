package com.webthietbibep.controller;

import com.webthietbibep.dao.WishlistDAO;
import com.webthietbibep.model.Product;
import com.webthietbibep.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WishlistController", value = "/wishlist")
public class WishlistController extends HttpServlet {

    private final WishlistDAO wishlistDAO = new WishlistDAO();

    // Hàm xử lý chung cho cả GET và POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        // --- QUAN TRỌNG: Sửa thành "user" cho khớp với LoginController ---
        User user = (User) session.getAttribute("user");

        // 1. NẾU CHƯA ĐĂNG NHẬP
        if (user == null) {
            // Lưu lại đường dẫn hiện tại (kèm tham số add/remove) vào session
            String queryString = request.getQueryString();
            String redirectUrl = "wishlist" + (queryString != null ? "?" + queryString : "");

            session.setAttribute("redirectUrl", redirectUrl); // Lưu lại để LoginController dùng
            response.sendRedirect("login"); // Chuyển sang trang Login
            return;
        }

        // 2. NẾU ĐÃ ĐĂNG NHẬP
        String action = request.getParameter("action");
        String pIdStr = request.getParameter("product_id");

        if (action != null && pIdStr != null) {
            try {
                int pid = Integer.parseInt(pIdStr);

                if ("add".equals(action)) {
                    if (!wishlistDAO.checkExist(user.getUser_id(), pid)) {
                        wishlistDAO.insert(user.getUser_id(), pid);
                    }
                } else if ("remove".equals(action)) {
                    wishlistDAO.delete(user.getUser_id(), pid);
                }

                String referer = request.getHeader("Referer");

                if (referer != null && !referer.contains("login")) {
                    response.sendRedirect(referer);
                } else {
                    response.sendRedirect("wishlist");
                }
                return;

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // 3. HIỂN THỊ DANH SÁCH (Mặc định)
        List<Product> wishlistProducts = wishlistDAO.getWishlistByUserId(user.getUser_id());
        request.setAttribute("wishlist", wishlistProducts);
        request.getRequestDispatcher("wishlist.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}