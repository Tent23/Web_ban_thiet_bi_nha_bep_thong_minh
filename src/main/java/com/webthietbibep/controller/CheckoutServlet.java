package com.webthietbibep.controller;

import com.webthietbibep.cart.Cart;
import com.webthietbibep.cart.CartItem;
import com.webthietbibep.dao.ProductDAO;
import com.webthietbibep.dao.UserAddressDAO;
import com.webthietbibep.dao.OrdersDAO;
import com.webthietbibep.dao.OrderItemDAO;
import com.webthietbibep.model.Order;
import com.webthietbibep.model.OrderItem;
import com.webthietbibep.model.Product;
import com.webthietbibep.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private final UserAddressDAO addressDAO = new UserAddressDAO();
    private final OrdersDAO ordersDAO = new OrdersDAO();
    private final OrderItemDAO itemDAO = new OrderItemDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }


        String mode = req.getParameter("mode");
        Cart cart;

        if ("buy_now".equals(mode)) {
            int productId = Integer.parseInt(req.getParameter("id"));
            Product p = new ProductDAO().getById(productId);

            if (p == null) {
                resp.sendRedirect("products");
                return;
            }

            cart = new Cart();
            cart.addItem(p, 1);

        } else {
            cart = (Cart) req.getSession().getAttribute("cart");

            if (cart == null || cart.getItems().isEmpty()) {
                resp.sendRedirect("cart");
                return;
            }
        }

        req.setAttribute("cart", cart);
        req.setAttribute("mode", mode);
        req.setAttribute("addresses",
                addressDAO.findByUserId(user.getUser_id()));

        req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        String mode = req.getParameter("mode");
        Cart cart;

        if ("buy_now".equals(mode)) {
            cart = (Cart) req.getAttribute("cart");
        } else {
            cart = (Cart) req.getSession().getAttribute("cart");
        }

        if (cart == null || cart.getItems().isEmpty()) {
            resp.sendRedirect("cart");
            return;
        }

        int addressId = Integer.parseInt(req.getParameter("addressId"));
        String payment = req.getParameter("paymentMethod");

        Order order = new Order();
        order.setUser_id(user.getUser_id());
        order.setAddress_id(addressId);
        order.setPayment_method(payment);
        order.setTotal_amount(cart.getTotal());

        int orderId = ordersDAO.insert(order);

        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder_id(orderId);
            oi.setProduct_id(ci.getProduct().getProduct_id());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice_at_purchase(ci.getPrice());

            itemDAO.insert(oi);
        }

        if ("cart".equals(mode)) {
            req.getSession().removeAttribute("cart");
        }

        resp.sendRedirect("orders");
    }

}
