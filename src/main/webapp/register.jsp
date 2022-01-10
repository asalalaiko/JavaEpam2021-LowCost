<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Registration user</title>
    <jsp:include page="blocks/header.jsp" />
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
<jsp:include page="blocks/navbar.jsp" />



<div class="container border-5" >
    <form class="main_form " method="post" action="/JavaEpam2021_LowCost_war/register">
        <div class="row">

            <p style="color: #ff0000;">${errorMessage}</p>

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
            <div class="col-md-12">
                <div class="g-recaptcha" data-sitekey="6Lc_mwQeAAAAAHnK7PHKAHn43oG3Ppm1ybs18rwr"></div>
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
