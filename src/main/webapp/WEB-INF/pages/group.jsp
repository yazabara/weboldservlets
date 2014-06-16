<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
    <%@include file="blocks/headSection.jsp" %>
    <title>
        <fmt:message key="common.pages.group" bundle="${common}"/>
    </title>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>

<div class="container content-wrapper">

    <c:if test="${not empty group}">
        <div class="bs-callout bs-callout-info">
            <h4>
                <a href="/groupList/groupList" class="btn btn-warning dropdown-toggle" ><fmt:message key="common.group" bundle="${common}"/></a>
                #${group.id} ${group.name}
            </h4>

            <p>
                <span class="label label-success">
                    <fmt:message key="group.description" bundle="${common}"/>
                </span>
                    ${group.description}
            </p>

        </div>

        <c:if test="${fn:length(group.books) > 1}">
            <c:set var="bookList" value="${group.books}"/>
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">
                    <h4><fmt:message key="common.books" bundle="${common}"/></h4>
                </div>

                <c:choose>
                    <c:when test="${not empty bookList && fn:length(bookList) > 0}">
                        <!-- Table -->
                        <table class="table table-hover">
                            <tr>
                                <th><fmt:message key="book.id" bundle="${common}"/></th>
                                <th><fmt:message key="book.name" bundle="${common}"/></th>
                                <th><fmt:message key="book.author" bundle="${common}"/></th>
                                <th><fmt:message key="book.createDate" bundle="${common}"/></th>
                            </tr>
                            <tbody>
                            <c:forEach var="book" items="${bookList}">
                                <tr>
                                    <td><a href="/book/book?id=${book.id}">${book.id}</a></td>
                                    <td><a href="/book/book?id=${book.id}">${book.name}</a></td>
                                    <td><a href="/book/book?id=${book.id}">${book.author}</a></td>
                                    <td><a href="/book/book?id=${book.id}"><fmt:formatDate
                                            value="${book.createDate}"/></a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="bs-callout bs-callout-danger">
                            <h4><fmt:message key="bookList.empty" bundle="${common}"/></h4>

                            <p><fmt:message key="bookList.empty.description" bundle="${common}"/></p>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </c:if>


    </c:if>

    <c:if test="${empty group}">
        <div class="bs-callout bs-callout-danger">
            <h4><fmt:message key="group.error" bundle="${common}"/></h4>

            <p><fmt:message key="group.error.description" bundle="${common}"/></p>
        </div>
    </c:if>

</div>

<%@include file="blocks/footer.jsp" %>
</body>
</html>
