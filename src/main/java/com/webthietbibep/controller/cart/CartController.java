package com.webthietbibep.controller.cart;

import com.webthietbibep.cart.Cart;
import com.webthietbibep.cart.CartItem;
import com.webthietbibep.services.BrandService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BrandService bs = new BrandService();
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Map<Integer,String> data = new HashMap<>();
        if(cart != null && cart.getData() != null){


            for(CartItem ci : cart.getData().values()){
                int Pid = ci.getProduct().getProduct_id();
                int Bid =  ci.getProduct().getBrand_id();
                if (!data.containsKey(Pid)) {
                    String brandname = bs.getBrandById(Bid).getBrand_name();
                    if(brandname != null) {
                        data.put(Pid, brandname);
                    }
                }

            }
        }
        request.setAttribute("data",data);
        request.getRequestDispatcher("giohang.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}