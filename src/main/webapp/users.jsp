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
    <title>Users</title>
    <jsp:include page="blocks/header.jsp" />
</head>
<body>
<jsp:include page="blocks/navbar.jsp" />

<div class="container border-5" >
    <c:choose>
        <c:when test="${requestScope.users ne null}">
            <table>
                <tr>
                    <th>Id</th>
                    <th>Login</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Created</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${requestScope.users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.login}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.created}</td>
                        <td><a href="/delUser?userId=${user.id}">Delete user </a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <c:out value="No users found" />
        </c:otherwise>
    </c:choose>

    <c:if test="${deletedId ne null}">User with id ${deletedId} was deleted successfully</c:if>

    <c:if test="${addedId ne null}">User with id ${addedId} was added successfully</c:if>


    <form action="/addUser">
        <label for="login">Login:</label><br>
            <input type="text" id="login" name="login" value="Login"><br>
        <label for="fname">First name:</label><br>
            <input type="text" id="fname" name="fname" value="John"><br>
        <label for="lname">Last name:</label> <br>
            <input type="text" id="lname" name="lname" value="Doe"><br> <br>
        <input type="submit" value="Submit">
    </form>
</div>
<jsp:include page="blocks/footer.jsp" />
</body>
</html>
