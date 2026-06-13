package com.webthietbibep.controller;

import com.webthietbibep.dao.UserDAO;
import com.webthietbibep.model.User;
import com.webthietbibep.utils.KeyUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet(name = "KeyManagementServlet", urlPatterns = "/user/key-management")
public class KeyManagementServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        currentUser = userDAO.findById(currentUser.getUser_id());
        session.setAttribute("user", currentUser);

        req.getRequestDispatcher("/key-management.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String action = req.getParameter("action");
        if ("generateNewKey".equals(action)) {
            try {
                KeyPair keyPair = KeyUtil.generateKeyPair();
                String publicKeyBase64 = KeyUtil.encodePublicKey(keyPair.getPublic());
                String privateKeyPem = KeyUtil.encodePrivateKeyToPem(keyPair.getPrivate()); // Use new method

                String publicKeyId = UUID.randomUUID().toString();
                LocalDateTime creationDate = LocalDateTime.now();


                userDAO.updatePublicKeyInfo(currentUser.getUser_id(), publicKeyBase64, publicKeyId, creationDate, null);


                currentUser.setPublicKey(publicKeyBase64);
                currentUser.setPublicKeyId(publicKeyId);
                currentUser.setPublicKeyCreationDate(creationDate);
                currentUser.setPublicKeyRevocationDate(null);
                session.setAttribute("user", currentUser);

                resp.setContentType("application/octet-stream");
                resp.setHeader("Content-Disposition", "attachment; filename=\"private_key_" + currentUser.getUsername() + ".pem\"");
                PrintWriter out = resp.getWriter();
                out.print(privateKeyPem); // Send PEM formatted key
                out.flush();

            } catch (NoSuchAlgorithmException e) {
                req.setAttribute("errorMessage", "Lỗi: Thuật toán mã hóa không khả dụng. " + e.getMessage());
                req.getRequestDispatcher("/key-management.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("errorMessage", "Lỗi khi tạo khóa: " + e.getMessage());
                req.getRequestDispatcher("/key-management.jsp").forward(req, resp);
            }
        } else if ("revokeKey".equals(action)) {
            try {

                userDAO.revokePublicKey(currentUser.getUser_id(), LocalDateTime.now());


                currentUser.setPublicKeyRevocationDate(LocalDateTime.now());
                session.setAttribute("user", currentUser);

                req.setAttribute("successMessage", "Khóa công khai đã được thu hồi thành công.");
                req.getRequestDispatcher("/key-management.jsp").forward(req, resp);

            } catch (Exception e) {
                req.setAttribute("errorMessage", "Lỗi khi thu hồi khóa: " + e.getMessage());
                req.getRequestDispatcher("/key-management.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "Hành động không hợp lệ.");
            req.getRequestDispatcher("/key-management.jsp").forward(req, resp);
        }
    }
}