<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.example.demo.Database.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Profile Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">

    <style>

    </style>
</head>
<body>
    <h1 style="text-align: right; font-size: 20px;">Logged in as: ${sessionScope.username}  </h1>
    <a href="main-servlet" class="button">Return to Homepage</a>

    <div class="list-group mx-auto pt-2" style="width: 600px;">
        <c:forEach items="${acc_reservations.values()}" var="cur_res">
            <a href="#" class="list-group-item list-group-item-action" >
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">Table #${cur_res.getTableNum()}</h5>
                    <small>${cur_res.getDate()}</small>
                    <small>${cur_res.getTime()}</small>
                </div>
                <p class="mb-1"> -- Party: ${cur_res.getPartySize()}</p>
                <form method="post">
                    <input type="hidden" name="res_num" value="${cur_res.getResNum()}" />
                    <button type="submit" class="btn btn-outline-danger btn-sm"
                        name="submit_type" value="Cancel Reservation" >
                        Cancel Reservation
                    </button>
                </form>
            </a>
        </c:forEach>
    </div>
</body>
</html>