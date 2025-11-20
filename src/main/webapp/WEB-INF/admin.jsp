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
        #account_editing {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 30px;
        }
        #table_editing {
            display: flex;
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
        <button onclick="show_account_editing()">Accounts</button>
        <button onclick="show_table_editing()">Tables</button>
    </div>

    <div id="account_editing" style="display: none" >
        <table>
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
                <input type="text" name="username" /><br/>
                <input type="password" name="password" /><br/>
                <input type="text" name="name" /><br/>
                <input type="number" name="auth" /><br/>
                <input type="submit" name="submit_type" value="Add Account" />
                <input type="submit" name="submit_type" value="Edit Account" />
                <input type="submit" name="submit_type" value="Delete Account" />
            </form>

            <form method="post">
                <input type="submit" name="submit_type" value="Save Changes" />
            </form>
        </div>
    </div>

    <script>
        function show_account_editing() {
          var x = document.getElementById('account_editing');
          if (x.style.display === 'none') {
            x.style.display = 'flex';
          } else {
            x.style.display = 'none';
          }
        }
    </script>

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
                <input type="number" name="number" /><br/>
                <input type="text" name="cap" /><br/>
                <input type="number" name="price" /><br/>
                <input type="text" name="location" /><br/>
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

    <script>
        function show_table_editing() {
            var x = document.getElementById('table_editing');
            if (x.style.display === 'none') {
                x.style.display = 'flex';
            } else {
                x.style.display = 'none';
            }
        }
    </script>
</body>
</html>
