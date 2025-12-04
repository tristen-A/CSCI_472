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

    <a style="line-height: 30px;" href="main-servlet">Return to Homepage</a>

    <form style="line-height: 30px;" method="post">
        <input type="text" id="usern_field" name="username" required /><br/>
        <label for="usern_field">Username</label><br>
        <input type="password" id="pass_field" name="password" required /><br/>
        <label for="pass_field">Password</label><br>
        <input type="submit" value="Login" />
    </form>

    <p> ${error} </p>
</body>
</html>
