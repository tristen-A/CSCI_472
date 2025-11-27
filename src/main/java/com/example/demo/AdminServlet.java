package com.example.demo;

import com.example.demo.Core.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "adminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    private final AccountManager AccManager = new  AccountManager();
    private final TableManager TableManager = new  TableManager();
    private final ReservationManager ReservationManager = new  ReservationManager(TableManager);

    @Override
    public void init() {}
    public void destroy() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        updatePage(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String editing_page = request.getParameter("editing_page");

        if (editing_page.equals("accounts")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String auth = String.valueOf(request.getParameter("auth"));

            String[] data = {username, password, name, auth};

            String submit_type = request.getParameter("submit_type");
            switch (submit_type) {
                case "Add Account":
                    AccManager.addAccount(data);
                    break;
                case "Edit Account":
                    AccManager.editAccount(data[0], data);
                    break;
                case "Delete Account":
                    AccManager.deleteAccount(data[0]);
                    break;
                case "Save Changes":
                    AccManager.updateDB();
                    break;
            }
        } else if (editing_page.equals("tables")) {
            int number = Integer.parseInt(request.getParameter("number"));
            String cap = String.valueOf(request.getParameter("cap"));
            String price = String.valueOf(request.getParameter("price"));
            String location = request.getParameter("location");

            String[] data = {cap, price, location, "false"};

            String submit_type = request.getParameter("submit_type");
            switch (submit_type) {
                case "Add Table":
                    TableManager.addTable(number, data);
                    break;
                case "Edit Table":
                    TableManager.editTable(number, data);
                    break;
                case "Delete Table":
                    TableManager.deleteTable(number);
                    break;
                case "Save Changes":
                    TableManager.updateDB();
                    break;
            }
        } else if (editing_page.equals("reservations")) {
            int res_number = Integer.parseInt(request.getParameter("res_number"));
            String table_number = String.valueOf(request.getParameter("table_number"));
            String date = request.getParameter("date");
            String time = request.getParameter("time");

            String[] data = {"", table_number, date, time};

            String submit_type = request.getParameter("submit_type");
            switch (submit_type) {
                case "Edit Reservation":
                    ReservationManager.editReservation(res_number, data);
                    break;
                case "Delete Reservation":
                    ReservationManager.deleteReservation(res_number);
                    break;
                case "Save Changes":
                    ReservationManager.updateDB();
                    break;
            }
        }

        updatePage(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin.jsp");
        dispatcher.forward(request, response);
    }

    protected void updatePage(HttpServletRequest request) {
        request.setAttribute("accounts", AccManager.getAccounts());
        request.setAttribute("tables", TableManager.getTables());
        request.setAttribute("reservations", ReservationManager.getReservations());
    }
}