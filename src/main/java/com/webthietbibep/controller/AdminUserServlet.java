package com.webthietbibep.controller;

import com.webthietbibep.dao.UserDAO;
import com.webthietbibep.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "AdminUserServlet", urlPatterns = {"/admin/users"})
public class AdminUserServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                listUsers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "insert":
                insertUser(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            default:
                listUsers(request, response);
                break;
        }
    }

    // 1. Hiển thị danh sách
    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> list = userDAO.findAll();
        request.setAttribute("users", list);
        request.getRequestDispatcher("/admin/admin_user_list.jsp").forward(request, response);
    }

    // 2. Hiển thị form thêm mới
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gửi một user rỗng sang để form không bị lỗi null
        request.setAttribute("user", new User());
        request.getRequestDispatcher("/admin/admin_user_form.jsp").forward(request, response);
    }

    // 3. Hiển thị form sửa
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.findById(id);

        if (existingUser == null) {
            response.sendRedirect(request.getContextPath() + "/admin/users?message=notfound");
            return;
        }

        request.setAttribute("user", existingUser);
        request.getRequestDispatcher("/admin/admin_user_form.jsp").forward(request, response);
    }

    // 4. Xử lý thêm mới (INSERT)
    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Validate cơ bản
        if (userDAO.existsUsername(username)) {
            request.setAttribute("error", "Tên đăng nhập đã tồn tại!");
            request.setAttribute("user", new User(0, username, fullName, email, phone, "", null, role,null,true));
            request.getRequestDispatcher("/admin/admin_user_form.jsp").forward(request, response);
            return;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setFull_name(fullName);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setRole(role);
        newUser.setCreate_at(LocalDateTime.now());

        // TODO: Thực tế bạn CẦN mã hóa mật khẩu ở đây (ví dụ dùng BCrypt)
        // newUser.setPassword_hash(BCrypt.hashpw(password, BCrypt.gensalt()));
        newUser.setPassword_hash(password); // Tạm thời lưu plain text (không khuyến khích)

        userDAO.insert(newUser);
        response.sendRedirect(request.getContextPath() + "/admin/users?message=saved");
    }

    // 5. Xử lý cập nhật (UPDATE)
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        String password = request.getParameter("password"); // Có thể rỗng nếu không đổi pass

        User user = new User();
        user.setUser_id(id);
        user.setFull_name(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);

        if (password != null && !password.trim().isEmpty()) {
            // TODO: Mã hóa mật khẩu mới nếu có nhập
            user.setPassword_hash(password);
        } else {
            user.setPassword_hash(""); // Báo hiệu cho DAO là không update pass
        }

        userDAO.update(user);
        response.sendRedirect(request.getContextPath() + "/admin/users?message=updated");
    }

    // 6. Xử lý xóa
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/admin/users?message=deleted");
    }
}