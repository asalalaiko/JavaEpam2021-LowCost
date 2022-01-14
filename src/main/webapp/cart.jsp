<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>CART</title>
    <jsp:include page="/blocks/header.jsp" />
</head>
<body>
<jsp:include page="/blocks/navbar.jsp" />
<div class="container border-5" >
    <table>
        <tr>
            <th>No</th>
            <th>Flight</th>
            <th>Price</th>
            <th> ticket</th>
            <th>Action</th>

        </tr>
        <c:forEach items="${flights}" var="flight">
        <form class="main_form " action="${pageContext.request.contextPath}/user/order">
            <input type="hidden" name="id" value="${flight.id}">
            <tr>
                <td>${flight.id}</td>
                <td>(${flight.startAirport.city.name} - ${flight.finishAirport.city.name})</td>
                <td>${flight.cost}</td>
                <td> <input placeholder="" type="number"
                            name="quantity"  value="1" step="1" min="1"></td>
                <td>
                    <input class="send_btn" type="submit" value="To Order">
                    <a href="<c:url value="/cart/del?id=${flight.id}"/>">Delete</a>
                </td>
            </tr>
        </form>
        </c:forEach>
    </table>
</div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>