<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
    <%@include file="blocks/headSection.jsp" %>
    <title>
        <fmt:message key="common.pages.groupList" bundle="${common}"/>
    </title>
</head>
<body>
<jsp:include page="blocks/header.jsp"/>

<div class="container content-wrapper">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">
            <fmt:message key="groupList.heading" bundle="${common}"/>
        </div>

        <c:choose>
            <c:when test="${not empty groupList && fn:length(groupList) > 0}">
                <!-- Table -->
                <table class="table table-hover">
                    <tr>
                        <th><fmt:message key="group.id" bundle="${common}"/></th>
                        <th><fmt:message key="group.name" bundle="${common}"/></th>
                    </tr>
                    <tbody>
                    <c:forEach var="group" items="${groupList}">
                        <tr>
                            <td><a href="/groups/group?id=${group.id}">${group.id}</a></td>
                            <td><a href="/groups/group?id=${group.id}">${group.name}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="bs-callout bs-callout-danger">
                    <h4><fmt:message key="groupList.empty" bundle="${common}"/></h4>
                    <p><fmt:message key="groupList.empty.description" bundle="${common}"/></p>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<%@include file="blocks/footer.jsp" %>
</body>
</html>
