<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            <h1>Welcome in Lowcost Company!</h1>

            <form class="main_form " method="post" action="/JavaEpam2021_LowCost_war/home">
                <select class="form_contril" id="startCity" name="startCity">
                    <option value="none" selected disabled hidden>Select an City from</option>
                    <c:forEach items="${cities}" var="city">
                        <option value=${city.id}>
                                ${city.name}
                        </option>
                    </c:forEach>
                </select>
                <select class="form_contril" id="finishCity" name="finishCity">
                    <option value="none" selected disabled hidden>Select an City to</option>
                    <c:forEach items="${cities}" var="city">
                        <option value=${city.id}>
                                ${city.name}
                        </option>
                    </c:forEach>
                </select>
                <input class="send_btn" type="submit" value="Search">
            </form>



            <c:forEach items="${flights}" var="flight">
                ${flight.start} -
                (${flight.startAirport.city.name} - ${flight.finishAirport.city.name}) - ${flight.cost}
                <a href="${pageContext.request.contextPath}/addcart?id=${flight.id}">Add cart</a><br>
            </c:forEach>

        </div>



    <jsp:include page="blocks/footer.jsp" />
</body>
</html>
