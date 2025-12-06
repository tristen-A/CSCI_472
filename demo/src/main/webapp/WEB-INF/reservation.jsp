<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reservations</title>
    <!--<link rel="stylesheet" href="../style.css">-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">

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
          text-align: center;
        }
    </style>
</head>
<body>
    <h1 style="text-align: right; font-size: 20px;">Logged in as: ${sessionScope.username}  </h1>
    <a href="main-servlet" class="button">Return to Homepage</a>

    <p>Resturaunt Tables</p>
    <!--<table>
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
    </table>-->
    <br/>

    <div class="modal fade" id="tableConfirm" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>Modal body text goes here.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <div class="container" id="table_container" data-amount="${tables.size()}">
        <c:forEach items="${tables.keySet()}" var="i">
            <div>
                <!--<form method="post">
                    <input type="hidden" name="table_num" value="${i}" /><br/>
                    <input id="btn_img${i}" type="image" src="" alt="Submit" width="64" height="64">
                </form>-->
                <!--<a id="btn${i}" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#tableConfirm">
                    <img class="img-fluid" id="btn_img${i}" src="" width="64" height="64"/>
                </a>-->

                <button id="btn${i}" onclick="select_table(${i});">
                    <img class="img-fluid" id="btn_img${i}" src="" width="64" height="64"/>
                </button>
                <p style="font-size: 16px;" >Table Number: ${i}</p>
                <p style="font-size: 16px;" >Seating: ${tables.get(i).getCap()}</p>
                <p style="font-size: 16px;" >${tables.get(i).getLocation()}</p>

                <div id="table_selection_${i}" style="display: none" data-reserved="${tables.get(i).checkReservation()}">
                    <p style="font-size: 24px;" >Reserve table?</p>

                    <form method="post" style="height: 10%; ">
                        <input type="hidden" name="acc_usern" value="${sessionScope.username}" /><br/>
                        <input type="hidden" name="table_num" value="${i}" /><br/>

                        <label for="date">Date</label>
                        <input type="date" id="date" name="date" required /><br/>

                        <label for="time">Time</label>
                        <input type="time" id="time" name="time" required /><br/>

                        <label for="party_size">Party Size (between 1, ${tables.get(i).getCap()})</label>
                        <input type="range" id="party_size" name="party_size" min="1" max="${tables.get(i).getCap()}" required /><br/>

                        <input type="submit" name="action" value="Submit" />
                     </form>
                </div>
            </div>
        </c:forEach>

        <script src="JS/reservation.js"></script>
    </div>

    <p style="text-align: center; font-size: 32px; background-color: tomato;"> ${error} </p>
</body>
</html>
