<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Registration user</title>
    <jsp:include page="blocks/header.jsp" />
</head>
<body>
<jsp:include page="blocks/navbar.jsp" />

<div class="container border-5" >
    <form class="main_form " action="/JavaEpam2021_LowCost_war/register">
        <div class="row">
            <div class="col-md-12 ">
                <input class="form_contril" placeholder="Login " type="text" name="login">
            </div>
            <div class="col-md-12">
                <input class="form_contril" placeholder="Password" type="password" name="password">
            </div>
            <div class="col-md-12">
                <input class="form_contril" placeholder="Email" type="text" name="email">
            </div>
            <div class="col-md-12">
                <input class="form_contril" placeholder="First name" type="text" name="fname">
            </div>
            <div class="col-md-12">
                <input class="form_contril" placeholder="Last name" type="text" name="lname">
            </div>
            <div class="col-sm-12">
                <input class="send_btn" type="submit" value="Register">
            </div>
        </div>
    </form>

</div>
<jsp:include page="blocks/footer.jsp" />
</body>
</html>
