<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Registration user</title>
    <jsp:include page="blocks/header.jsp" />
</head>
<body>
<jsp:include page="blocks/navbar.jsp" />

<div class="container border-5" >
    <form action="/JavaEpam2021_LowCost_war/login">
        <div class="row justify-content-center  p-1">
            <label for="login">Login:</label><br>
            <input type="text" id="login" name="login"><br>
        </div>
        <div class="row justify-content-center  p-1">
            <label for="password">Password:</label> <br>
            <input type="password" id="password" name="password"><br> <br>
        </div>
        <div class="row justify-content-center  p-1">
            <input type="submit" value="Sign In">
            <a class="link-secondary" href="/JavaEpam2021_LowCost_war/register.jsp">Register new user</a>

        </div>
    </form>
</div>
<jsp:include page="blocks/footer.jsp" />
</body>
</html>
