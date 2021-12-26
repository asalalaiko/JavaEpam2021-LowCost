<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 08.12.2021
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
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
    <form action="/login">
        <label for="login">Login:</label><br>
        <input type="text" id="login" name="login"><br>

        <label for="pass">Password:</label> <br>
        <input type="password" id="pass" name="pass"><br> <br>
        <input type="submit" value="Submit">
    </form>
</div>
<jsp:include page="blocks/footer.jsp" />
</body>
</html>
