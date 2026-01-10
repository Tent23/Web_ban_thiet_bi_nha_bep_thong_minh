package com.webthietbibep.controller.cart;

import com.webthietbibep.cart.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UpdateCartItem", value = "/update-cart")
public class UpdateCartItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =  Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");

        if (cart != null && cart.getData().containsKey(id)) {
            int curQuantity = cart.getData().get(id).getQuantity();
            if(action.equals("up")){
                cart.getData().get(id).setQuantity(curQuantity+1);
            }
            else  if(action.equals("down") && curQuantity>1){
                cart.getData().get(id).setQuantity(curQuantity-1);
            }
            session.setAttribute("cart",cart);
        }
       response.sendRedirect("cart");
    }
}