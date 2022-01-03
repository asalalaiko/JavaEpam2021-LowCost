<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<header >
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="#">Lowcost Company</a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Admin</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/admin/city/">City</a>
                    <a class="dropdown-item" href="#">----------------------</a>
                    <a class="dropdown-item" href="#">----------------------</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">----------------------</a>
                </div>
            </li>
        </ul>
        <c:if test="${sessionScope.user ne null}">
            <div class="nav-item">Hello ${sessionScope.user.login}!</div>
            <a class="nav-link my-2 my-sm-0 " href="<c:url value = "/logout"/>">Logout</a>
        </c:if>
        <c:if test="${sessionScope.user eq null}">
            <a class="nav-link my-2 my-sm-0 " href="/JavaEpam2021_LowCost_war/login.jsp">Login</a>
        </c:if>
    </div>
</nav>
</header>



