<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

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



    <fmt:formatDate value="${book.createDate}" var="dateStr"/>
    <tags:book bookId="${book.id}" bookName="${book.name}" bookAuthor="${book.author}"
               bookCreateDate="${dateStr}" bookDescription="${book.description}"/>

    <c:if test="${not empty book}">
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


</div>

<%@include file="blocks/footer.jsp" %>
</body>
</html>
