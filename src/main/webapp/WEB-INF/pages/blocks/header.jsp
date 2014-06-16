<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<nav role="navigation" class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">

		<div class="navbar-header">
			<button type="button" data-toggle="collapse"
					data-target=".navbar-collapse"
					class="navbar-toggle collapsed">
				<span class="sr-only">
                    <fmt:message key="common.pages.home" bundle="${common}"/>
                </span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="${pageContext.request.contextPath}/home" class="navbar-brand">
                <fmt:message key="common.pages.home" bundle="${common}"/>
            </a>
		</div>

		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
                <li class="active">
                    <a href="${pageContext.request.contextPath}/">
                        <fmt:message key="common.pages.home" bundle="${common}"/>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/contact">
                        <fmt:message key="common.pages.contacts" bundle="${common}"/>
                    </a>
                </li>
			</ul>

			<form class="navbar-form navbar-right" role="form" action="/bookListServlet/booklist" method="post">
				<div class="form-group">
					<input name="question" type="text" placeholder="Question" class="form-control">
				</div>
				<button type="submit" class="btn btn-success">find</button>
			</form>
		</div>


	</div>
</nav>