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

    <div class="container">
        <c:forEach items="${tables.keySet()}" var="i">
            <div>
                <!--<form method="post">
                    <input type="hidden" name="table_num" value="${i}" /><br/>
                    <input id="btn_img${i}" type="image" src="" alt="Submit" width="64" height="64">
                </form>-->
                <a id="btn${i}" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#tableConfirm">
                    <img class="img-fluid" id="btn_img${i}" src="" width="64" height="64"/>
                </a>
                <p style="font-size: 16px;" >Table Number: ${i}</p>
                <p style="font-size: 16px;" >${tables.get(i).getLocation()}</p>
            </div>
        </c:forEach>

        <script>
            function load_table_icons() {
                const images = ["Assets/Available_Table_Icon.png", "Assets/Reserved_Table_Icon.png"];
                for (let i = 1; i < 6; i++) {
                    const imageElement = document.getElementById("btn_img" + i);
                    imageElement.src = images[0];
                }
            }

            document.addEventListener("DOMContentLoaded", function() {
                load_table_icons();
            });
        </script>
    </div>

    <p> ${error} </p>
</body>
</html>
