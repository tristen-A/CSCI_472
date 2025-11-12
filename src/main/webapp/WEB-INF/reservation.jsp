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
    <p>testing testing</p>
    <p>Current Directory: ${current_directory} </p>

    <table>
        <tr>
            <th>Table Number</th>
            <th>Capacity</th>
            <th>Price</th>
            <th>Location</th>
            <th>Reserved</th>
        </tr>
        <c:forEach items="${accounts.keySet()}" var="i">
            <tr>
                <td>${i}</td>
                <td>${accounts.get(i)[0]}</td>
                <td>${accounts.get(i)[1]}</td>
                <td>${accounts.get(i)[2]}</td>
                <td>${accounts.get(i)[3]}</td>
            </tr>
        </c:forEach>
    </table>

    <p>Make a reservation:</p>

    <form method="post">
        <input type="number" name="table_num" required /><br/>
        <input type="date" name="date" required /><br/>
        <input type="time" value="time" required /><br/>
        <input type="submit" name="action" value="Submit" />
    </form>
</body>
</html>
