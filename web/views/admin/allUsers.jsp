<%@ page import="app.entities.UsersDataSet" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<table border="1" cellpadding="7">
    <tr>
        <td>ID</td>
        <td>Логин</td>
        <td>Пароль</td>
        <td>Действия</td>
        <td>Полномочия</td>
    </tr>
    </div>
    <form action="/admin/allUsers" method="get">


        <c:forEach items="${users}" var="user">
            <tr>
                <td> ${user.id}</td>
                <td> ${user.login}</td>
                <td> ${user.password}</td>
                <td><a href="/admin/editUser?id=<c:out value='${user.id}' />">Edit</a>
                <a href="/admin/deleteUser?id=<c:out value='${user.id}' />">Delete</a></td>
               <td><c:forEach items="${user.authorities}" var="auth">
                  ${auth.role}
               </c:forEach>
               </td>

            </tr>
        </c:forEach>

        </div>
    </form>
</table>

<div>
    <button onclick="location.href='/admin/newUser'">Add new user</button>
</div>

</body>
</html>