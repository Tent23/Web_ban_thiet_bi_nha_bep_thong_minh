package com.webthietbibep.controller;

import com.webthietbibep.model.Banner;
import com.webthietbibep.services.BannerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet(name = "AdminBannerServlet", urlPatterns = {"/admin/banners"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class AdminBannerServlet extends HttpServlet {

    private final BannerService bannerService = new BannerService();

    // Thư mục lưu ảnh trong dự án (Sẽ tạo trong thư mục build/webapp)
    private static final String UPLOAD_DIR = "assets/uploads/banners";

    // ... (Giữ nguyên doGet) ...
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                req.getRequestDispatcher("/admin/admin_banner_form.jsp").forward(req, resp);
                break;
            case "edit":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Banner existingBanner = bannerService.getBannerById(id);
                    req.setAttribute("banner", existingBanner);
                    req.getRequestDispatcher("/admin/admin_banner_form.jsp").forward(req, resp);
                } catch (Exception e) {
                    resp.sendRedirect("banners?error=true");
                }
                break;
            case "delete":
                deleteBanner(req, resp);
                break;
            default:
                listBanners(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("insert".equals(action)) {
            insertBanner(req, resp);
        } else if ("update".equals(action)) {
            updateBanner(req, resp);
        } else {
            listBanners(req, resp);
        }
    }

    // --- LOGIC XỬ LÝ ẢNH ---
    private String handleImageUpload(HttpServletRequest req) throws IOException, ServletException {
        // 1. Kiểm tra xem có file upload không
        Part filePart = req.getPart("image_file");
        if (filePart != null && filePart.getSize() > 0) {
            // Lấy đường dẫn thực tế trên server
            String applicationPath = req.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

            // Tạo thư mục nếu chưa tồn tại
            File uploadDir = new File(uploadFilePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Tạo tên file ngẫu nhiên để tránh trùng lặp (dùng UUID)
            String fileName = UUID.randomUUID().toString() + "_" + getFileName(filePart);

            // Lưu file
            filePart.write(uploadFilePath + File.separator + fileName);

            // Trả về đường dẫn tương đối để lưu vào DB
            return UPLOAD_DIR + "/" + fileName; // VD: assets/uploads/banners/abc.jpg
        }

        // 2. Nếu không upload file, kiểm tra xem có nhập Link URL không
        String imageUrl = req.getParameter("image_url");
        if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            return imageUrl.trim(); // VD: https://example.com/anh.jpg
        }

        return null; // Không có ảnh mới
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "unknown.jpg";
    }

    // --- INSERT ---
    private void insertBanner(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String linkUrl = req.getParameter("link_url");
            int sortOrder = Integer.parseInt(req.getParameter("sort_order"));
            byte isActive = Byte.parseByte(req.getParameter("is_active"));

            // Xử lý ảnh
            String imagePath = handleImageUpload(req);
            if (imagePath == null) imagePath = ""; // Nếu không có ảnh thì để rỗng

            Banner newBanner = new Banner(0, title, description, imagePath, linkUrl, sortOrder, isActive);
            bannerService.insertBanner(newBanner);
            resp.sendRedirect(req.getContextPath() + "/admin/banners?message=inserted");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/banners?error=true");
        }
    }

    // --- UPDATE ---
    private void updateBanner(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String linkUrl = req.getParameter("link_url");
            int sortOrder = Integer.parseInt(req.getParameter("sort_order"));
            byte isActive = Byte.parseByte(req.getParameter("is_active"));

            String oldImage = req.getParameter("old_image");

            // Xử lý ảnh mới
            String newImage = handleImageUpload(req);

            // Nếu newImage null (nghĩa là user không up file mới, không nhập link mới) -> Dùng lại ảnh cũ
            if (newImage == null) {
                newImage = oldImage;
            }

            Banner banner = new Banner(id, title, description, newImage, linkUrl, sortOrder, isActive);
            bannerService.updateBanner(banner);
            resp.sendRedirect(req.getContextPath() + "/admin/banners?message=updated");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/banners?error=true");
        }
    }

    // ... (Giữ nguyên listBanners, deleteBanner) ...
    private void listBanners(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listBanners", bannerService.getAllBannersAdmin());
        req.getRequestDispatcher("/admin/admin_banners.jsp").forward(req, resp);
    }

    private void deleteBanner(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            bannerService.deleteBanner(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/banners");
    }
}