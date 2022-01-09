<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Admin - User list</title>
    <jsp:include page="/blocks/header.jsp" />
</head>
<body>
<jsp:include page="/blocks/navbar.jsp" />
<div class="container border-5" >


    <table>
        <tr>
            <th>Id</th>
            <th>Login</th>
            <th>First name</th>
            <th>Last name</th>
            <th>E-mail</th>
            <th>Created</th>
            <th>Locked</th>
            <th>Role</th>
            <th>Activated</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.created}</td>
                <td>${user.locked}</td>
                <td>${user.role}</td>
                <td>${user.active}</td>

                <td><a href="<c:url value="/admin/user/lock?id=${user.id}"/>">
                    <c:if test="${user.locked}">UNLOCK</c:if>
                    <c:if test="${!user.locked}">LOCK</c:if>
                </a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>