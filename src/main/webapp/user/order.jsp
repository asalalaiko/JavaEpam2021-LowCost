<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Registration user</title>
    <jsp:include page="/blocks/header.jsp" />
</head>
<body>
<jsp:include page="/blocks/navbar.jsp" />

${Message}

    <div class="container border-5" >

        <c:forEach items="${quantity}" var="quantity">
            <form method="post" action="/JavaEpam2021_LowCost_war/user/pay">
                <div class="row">
                    <div class="col-md-12 ">
                        <input class="form_contril" placeholder="Passenger " type="text" name="passenger" >
                    </div>
                    <div class="col-md-12 ">
                        <input type="checkbox" id="baggage" name="baggage">
                        <label for="baggage"> Add baggage</label><br>
                    </div>
                    <div class="col-md-12 ">
                        <input type="checkbox" id="priority" name="baggage">
                        <label for="priority"> Priority registration</label><br>
                    </div>
                    <div class="col-sm-12">
                        <input class="send_btn" type="submit" value="Pay">
                    </div>
                </div>
            </form>

        </c:forEach>



    </div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>
