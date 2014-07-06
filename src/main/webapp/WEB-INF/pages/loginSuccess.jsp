<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <%@include file="blocks/headSection.jsp" %>
    <title>
        <fmt:message key="common.pages.book" bundle="${common}"/>
    </title>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="container content-wrapper">
    <div class="starter-template">
        <h1>Login complete</h1>
        <p class="lead">I wish you good
            <a href="bookListServlet/booklist">book-surfing</a>
        </p>

    </div>
</div>
<%@include file="blocks/footer.jsp" %>
</body>
</html>
