<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Admin - Plane</title>
    <jsp:include page="/blocks/header.jsp" />
</head>
<body>
<jsp:include page="/blocks/navbar.jsp" />
<div class="container border-5" >

    <form class="main_form " action="/JavaEpam2021_LowCost_war/admin/plane/save">
        <div class="row">
            <div class="col-md-12 ">
                <input type="hidden" name="id" value="${plane.id}">
                <input class="form_contril" placeholder="name " type="text"
                       name="name" value="${plane.name}">
                <input class="form_contril" placeholder="model " type="text"
                       name="model" value="${plane.model}">
                <input class="form_contril" placeholder="seats " type="number"
                       name="seats" value="${plane.seats}" step="1" min="0">
                <input class="form_contril" placeholder="cost 1km " type="number"
                       name="costKM" value="${plane.costKM}" step="0.01" min="0">
            </div>
            <div class="col-sm-12">
                <input class="send_btn" type="submit" value="Save">
            </div>
        </div>
    </form>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Model</th>
            <th>Seats</th>
            <th>Cost 1km</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${planes}" var="plane">
            <tr>
                <td>${plane.id}</td>
                <td>${plane.name}</td>
                <td>${plane.model}</td>
                <td>${plane.seats}</td>
                <td>${plane.costKM}</td>
                <td><a href="<c:url value="/admin/plane/edit?id=${plane.id}"/>">Edit</a></td>
                <td><a href="<c:url value="/admin/plane/del?id=${plane.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>