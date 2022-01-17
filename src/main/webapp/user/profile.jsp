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

        Your tickets:
        <table>
            <tr>
                <th>Date</th>
                <th>Fligth</th>
                <th>Baggage</th>
                <th>Priority</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${tickets}" var="ticket">
                <tr>
                    <td>${ticket.flight.start}</td>
                    <td>(${ticket.flight.startAirport.city.name} - ${ticket.flight.finishAirport.city.name})</td>
                    <td>${ticket.baggage}</td>
                    <td>${ticket.priority}</td>
                    <td>${ticket.status}</td>
                </tr>
            </c:forEach>
        </table>

    </c:if>


</div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>
