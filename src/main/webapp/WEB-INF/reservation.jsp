<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reservations</title>
    <link rel="stylesheet" href="../style.css">

    <style>
        .container {
          display: grid;
          grid-template-columns: auto auto auto auto auto;
          background-color: dodgerblue;
          padding: 10px;
        }
        .container div {
          background-color: #f1f1f1;
          border: 1px solid black;
          padding: 5px;
          font-size: 30px;
          text-align: center;
        }
    </style>
</head>
<body>
    <a href="main-servlet">Return to Homepage</a>
    <br/>
    <p>Current Directory: ${current_directory} </p>

    <p>Resturaunt Tables</p>
    <table>
        <tr>
            <th>Table Number</th>
            <th>Capacity</th>
            <th>Price</th>
            <th>Location</th>
            <th>Reserved</th>
        </tr>
        <c:forEach items="${tables.values()}" var="cur_tbl">
            <tr>
                <td>${cur_tbl.getNumber()}</td>
                <td>${cur_tbl.getCap()}</td>
                <td>${cur_tbl.getPrice()}</td>
                <td>${cur_tbl.getLocation()}</td>
                <td>${cur_tbl.checkReservation()}</td>
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

    <p>Number of tables: ${numberOfTables}</p>

    <div class="container">
        <c:forEach items="${tables.keySet()}" var="i">
            <div>
                <p>Table Number: ${i}</p>
                <p>${tables.get(i).getLocation()}</p>
                <!--<button id="tblBtn" src="../Assets/Reservation_Page_Icon.png" style="margin-top:20px; padding:10px 20px; font-size:16px; cursor:pointer;">
                    Image
                </button>-->
                <input type="image" src="../Assets/Reservation_Page_Icon.png" />
            </div>
        </c:forEach>
    </div>

    <p> ${error} </p>
</body>
</html>
