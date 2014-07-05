<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <%@include file="WEB-INF/pages/blocks/headSection.jsp" %>
    <title>
        <fmt:message key="common.pages.book" bundle="${common}"/>
    </title>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <div class="container ">
            <form class="form-signin" role="form" action="j_security_check">
                <h2 class="form-signin-heading">Please sign in</h2>

                <input type="text" class="form-control" placeholder="username" name="j_username" required="" autofocus="">
                <input type="text" class="form-control" placeholder="Password" name="j_password" required="">

                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>