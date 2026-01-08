package com.webthietbibep.controller;

import com.webthietbibep.dao.ComboDao;
import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.model.Combo;
import com.webthietbibep.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminComboServlet", urlPatterns = {"/admin/combos"})
public class AdminComboServlet extends HttpServlet {

    private ComboDao comboDao = new ComboDao();
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Luôn load danh sách sản phẩm để hiển thị checkbox
        // Lưu ý: Hàm này trong ProductDao phải trả về List<Product>
        List<Product> listProducts = productDAO.getListProduct();
        request.setAttribute("listProducts", listProducts);

        if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Combo combo = comboDao.getCombo(id);

                // Lấy danh sách ID các sản phẩm đã có trong combo này
                List<Integer> selectedProductIds = comboDao.getProductIdsByComboId(id);

                request.setAttribute("combo", combo);
                request.setAttribute("selectedProductIds", selectedProductIds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Forward về JSP
        request.getRequestDispatcher("/admin/admin_combo_form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("save".equals(action)) {
            try {
                // 1. Nhận dữ liệu từ form
                String idStr = request.getParameter("id");
                String name = request.getParameter("name");
                String tag = request.getParameter("tag");
                String content = request.getParameter("content");
                String image = request.getParameter("image");
                String gift = request.getParameter("gift");

                double basePrice = Double.parseDouble(request.getParameter("baseprice"));
                double discountPrice = Double.parseDouble(request.getParameter("discountprice"));
                int stock = Integer.parseInt(request.getParameter("stock_quantity"));

                // Checkbox active trả về "1" hoặc null
                byte isActive = (byte) (request.getParameter("is_active") != null ? 1 : 0);

                // Lấy mảng ID sản phẩm được chọn
                String[] productIds = request.getParameterValues("product_ids");

                // 2. Tạo đối tượng Combo
                Combo combo = new Combo();
                combo.setName(name);
                combo.setTag(tag);
                combo.setContent(content);
                combo.setImage(image);
                combo.setGift(gift);
                combo.setBaseprice(basePrice);
                combo.setDiscountprice(discountPrice);
                combo.setStock_quantity(stock);
                combo.setIs_active(isActive);

                // 3. Gọi DAO xử lý
                if (idStr == null || idStr.equals("0") || idStr.isEmpty()) {
                    // Thêm mới
                    comboDao.insert(combo, productIds);
                    request.getSession().setAttribute("msg", "Thêm Combo thành công!");
                } else {
                    // Cập nhật
                    combo.setId(Integer.parseInt(idStr));
                    comboDao.update(combo, productIds);
                    request.getSession().setAttribute("msg", "Cập nhật Combo thành công!");
                }

                // Redirect về trang danh sách (Bạn cần tạo trang list hoặc servlet list)
                response.sendRedirect(request.getContextPath() + "/admin/combo-list");

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Lỗi: " + e.getMessage());
                // Nếu lỗi thì giữ lại form để user sửa
                doGet(request, response);
            }
        }
    }
}