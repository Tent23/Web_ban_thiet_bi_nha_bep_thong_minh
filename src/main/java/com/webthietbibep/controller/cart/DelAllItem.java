package com.webthietbibep.controller.cart;

import com.webthietbibep.cart.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DelAllItem", value = "/delete-all")
public class DelAllItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        com.webthietbibep.cart.Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart =  new Cart();
        }
        cart.delAllItems();
        response.sendRedirect("cart");
    }
}