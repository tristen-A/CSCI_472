<%--
  Created by IntelliJ IDEA.
  User: trons
  Date: 11/10/2025
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
    <h1 style="text-align: center;"><%= "Account Login" %></h1>

    <form method="post">
        <input type="text" name="username" required /><br/>
        <input type="password" name="password" required /><br/>
        <input type="submit" value="Login" />
    </form>
</body>
</html>
