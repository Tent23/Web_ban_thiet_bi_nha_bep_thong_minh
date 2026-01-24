package com.webthietbibep.controller.admin;

import com.webthietbibep.dao.StatsDAO;
import com.webthietbibep.model.ChartData;
import com.webthietbibep.model.TopProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

// Đường dẫn truy cập dashboard
@WebServlet(name = "AdminDashboardServlet", urlPatterns = "/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatsDAO statsDAO = new StatsDAO();

        // 1. Lấy số liệu tổng quan
        double totalRevenue = statsDAO.getTotalRevenue();
        int totalOrders = statsDAO.countAllOrders();
        int totalUsers = statsDAO.countUsers();

        // 2. Lấy dữ liệu biểu đồ
        List<ChartData> chartDataList = statsDAO.getRevenueLast7Days();

        // 3. Lấy Top sản phẩm
        List<TopProduct> topProducts = statsDAO.getTopSellingProducts();

        // 4. Format tiền tệ để hiển thị đẹp
        NumberFormat vnFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        req.setAttribute("totalRevenueFormat", vnFormat.format(totalRevenue));
        req.setAttribute("totalOrders", totalOrders);
        req.setAttribute("totalUsers", totalUsers);

        req.setAttribute("chartDataList", chartDataList); // Truyền list này sang JSP để JS xử lý
        req.setAttribute("topProducts", topProducts);

        // Forward sang trang JSP
        req.getRequestDispatcher("/admin/admin_dashboard.jsp").forward(req, resp);
    }
}