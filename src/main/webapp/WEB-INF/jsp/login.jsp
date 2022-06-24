<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
    <c:if test="${param.error!=null}">
        <p style="color: red">You entered wrong credentials!</p>
    </c:if>
    <c:if test="${not empty param.addUserSuccess}" >
    <p style="color: green">You are registered: ${param.savedUser}. Log in.</p>
    </c:if>
    <h1>Login</h1>

    <form th:action="@{/login}" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><input type="submit" value="Log In"/></div>
    </form>
    </body>
</html>