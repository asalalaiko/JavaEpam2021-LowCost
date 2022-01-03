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

        <form class="main_form " action="/admin/city/save">
            <div class="row">
                <div class="col-md-12 ">
                    <input class="form_contril" placeholder="name " type="text"
                           name="name" >
                </div>
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="Create">
                </div>
            </div>
        </form>
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${cities}" var="city">
                <tr>
                <td>${city.id}</td>
                <td>${city.name}</td>
                <td><a href="<c:url value="/admin/city/edit?id=${city.id}"/>">Edit</a></td>
                <td><a href="<c:url value="/admin/city/del?id=${city.id}"/>">Delete</a></td>
                </tr>
                </c:forEach>
        </table>
    </div>
<jsp:include page="/blocks/footer.jsp" />
</body>
</html>