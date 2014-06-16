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

	<c:if test="${not empty error}" >
		<div class="bs-callout bs-callout-danger">
			<h4><fmt:message key="${error}" bundle="${common}"/></h4>

			<p><fmt:message key="${error}.description" bundle="${common}"/></p>
		</div>
	</c:if>

	<form class="container" action="/addBook/addBook" method="post">
		<div class="input-group input-group-lg">
			<span class="input-group-addon">@</span>
			<input name="name" type="text" class="form-control" placeholder="Book name">
		</div>

		<div class="input-group">
			<span class="input-group-addon">@</span>
			<input type="text" name="desc" class="form-control" placeholder="book description">
		</div>

		<div class="input-group">
			<span class="input-group-addon">@</span>
			<input name="author" type="text" class="form-control" placeholder="book author">
		</div>

		<div class="form-group">
			<div class='input-group date' id='datetimepicker5' data-date-format="DD.MM.YYYY">
				<input name="date" type='text' class="form-control" />
                    <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                    </span>
			</div>
			<script type="text/javascript">
				$(function () {
					$('#datetimepicker5').datetimepicker({
						pickTime: false
					});
				});
			</script>
		</div>
		<input type="submit" class="btn btn-primary btn-lg" role="button" value="Submit"/>
	</form>


</div>

<%@include file="blocks/footer.jsp" %>
</body>
</html>
