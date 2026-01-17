package com.webthietbibep.controller.cart;

import com.webthietbibep.cart.Cart;
import com.webthietbibep.model.Product;
import com.webthietbibep.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddCart", value = "/add-cart")
public class AddCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("products");
            return;
        }
        int id = Integer.parseInt(idParam);

        int quantity = 1;
        String qParam = request.getParameter("q");
        if (qParam != null && !qParam.isEmpty()) {
            try {
                quantity = Integer.parseInt(qParam);
                if (quantity < 1) quantity = 1;
            } catch (NumberFormatException e) {
                quantity = 1;
            }
        }

        HttpSession session = request.getSession();
        ProductService ps = new ProductService();
        Product product = ps.getProduct(id);

        if(product == null){
            response.sendRedirect("products");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }

        if(product.getStock_quantity() == 0 ){
            session.setAttribute("message", "Sản phẩm này hiện đang hết hàng!");
            redirectBack(request, response, id);
            return;
        }
        int currProductQuantity = 0;
        if(cart.getData().containsKey(id)){
            currProductQuantity = cart.getData().get(id).getQuantity();
        }

        if ((currProductQuantity + quantity) > product.getStock_quantity()) {
            session.setAttribute("message", "Số lượng trong kho không đủ (Còn lại: " + product.getStock_quantity() + ")");
        }
        else {
            cart.addItem(product, quantity);
            session.setAttribute("cart", cart);

            session.setAttribute("message", "Đã thêm sản phẩm vào giỏ hàng!");
        }

        redirectBack(request, response, id);
    }

    private void redirectBack(HttpServletRequest request, HttpServletResponse response, int productId) throws IOException {
        String referer = request.getHeader("Referer");

        if (referer != null && !referer.isEmpty()) {
            // Quay lại trang trước đó (Wishlist, Products, Detail...)
            response.sendRedirect(referer);
        } else {
            response.sendRedirect("product-detail?id=" + productId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}