<%@ include file="home.jsp"%>
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