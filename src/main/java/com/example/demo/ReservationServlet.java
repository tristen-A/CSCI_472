package com.example.demo;

import com.example.demo.Database.DatabaseHandler;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.example.demo.Database.*;

@WebServlet(name = "reservationServlet", value = "/reservation")
public class ReservationServlet extends HttpServlet {
    private final TableManager TableManager = new  TableManager();

    @Override
    public void init() {}

    public void destroy() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        updatePage(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        updatePage(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin.jsp");
        dispatcher.forward(request, response);
    }

    protected void updatePage(HttpServletRequest request) {
        request.setAttribute("accounts", TableManager.getTables());
    }
}