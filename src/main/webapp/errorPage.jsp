<%@ page isErrorPage="true"%>
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
			<h1><fmt:message key="common.error" bundle="${common}"/></h1>
			<p><fmt:message key="common.error.description" bundle="${common}"/></p>
		</div>
	</div>
</div>

<div class="container" >
	<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

	<!-- Stack trace -->
	<jsp:scriptlet>
  		exception.printStackTrace(new java.io.PrintWriter(out));
	</jsp:scriptlet>
</div>

<%@include file="WEB-INF/pages/blocks/footer.jsp" %>
</body>
</html>
