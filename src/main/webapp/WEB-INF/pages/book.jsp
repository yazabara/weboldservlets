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

    <c:if test="${not empty book}">
        <div class="bs-callout bs-callout-info">
            <h4>
                <span class="label label-primary">
                    <fmt:message key="common.book" bundle="${common}"/>
                </span>
                #${book.id} ${book.name}
            </h4>

            <p>
                <span class="label label-success">
                    <fmt:message key="book.author" bundle="${common}"/>
                </span>
                    ${book.author}
            </p>

            <p>
                <span class="label label-success">
                    <fmt:message key="book.description" bundle="${common}"/>
                </span>
                    ${book.description}
            </p>

            <p>
                <span class="label label-success">
                    <fmt:message key="book.createDate" bundle="${common}"/>
                </span>
                <fmt:formatDate value="${book.createDate}"/>
            </p>
        </div>


        <div class="bs-callout bs-callout-warning">
            <c:choose>
                <c:when test="${not empty groups && fn:length(groups) > 0}">
                    <h4><fmt:message key="common.groups" bundle="${common}"/> :</h4>
                    <p>
                        <c:forEach var="group" items="${groups}">
                            <a href="/groups/group?id=${group.id}" type="button" class="btn btn-warning dropdown-toggle">${group.name}</a>
                        </c:forEach>
                    </p>
                </c:when>
            </c:choose>
        </div>

    </c:if>

    <c:if test="${empty book}">
        <div class="bs-callout bs-callout-danger">
            <h4><fmt:message key="book.error" bundle="${common}"/></h4>

            <p><fmt:message key="book.error.description" bundle="${common}"/></p>
        </div>
    </c:if>

</div>

<%@include file="blocks/footer.jsp" %>
</body>
</html>
