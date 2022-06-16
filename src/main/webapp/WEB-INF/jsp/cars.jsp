<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <body>
    <sec:authorize access="!isAuthenticated()">
        <a  href="${pageContext.request.contextPath}/signup">Register</a></br>
        <a  href="${pageContext.request.contextPath}/signin">Login</a></br>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a  href="${pageContext.request.contextPath}/logout">Logout</a></br>
    </sec:authorize>
        <h1>All Cars</h1>
        <div align="center">
            <table border="1" cellpadding="5">
                <caption><h2>List of users</h2></caption>
                <tr>
                    <th>Model</th>
                    <th>Speed</th>
                </tr>
                <c:forEach var="car" items="${cars}">
                    <tr>
                        <td><c:out value="${car.name}" /></td>
                        <td><c:out value="${car.speed}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
