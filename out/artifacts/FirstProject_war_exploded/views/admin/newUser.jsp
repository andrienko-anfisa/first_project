<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add new user</title>
</head>
<body>

<div>
    <div>
        <h2>It's time to add new user!</h2>
    </div>
    <div>
        <%
            if (request.getAttribute("login") != null) {
                out.println("<p>User '" + request.getAttribute("login") + "' added!</p>");
            }
        %>
        <form action="/admin/newUser" method="post">
            <label>Name:
                <input type="text" name="login"><br/>
            </label>
            <label>Password:
                <input type="password" name="password"><br/>
            </label>
            <label>Role:
                <p><input name="user" value="user" type="checkbox"> user</p>
                <p><input name="admin" value="admin" type="checkbox"> admin</p>
                <br/></label>
            <button type="submit">Save</button>
        </form>
    </div>
</div>

<div>
    <button onclick="location.href='/admin/allUsers'">Back to table users</button>
</div>
</body>

</html>