<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Produit Management Application</title>
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
            <li><a href="<%=request.getContextPath()%>/Commande/list"
                   class="nav-link">Commande</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Produits</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/Produit/new" class="btn btn-success">Add
                New Produit</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Prix</th>
                <th>Qte stock</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="produit" items="${listProduits}">

                <tr>
                    <td><c:out value="${produit.uuid}" /></td>
                    <td><c:out value="${produit.name}" /></td>
                    <td><c:out value="${produit.description}" /></td>
                    <td><c:out value="${produit.prix}" /></td>
                    <td><c:out value="${produit.qte_stock}" /></td>
                    <td><a href="<%=request.getContextPath()%>/Produit/edit?id=<c:out value='${produit.uuid}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="<%=request.getContextPath()%>/Produit/delete?id=<c:out value='${produit.uuid}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>