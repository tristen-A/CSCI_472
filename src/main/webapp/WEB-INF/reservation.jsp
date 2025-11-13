<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reservations</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
    <a href="main-servlet">Return to Homepage</a>
    <br/>
    <!--<p>Current Directory: ${current_directory} </p>-->

    <p>Resturaunt Tables</p>
    <table>
        <tr>
            <th>Table Number</th>
            <th>Capacity</th>
            <th>Price</th>
            <th>Location</th>
            <th>Reserved</th>
        </tr>
        <c:forEach items="${tables.keySet()}" var="i">
            <tr>
                <td>${i}</td>
                <td>${tables.get(i)[0]}</td>
                <td>${tables.get(i)[1]}</td>
                <td>${tables.get(i)[2]}</td>
                <td>${tables.get(i)[3]}</td>
            </tr>
        </c:forEach>
    </table>
    <br/>

    <p>Make a reservation:</p>

    <form method="post">
        <input type="hidden" name="acc_usern" value="${sessionScope.username}" /><br/>
        <input type="number" name="table_num" required /><br/>
        <input type="date" name="date" required /><br/>
        <input type="time" value="time" required /><br/>
        <input type="submit" name="action" value="Submit" />
    </form>
    <br/>

    <p>Current Reservations:</p>

    <table>
        <tr>
            <th>Number</th>
            <th>Account</th>
            <th>Table</th>
            <th>Date</th>
            <th>Time</th>
        </tr>
        <c:forEach items="${reservations.values()}" var="cur_res">
            <tr>
                <td>${cur_res.getResNum()}</td>
                <td>${cur_res.getAccUsern()}</td>
                <td>${cur_res.getTableNum()}</td>
                <td>${cur_res.getDate()}</td>
                <td>${cur_res.getTime()}</td>
            </tr>
        </c:forEach>
    </table>

    <p> ${error} </p>
</body>
</html>
