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
    private final ReservationManager ReservationManager = new  ReservationManager(TableManager);

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

        String action = request.getParameter("action");
        switch (action) {
            case "Submit":
                int table_num = Integer.parseInt(request.getParameter("table_num"));

                if (!TableManager.checkReserved(table_num)) {
                    TableManager.updateReserved(table_num, true);

                    String acc_usern = request.getParameter("acc_usern");
                    String date = request.getParameter("date");
                    String time = request.getParameter("time");
                    String[] data = {acc_usern, String.valueOf(table_num), date, time};

                    ReservationManager.addReservation(data);
                } else {
                    request.setAttribute("error", "Given table #" + table_num + " is already reserved.");
                }

                break;
        }

        updatePage(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
        dispatcher.forward(request, response);
    }

    protected void updatePage(HttpServletRequest request) {
        request.setAttribute("tables", TableManager.getTables());
        request.setAttribute("reservations", ReservationManager.getReservations());
    }
}