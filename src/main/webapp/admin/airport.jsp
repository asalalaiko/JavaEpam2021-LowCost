<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Admin - Airport</title>
    <jsp:include page="/blocks/header.jsp" />
</head>
<body>
<jsp:include page="/blocks/navbar.jsp" />
    <div class="container border-5" >

        <form class="main_form " action="/JavaEpam2021_LowCost_war/admin/airport/save">
            <div class="row">
                <div class="col-md-12 ">
                    <input type="hidden" name="id" value="${airport.id}">
                    <input class="form_contril" placeholder="name " type="text"
                           name="name" value="${airport.name}">
                    <input class="form_contril" placeholder="tax " type="number"
                           name="tax" value="${airport.tax}" step="0.01" min="0">
                    <select id="city" name="city">
                        <option value="none" selected disabled hidden>Select an City</option>
                        <c:forEach items="${cities}" var="city">
                        <option value=${city.id}
                                <c:if test="${airport.city.id == city.id}"> selected="selected"</c:if>
                            >${city.name}
                        </option>
                        </c:forEach>
                    </select>
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
                <th>TAX</th>
                <th>Airport</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${airports}" var="airport">
                <tr>
                    <td>${airport.id}</td>
                    <td>${airport.name}</td>
                    <td>${airport.tax}</td>
                    <td>${airport.city.name}</td>
                    <td><a href="<c:url value="/admin/airport/edit?id=${airport.id}"/>">Edit</a></td>
                    <td><a href="<c:url value="/admin/airport/del?id=${airport.id}"/>">Delete</a></td>
                </tr>
                </c:forEach>
        </table>
    </div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>