package com.example.demo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.example.demo.Database.*;

@WebServlet(name = "adminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    private final AccountManager AccManager = new  AccountManager();

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

        updatePage(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin.jsp");
        dispatcher.forward(request, response);
    }

    protected void updatePage(HttpServletRequest request) {
        request.setAttribute("accounts", AccManager.getAccounts());
    }
}