<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
    <%
     boolean test= true;
    %>
<c:out value="HEllo World" ></c:out>
<br/>
<h1>HEllo</h1>
<a href="commande-form.jsp">Hello Servlet</a>
</body>
</html>