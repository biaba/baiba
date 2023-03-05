<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
    <sec:authorize access="!isAuthenticated()">
        <span id="logedIn">
        <a  href="${pageContext.request.contextPath}/signup" id="register">Register</a></br>
        <a  href="${pageContext.request.contextPath}/signin" id="login">Login</a></span>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
    <span id="logedOut">
        <a  href="${pageContext.request.contextPath}/logout" id="logout">Logout</a></br>
        <a  href="${pageContext.request.contextPath}/books" id="books">Books</a></br>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a  href="${pageContext.request.contextPath}/books/form" id="booksForm">Add book</a></br>
            </sec:authorize>
        </sec:authorize></span>
</body>
</html>