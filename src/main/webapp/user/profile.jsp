<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Profile user</title>
    <jsp:include page="/blocks/header.jsp" />
</head>
<body>
<jsp:include page="/blocks/navbar.jsp" />

<div class="container border-5" >
    <c:if test="${sessionScope.user.active == 'FALSE'}">
        <h1>Sorry, you are not activated</h1>
    </c:if>

    <c:if test="${sessionScope.user.active == 'TRUE'}">
        <h1>User - ${user.login} </h1>
    </c:if>


</div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>
