<%--
  Created by IntelliJ IDEA.
  User: anfis
  Date: 28.11.2020
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<div>
    <div>
        <h2>It's time to add new user!</h2>
    </div>
    <div>
        <form action="/signUp" method="post">
            <label>Name:
                <input type="text" name="login"><br/>
            </label>
            <label>Password:
                <input type="password" name="password"><br/>
            </label>
            <button type="submit">Save</button>
        </form>
    </div>
</div>
<div>
    <button onclick="location.href='/signIn'">Back to sign in</button>
</div>
</body>
</html>
