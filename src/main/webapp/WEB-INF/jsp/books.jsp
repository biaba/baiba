<%@ include file="home.jsp"%>
<html>
<body>

<h1>Books</h1>
    <c:if test="${status==true}" >
        <p style="color: green">New book with name ${bookName} added</p>
    </c:if>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of books</h2></caption>

        <%
            String prevPage = request.getAttribute("prevPage").toString();
            String nextPage = request.getAttribute("nextPage").toString();
        %>
        <c:if test="${prevPage !=999}">
            <a href="${pageContext.request.contextPath}/books/<%= prevPage%>">Prev</a>
        </c:if><br>
        <c:if test="${nextPage !=999}">
            <a href="${pageContext.request.contextPath}/books/<%= nextPage%>">Next</a>
        </c:if>

        <tr>
            <th>id</th>
            <th>Name</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td><c:out value="${book.id}" /></td>
                <td><c:out value="${book.name}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
