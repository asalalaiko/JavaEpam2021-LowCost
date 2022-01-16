<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Admin - Flight</title>
    <jsp:include page="/blocks/header.jsp" />
</head>
<body>
<jsp:include page="/blocks/navbar.jsp" />
<div class="container border-5" >

    <form class="main_form " action="/JavaEpam2021_LowCost_war/admin/flight/save">
        <div class="row">
            <div class="col-md-12 ">
                <input type="hidden" name="id" value="${flight.id}">
                <input class="form_contril" placeholder="name " type="datetime-local"
                       name="start" value="${flight.start}">
                <select id="startAirport" name="startAirport">
                    <option value="none" selected disabled hidden>Select an Airport</option>
                    <c:forEach items="${airports}" var="airport">
                        <option value=${airport.id}
                                <c:if test="${flight.startAirport.id == airport.id}"> selected="selected"</c:if>
                        >${airport.name}
                        </option>
                    </c:forEach>
                </select>
                <input class="form_contril" placeholder="name " type="datetime-local"
                       name="finish" value="${flight.finish}">
                <select id="finishAirport" name="finishAirport">
                    <option value="none" selected disabled hidden>Select an Airport</option>
                    <c:forEach items="${airports}" var="airport">
                        <option value=${airport.id}
                                <c:if test="${flight.finishAirport.id == airport.id}"> selected="selected"</c:if>
                        >${airport.name}
                        </option>
                    </c:forEach>
                </select>
                <input class="form_contril" placeholder="distance " type="number"
                       name="km" value="${flight.km}" step="1" min="0">

                <select id="plane" name="plane">
                    <option value="none" selected disabled hidden>Select an Plane</option>
                    <c:forEach items="${planes}" var="plane">
                        <option value=${plane.id}
                                <c:if test="${flight.plane.id == plane.id}"> selected="selected"</c:if>
                        >${plane.name}
                        </option>
                    </c:forEach>
                </select>

                <input class="form_contril" placeholder="cost baggage " type="number"
                       name="costBaggage" value="${flight.costBaggage}" step="0.01" min="0">
                <input class="form_contril" placeholder="cost priority " type="number"
                       name="costPriority" value="${flight.costPriority}" step="0.01" min="0">
            </div>
            <div class="col-sm-12">
                <input class="send_btn" type="submit" value="Save">
            </div>
        </div>
    </form>
    <table>
        <tr>
            <th>Id</th>
            <th>Start</th>
            <th>Start Airport</th>
            <th>Finish</th>
            <th>Finish Airport</th>
            <th>Distance</th>
            <th>Plane</th>
            <th>Cost Ticket</th>
            <th>Сost Baggage</th>
            <th>Сost Priority</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${flights}" var="flight">
            <tr>
                <td>${flight.id}</td>
                <td>${flight.start}</td>
                <td>${flight.startAirport.name}(${flight.startAirport.city.name})</td>
                <td>${flight.finish}</td>
                <td>${flight.finishAirport.name}(${flight.finishAirport.city.name})</td>
                <td>${flight.km}</td>
                <td>${flight.plane.name}</td>
                <td>${flight.cost}</td>
                <td>${flight.costBaggage}</td>
                <td>${flight.costPriority}</td>
                <td><a href="<c:url value="/admin/flight/edit?id=${flight.id}"/>">Edit</a></td>
                <td><a href="<c:url value="/admin/flight/del?id=${flight.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>