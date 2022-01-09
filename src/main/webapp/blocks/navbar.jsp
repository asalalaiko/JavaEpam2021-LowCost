<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<header >
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">Lowcost Company</a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

        </ul>
        <c:if test="${sessionScope.user ne null}">
            <div class="nav-item">Hello ${sessionScope.user.login}!</div>
            <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <a class="nav-link my-2 my-sm-0 "  href="${pageContext.request.contextPath}/admin/profile.jsp">Profile</a>
            </c:if>
            <c:if test="${sessionScope.user.role == 'USER'}">
                <a class="nav-link my-2 my-sm-0 "  href="${pageContext.request.contextPath}/user/profile.jsp">Profile</a>
            </c:if>
            <a class="nav-link my-2 my-sm-0 " href="<c:url value = "/logout"/>">Logout</a>
        </c:if>
        <c:if test="${sessionScope.user eq null}">
            <a class="nav-link my-2 my-sm-0 " href="${pageContext.request.contextPath}/login.jsp">Login</a>
        </c:if>
    </div>
</nav>
</header>



