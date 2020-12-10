<%@ page import="java.util.List" %>
<%@ page import="app.entities.UsersDataSet" %><%--
  Created by IntelliJ IDEA.
  User: anfis
  Date: 20.11.2020
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>My super project!</title>
</head>
<body>
<!-- header -->
<div>
  <h1>Welcome to my first project</h1>
  <text> If you see this you're signed in</text>
</div>

<button onclick="location.href='/admin/allUsers'">Show all users</button>

</body>
</html>