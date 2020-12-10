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
        <h2>It's time to edit user!</h2>
    </div>

    <div>

        <form action="/admin/editUser" method="post">
            <label>ID:<input name="id" readonly value="${id}"/> <br/></label>
            <label>Login:<input type="text" name="login" value="${login}"/> <br/></label>
            <label>Password: <input type="password" name="password" value="${password}"/><br/></label>
            <label>Role:
                <p><input name="user" value="user" type="checkbox"> user</p>
                <p><input name="admin" value="admin" type="checkbox"> admin</p>
                <br/></label>
            <button type="submit">Save</button>
        </form>

    </div>
</div>

<div>
    <button onclick="location.href='/admin/allUsers'">Back to table</button>
</div>
</body>

</html>
