<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Registration user</title>
    <jsp:include page="/blocks/header.jsp" />
</head>
<body>
<jsp:include page="/blocks/navbar.jsp" />



    <div class="container border-5" >
        ${message}

            <form method="post" action="/JavaEpam2021_LowCost_war/user/pay">
                <input type="hidden" name="flight" value="${flight.id}">
                <input type="hidden" name="quantity" value="${quantity}">
                <div class="row">
                    <div class="col-md-12 ">
                        (${flight.start}) ${flight.startAirport.name}-${flight.finishAirport.name}
                    </div>
                    <div class="col-md-12 ">
                        ${quantity} - passenger(-s)
                    </div>
                    <div class="col-md-12 ">
                        <input type="checkbox" id="baggage" name="baggage" value="${baggage}">
                        <label for="baggage"> Add baggage(* $)</label><br>
                    </div>
                    <div class="col-md-12 ">
                        <input type="checkbox" id="priority" name="priority" value="${priority}">
                        <label for="priority"> Priority registration (* $)</label><br>
                    </div>


                    <div class="col-sm-12">
                        <input class="send_btn" type="submit" value="Pay">
                    </div>
                </div>
            </form>

    </div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>
