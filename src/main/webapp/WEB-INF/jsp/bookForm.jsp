<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<title>Book form</title>
</head>
<body>
	<div class="generic-container">
		<h1>Book form</h1>
		<c:if test="${status==true}" >
			<p style="color: red">Book with supplied name exists.</p>
		</c:if>
		<form:form action="${pageContext.request.contextPath}/books" modelAttribute="book">
			<div><label> Book title : <input type="text" name="name" maxlength="100" size="100"/> </label></div>
			<div><input type="submit" value="Add Book"/></div>
		</form:form>
	</div>
</body>
</html>
