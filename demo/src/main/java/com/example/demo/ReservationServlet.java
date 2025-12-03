package com.example.demo;

import com.example.demo.Core.ReservationManager;
import com.example.demo.Core.TableManager;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

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

        //Clearing any existing errors.
        request.setAttribute("error", "");

        String action = request.getParameter("action");
        switch (action) {
            case "Submit":
                int table_num = Integer.parseInt(request.getParameter("table_num"));
                String date = request.getParameter("date");
                String acc_usern = request.getParameter("acc_usern");
                String time = request.getParameter("time");
                String party_size = request.getParameter("party_size");
                String[] data = {acc_usern, String.valueOf(table_num), date, time, party_size};

                /*if (!ReservationManager.verifyResDateTime(date, time)) {
                    request.setAttribute("error", "Cannot select a date or time before current date/time.");
                    break;
                }
                //request.setAttribute("error", ReservationManager.verifyResDateTime(date, time));
                if (TableManager.checkReserved(table_num)) {
                    request.setAttribute("error", "Given table #" + table_num + " is already reserved.");
                    break;
                }*/

                TableManager.updateReserved(table_num, true);
                //ReservationManager.addReservation(data);
                request.setAttribute("error", ReservationManager.addReservation(data));

                updateDatabase();
                break;
        }

        updatePage(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
        dispatcher.forward(request, response);
    }

    protected void updatePage(HttpServletRequest request) {
        request.setAttribute("tables", TableManager.getTables());
        request.setAttribute("reservations", ReservationManager.getReservations());
        request.setAttribute("current_directory", System.getProperty("user.dir"));
    }

    protected void updateDatabase() {
        TableManager.updateDB();
        ReservationManager.updateDB();
    }
}