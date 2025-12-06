package com.example.demo;

import com.example.demo.Core.AccountManager;
import com.example.demo.Core.ReservationManager;
import com.example.demo.Core.TableManager;
import com.example.demo.Database.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "profileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    private final TableManager TableManager = new  TableManager();
    private final ReservationManager ReservationManager = new  ReservationManager(TableManager);

    @Override
    public void init() {}
    public void destroy() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        updatePage(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/profile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int res_num = Integer.parseInt(request.getParameter("res_num"));

        String submit_type = request.getParameter("submit_type");
        switch (submit_type) {
            case "Cancel Reservation":
                ReservationManager.deleteReservation(res_num);
                break;
        }

        updatePage(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/profile.jsp");
        dispatcher.forward(request, response);
    }

    protected void updatePage(HttpServletRequest request) {
        String usern = (String)request.getSession().getAttribute("username");
        request.setAttribute("acc_reservations", ReservationManager.getAccReservations(usern));
    }
}
