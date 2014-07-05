<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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

    <div class="starter-template">
        <h1>Login error</h1>
        <p class="lead">Try again.</p>
    </div>

</div>
</body>
</html>
