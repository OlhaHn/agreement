<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 6/10/18
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<style>
    #username, #password {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    #login {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    #login:hover {
        background-color: #45a049;
    }

    #maindiv {
        background-color: #f2f2f2;
        padding-top: 5%;
        padding-left: 200px;
        padding-right: 200px;
        padding-bottom: 10%;
    }

    #form-box {
        border: #4E4E4E;
        background-color: #2c4557;
    }

    .labels {
        color: white;
    }
</style>

<body>
<div id="maindiv" style="height: 59%">
<div id="form-box">
<form style="padding: 100px" id="loginForm" method="post" action="login" modelAttribute="loginBean">
    <label class="labels" path="username">Login</label>
    <input id="username" name="username" path="username" /><br>
    <label class="labels" path="username">Hasło</label>
    <input type="password" id="password" name="password" path="password" /><br>
    <input id="login" type="submit" value="Zaloguj" />

</form>
</div>
</div>
</body>
</html>
