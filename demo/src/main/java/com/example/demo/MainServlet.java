package com.example.demo;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "mainServlet", value = "/main-servlet")
public class MainServlet extends HttpServlet {

    @Override
    public void init() {}
    public void destroy() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page_request = request.getParameter("page_request");
        request.setAttribute("req", page_request);

        if (page_request.equals("profile")) {
            if (!(request.getSession().getAttribute("username") == null)) {
                response.sendRedirect("profile");
            } else {
                request.setAttribute("error", "Not currently logged in!");
            }
        } else if (page_request.equals("login")) {
            response.sendRedirect("login");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
