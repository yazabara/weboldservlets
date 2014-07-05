<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="bundles.application" var="common" scope="application"/>
<%@attribute name="bookId" required="true"%>
<%@attribute name="bookName" required="true"%>
<%@attribute name="bookAuthor" required="true"%>
<%@attribute name="bookCreateDate" required="true"%>
<%@attribute name="bookDescription" required="true"%>

<c:if test="${not empty bookId}">
    <div class="bs-callout bs-callout-info">
        <h4>
                <span class="label label-primary">
                    <fmt:message key="common.book" bundle="${common}"/>
                </span>
            #${bookId} ${bookName}
        </h4>

        <p>
                <span class="label label-success">
                    <fmt:message key="book.author" bundle="${common}"/>
                </span>
            ${bookAuthor}
        </p>

        <p>
                <span class="label label-success">
                    <fmt:message key="book.description" bundle="${common}"/>
                </span>
            ${bookDescription}
        </p>

        <p>
                <span class="label label-success">
                    <fmt:message key="book.createDate" bundle="${common}"/>
                </span>
           ${bookCreateDate}
        </p>
    </div>

</c:if>

<c:if test="${empty bookId}">
    <div class="bs-callout bs-callout-danger">
        <h4><fmt:message key="book.error" bundle="${common}"/></h4>

        <p><fmt:message key="book.error.description" bundle="${common}"/></p>
    </div>
</c:if>