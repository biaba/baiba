<%@ include file="home.jsp"%>
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

    <form th:action="@{/login}" method="post" id="loginForm">
        <div><label> User Name : <input type="text" name="username" id="userName"/> </label></div>
        <div><label> Password: <input type="password" name="password" id="passWord"/> </label></div>
        <div><input type="submit" value="Log In" id="login"/></div>
    </form>
    </body>
</html>