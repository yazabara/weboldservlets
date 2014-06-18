<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="test" uri="/WEB-INF/SubStrTaglibDescriptor.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

		<h2><fmt:message key="common.custom.taglib.header" bundle="${common}"/></h2>
		<c:set var="stringVar" value="Substring123"/>
		<c:forEach var="index" begin="2" end="10">
			<p>
				<fmt:message key="common.custom.taglib.p" bundle="${common}">
					<fmt:param value="${stringVar}"/>
					<fmt:param value="1"/>
					<fmt:param value="${index}"/>
				</fmt:message>
				<test:substring input="${stringVar}" start="1" end="${index}"/>
			</p>
		</c:forEach>

    </div>
</div>

<div class="container" >

</div>

<%@include file="WEB-INF/pages/blocks/footer.jsp" %>
</body>
</html>
