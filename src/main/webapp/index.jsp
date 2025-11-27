<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Main Page</title>
        <link rel="stylesheet" href="style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>
    <body>
        <h1><%= "Reservation Service - Portal" %></h1>

        <h1 style="text-align: right; font-size: 20px;">Logged in as: ${sessionScope.username}</h1>

        <a href="login">Login Here</a>
        <a href="profile">Profile</a>

        <p>Navigate to one of these three pages to get started;</p>
        <a href="reservation">
            <img id="res-icon" src="Assets/Reservation_Page_Icon.png" alt="Reservation Page" style="width:42px;height:42px;">
        </a>
        <label for="res-icon">Make a Reservation</label><br>
        <a href="admin">
            <img id="admin-icon" src="Assets/Admin_Page_Icon.png" alt="Admin Page" style="width:42px;height:42px;">
        </a>
        <label for="admin-icon">Administrator Portal</label><br>
        <!--
        <form action="hello-servlet" method="post">
            <input type="text" name="username" required /><br/>
            <input type="password" name="password" required /><br/>
            <input type="submit" value="Login" />
        </form>
        -->
    </body>
</html>