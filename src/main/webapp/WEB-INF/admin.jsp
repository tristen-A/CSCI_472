<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.example.demo.Database.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Portal</title>
    <link rel="stylesheet" href="../style.css">

    <style>
        #container {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 60%;
            margin: 0 auto;
        }

        #account_editing {
            display: block;
            justify-content: center;
            align-items: center;
            padding: 30px;
        }
        #table_editing {
            display: block;
            justify-content: center;
            align-items: center;
            padding: 30px;
        }
        #reservation_editing {
            display: block;
            justify-content: center;
            align-items: center;
            padding: 30px;
        }
        #button_tab {
            margin: auto;
            width: 75%;
            border: 3px solid #73AD21;
            padding: 10px;
        }
        a.button {
            padding: 1px 6px;
            border: 1px outset buttonborder;
            border-radius: 3px;
            color: buttontext;
            background-color: buttonface;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <h1 style="text-align: right; font-size: 20px;">Logged in as: ${sessionScope.username}</h1>
    <a href="main-servlet" class="button">Return to Homepage</a>

    <div id="button_tab">
        <button onclick="show_editing_menu('account_editing');">Accounts</button>
        <button onclick="show_editing_menu('table_editing');">Tables</button>
        <button onclick="show_editing_menu('reservation_editing');">Reservations</button>
    </div>

    </br>

    <div id="container">
        <div id="account_editing" style="display: none" >
            <table style="display: flex">
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Name</th>
                    <th>Auth</th>
                </tr>
                <c:forEach items="${accounts.values()}" var="cur_acc">
                    <tr>
                        <td>${cur_acc.getUsername()}</td>
                        <td>${cur_acc.getPassword()}</td>
                        <td>${cur_acc.getName()}</td>
                        <td>${cur_acc.getAuth()}</td>
                    </tr>
                </c:forEach>
            </table>

            <br/>

            <div id="account_controls" >
                <form method="post">
                    <input type="hidden" name="editing_page" value="accounts" />

                    <label for="username">Username:</label>
                    <input type="text" name="username" id="username" /><br/>

                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password" /><br/>

                    <label for="name">Name:</label>
                    <input type="text" name="name" id="name" /><br/>

                    <label for="auth">Auth Level:</label>
                    <input type="number" name="auth" id="auth" /><br/>

                    <input type="submit" name="submit_type" value="Add Account" />
                    <input type="submit" name="submit_type" value="Edit Account" />
                    <input type="submit" name="submit_type" value="Delete Account" />
                </form>

                <form method="post">
                    <input type="submit" name="submit_type" value="Save Changes" />
                </form>
            </div>
        </div>

        <div id="table_editing" style="display: none" >
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

            <div id="table_controls" >
                <form method="post">
                    <input type="hidden" name="editing_page" value="tables" />

                    <label for="number">Table Number:</label>
                    <input type="number" name="number" id="number" /><br/>

                    <label for="cap">Table Capacity:</label>
                    <input type="number" name="cap" id="cap" /><br/>

                    <label for="price">Price:</label>
                    <input type="number" name="price" id="price" /><br/>

                    <label for="location">Location:</label>
                    <input type="text" name="location" id="location" /><br/>

                    <input type="hidden" name="reserved" value="false" />

                    <input type="submit" name="submit_type" value="Add Table" />
                    <input type="submit" name="submit_type" value="Edit Table" />
                    <input type="submit" name="submit_type" value="Delete Table" />
                </form>

                <form method="post">
                    <input type="submit" name="submit_type" value="Save Changes" />
                </form>
            </div>
        </div>

        <div id="reservation_editing" style="display: none" >
            <div style="clear: both">
                <table>
                    <tr>
                        <th>Reservation Number</th>
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
            </div>

            <br/>

            <div id="reservation_controls" style="clear: both">
                <form method="post">
                    <input type="hidden" name="editing_page" value="reservations" />

                    <label for="res_number">Reservation Number:</label>
                    <input type="number" name="res_number" id="res_number"/><br/>

                    <label for="table_number">Table Number:</label>
                    <input type="number" name="table_number" id="table_number" /><br/>

                    <label for="date">Date:</label>
                    <input type="text" name="date" id="date" /><br/>

                    <label for="time">Time:</label>
                    <input type="text" name="time" id="time" /><br/>

                    <input type="submit" name="submit_type" value="Edit Reservation" />
                    <input type="submit" name="submit_type" value="Delete Reservation" />
                </form>

                <form method="post">
                    <input type="submit" name="submit_type" value="Save Changes" />
                </form>
            </div>
        </div>

        <script>
            const tabs = ['account_editing', 'table_editing', 'reservation_editing'];

            function show_editing_menu(menu) {
                var x = document.getElementById(menu);
                if (x.style.display === 'none') {
                    x.style.display = 'block';

                    for (let i=0; i<tabs.length; i++) {
                        if (tabs[i] !== menu) {
                            let item = document.getElementById(tabs[i])
                            item.style.display = 'none';
                        }
                    }
                }
            }
        </script>
    </div>
</body>
</html>
