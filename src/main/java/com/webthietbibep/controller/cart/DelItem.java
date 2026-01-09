package com.webthietbibep.controller.cart;

import com.webthietbibep.cart.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DelItem", value = "/del-item")
public class DelItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        com.webthietbibep.cart.Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.delItem(id);
        response.sendRedirect("cart");
    }
}