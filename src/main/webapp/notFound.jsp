<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<jsp:include page="WEB-INF/pages/blocks/header.jsp">
	<jsp:param name="active" value="Home"/>
</jsp:include>

<div class="jumbotron">
	<div class="container ">
		<div class="bs-callout bs-callout-danger">
			<h1><fmt:message key="common.404" bundle="${common}"/></h1>
			<p><fmt:message key="common.404.description" bundle="${common}"/></p>
		</div>
	</div>
</div>

<div class="container" >

</div>

<%@include file="WEB-INF/pages/blocks/footer.jsp" %>
</body>
</html>
