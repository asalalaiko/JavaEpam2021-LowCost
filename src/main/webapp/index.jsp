<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 06.12.2021
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <jsp:include page="blocks/header.jsp" />

</head>

<body class="d-flex flex-column" >

    <jsp:include page="blocks/navbar.jsp" />

        <div class="container border-5" >
            <h1>Добро пожаловать!</h1>

            ${pageContext.request.contextPath}<br>
            ${sessionScope.user.role}
        </div>



    <jsp:include page="blocks/footer.jsp" />
</body>
</html>
