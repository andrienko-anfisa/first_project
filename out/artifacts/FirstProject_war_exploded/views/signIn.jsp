
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Sign in</title>

</head>
<body>
<div>
    <div>
        <h2>It's time to sign in</h2>
    </div>
    <div>
        <form action="/signIn" method="post">
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
    <button onclick="location.href='/signUp'">Sign up</button>
</div>
</body>
</html>
