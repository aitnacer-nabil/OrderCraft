<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Client Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="#" class="navbar-brand"> OrderCraft - Gestion des Commandes </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/Client/list"
                   class="nav-link">Clients</a></li>
            <li><a href="<%=request.getContextPath()%>/Produit/list"
                   class="nav-link">Produits</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Clients</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/Client/new" class="btn btn-success">Add
                New Client</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Adress</th>
                <th>Phone</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="client" items="${listClient}">

                <tr>
                    <td><c:out value="${client.uuid}" /></td>
                    <td><c:out value="${client.name}" /></td>
                    <td><c:out value="${client.email}" /></td>
                    <td><c:out value="${client.adress}" /></td>
                    <td><c:out value="${client.phone}" /></td>
                    <td><a href="<%=request.getContextPath()%>/Client/edit?id=<c:out value='${client.uuid}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="<%=request.getContextPath()%>/Client/delete?id=<c:out value='${client.uuid}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>