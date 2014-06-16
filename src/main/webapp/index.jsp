<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <%@include file="WEB-INF/pages/blocks/headSection.jsp" %>
    <title>
        <fmt:message key="common.pages.home" bundle="${common}"/>
    </title>
</head>
<body>
<jsp:include page="WEB-INF/pages/blocks/header.jsp" >
    <jsp:param name="active" value="Home" />
</jsp:include>

<div class="jumbotron">
    <div class="container ">
        <h1>
            <fmt:message key="common.welcomeTo" bundle="${common}">
                <fmt:param value="Book Portal v0.2"/>
            </fmt:message>
        </h1>
        <p>
            <fmt:message key="common.projectInfo" bundle="${common}"/>
        </p>
    </div>
</div>

<div class="container" >

</div>

<%@include file="WEB-INF/pages/blocks/footer.jsp" %>
</body>
</html>
