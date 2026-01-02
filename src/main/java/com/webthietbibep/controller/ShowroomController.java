package com.webthietbibep.controller;

import com.webthietbibep.model.Showroom;
import com.webthietbibep.services.ShowroomService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowroomController", value = "/Showroom")
public class ShowroomController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         ShowroomService ss = new ShowroomService();
         List<Showroom> listS = ss.getListShowroom();

         for(Showroom s:listS){
             s.setImages(ss.getListShowroomImage(s.getId()));
         }
          request.setAttribute("listS", listS);
         request.getRequestDispatcher("Showroom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}