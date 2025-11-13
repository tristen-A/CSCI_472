<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.example.demo.Database.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Portal</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
    <a href="main-servlet">Return to Homepage</a>

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
</body>
</html>
