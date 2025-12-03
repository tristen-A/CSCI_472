<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.example.demo.Database.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Portal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">

    <style>

    </style>
</head>
<body>
    <!--<div class="mx-auto pt-2" style="width: 1000px;">
        <form>
          <div class="row mb-3">
            <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
              <input type="email" class="form-control" id="inputEmail3">
            </div>
          </div>
          <div class="row mb-3">
            <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
              <input type="password" class="form-control" id="inputPassword3">
            </div>
          </div>
          <fieldset class="row mb-3">
            <legend class="col-form-label col-sm-2 pt-0">Radios</legend>
            <div class="col-sm-10">
              <div class="form-check">
                <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="option1" checked>
                <label class="form-check-label" for="gridRadios1">
                  First radio
                </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="option2">
                <label class="form-check-label" for="gridRadios2">
                  Second radio
                </label>
              </div>
              <div class="form-check disabled">
                <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="option3" disabled>
                <label class="form-check-label" for="gridRadios3">
                  Third disabled radio
                </label>
              </div>
            </div>
          </fieldset>
          <div class="row mb-3">
            <div class="col-sm-10 offset-sm-2">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck1">
                <label class="form-check-label" for="gridCheck1">
                  Example checkbox
                </label>
              </div>
            </div>
          </div>
          <button type="submit" class="btn btn-primary">Sign in</button>
        </form>
    </div>-->

    <h1 style="text-align: right; font-size: 20px;">Logged in as: ${sessionScope.username}  </h1>
    <a href="main-servlet" class="button">Return to Homepage</a>

    <div class="list-group mx-auto pt-2" style="width: 600px;">
        <c:forEach items="${acc_reservations.values()}" var="cur_res">
            <a href="#" class="list-group-item list-group-item-action" >
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">Table #${cur_res.getTableNum()}</h5>
                    <small>${cur_res.getDate()}</small>
                    <small>${cur_res.getTime()}</small>
                </div>
                <p class="mb-1"> -- Party: ${cur_res.getPartySize()}</p>
                <form method="post">
                    <input type="hidden" name="res_num" value="${cur_res.getResNum()}" />
                    <button type="submit" class="btn btn-outline-danger btn-sm"
                        name="submit_type" value="Cancel Reservation" >
                        Cancel Reservation
                    </button>
                </form>
            </a>
        </c:forEach>
    </div>
</body>
</html>