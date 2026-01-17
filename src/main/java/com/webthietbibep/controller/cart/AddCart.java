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
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("q"));
        HttpSession session = request.getSession();
        ProductService ps = new ProductService();
        Product product = ps.getProduct(id);
        if(product == null){
            response.sendRedirect("products");
            return;

        }

        else if(product.getStock_quantity()  < quantity){}
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            cart = new Cart();
        }
        if(product.getStock_quantity() == 0 ){
            session.setAttribute("message", "Sản phẩm đã hết hàng");
            response.sendRedirect("product-detail?id=" + id);

            return;
        }
        int currProductQuantity = 0;
        if(cart.getData().containsKey(id)){
            currProductQuantity = cart.getData().get(id).getQuantity();
        }
        if ((currProductQuantity + quantity ) > product.getStock_quantity()) {
            session.setAttribute("message", "Vượt quá số lượng còn lại của sản phẩm để thêm vào giỏ hàng!");

        }

        else {
            cart.addItem(product, quantity);
            session.setAttribute("cart", cart);
            session.removeAttribute("message");
        }
            response.sendRedirect("product-detail?id=" + id);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}