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

    <div class="container">
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
                <p style="font-size: 16px;" >${tables.get(i).getLocation()}</p>

                <div id="table_selection_${i}" style="display: none" data-reserved="${tables.get(i).checkReservation()}">
                    <p style="font-size: 32px;" >Reserve table?</p>

                    <form method="post">
                        <input type="hidden" name="acc_usern" value="${sessionScope.username}" /><br/>
                        <input type="hidden" name="table_num" value="${i}" /><br/>
                        <input type="date" name="date" required /><br/>
                        <input type="time" value="time" required /><br/>
                        <input type="submit" name="action" value="Submit" />
                     </form>
                </div>
            </div>
        </c:forEach>

        <script>
            var prev_elmt_num = null;

            function select_table(num) {
                var element = document.getElementById("table_selection_" + num);
                var prev_element = document.getElementById("table_selection_" + prev_elmt_num);

                if (element.dataset.reserved === "true") {
                    window.alert("This table is already reserved.");
                    return;
                }

                if ((element.style.display === 'none') || (num !== prev_elmt_num)) {
                    if (prev_elmt_num != null) {
                        prev_element.style.display = 'none';
                    }
                    element.style.display = 'block';
                    prev_elmt_num = num;
                } else {
                    element.style.display = 'none';
                    prev_element.style.display = 'none';
                    prev_elmt_num = null;
                }
            }

            function load_table_icons() {
                const images = ["Assets/Available_Table_Icon.png", "Assets/Reserved_Table_Icon.png"];
                for (let i = 1; i < 6; i++) {
                    const imageElement = document.getElementById("btn_img" + i);
                    const tableSelector = document.getElementById("table_selection_" + i);
                    if (tableSelector.dataset.reserved === "false") {
                        imageElement.src = images[0];
                    } else {
                        imageElement.src = images[1];
                    }
                }
            }

            document.addEventListener("DOMContentLoaded", function() {
                load_table_icons();
            });
        </script>
    </div>

    <p>Make a reservation:</p>

    <form method="post">
        <input type="hidden" name="acc_usern" value="${sessionScope.username}" /><br/>
        <input type="number" name="table_num" required /><br/>
        <input type="date" name="date" required /><br/>
        <input type="time" value="time" required /><br/>
        <input type="submit" name="action" value="Submit" />
    </form>
    <br/>

    <!--<table>
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
    </table>-->

    <p> ${error} </p>
</body>
</html>
