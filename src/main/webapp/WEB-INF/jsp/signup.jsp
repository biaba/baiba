<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Register</title>
</head>
    <body>
    <h1>Register</h1>
    <c:if test="${not empty param.userNameTaken}" >
        <p style="color: red">Username taken. Try again.</p>
    </c:if>
        <form th:action="@{/signup}" method="post">
            <div><label> User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Register"/></div>
        </form>
    </body>
</html>