<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Admin - City</title>
    <jsp:include page="/blocks/header.jsp" />
</head>
<body>
<jsp:include page="/blocks/navbar.jsp" />
<div class="container border-5" >

             <a href="<c:url value="/admin/airport"/>">Airports</a> <br>
             <a href="<c:url value="/admin/city"/>">Cities</a> <br>
             <a href="<c:url value="/admin/flight"/>">Flights</a> <br>
             <a href="<c:url value="/admin/plane"/>">Planes</a> <br>
             <a href="<c:url value="/admin/user"/>">Users</a> <br>

</div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>