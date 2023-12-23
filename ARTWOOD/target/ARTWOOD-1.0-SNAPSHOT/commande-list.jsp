<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Commande Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style>
        /* Style par défaut pour les liens */




        /* Style lorsqu'un lien est activé (clic) */
        .nav-link:active {
            color: black; /* ou la couleur que vous souhaitez pour le clic */
            /* Ajoutez d'autres styles selon vos besoins */
        }
    </style>
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
        <h3 class="text-center">List Commande</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/Commande/add" class="btn btn-success">Add
                New Commande</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Date Creation</th>
                <th>Date Mis a jour</th>
                <th>Nom Client</th>
                <th>Status</th>
                <th>Total (DH)</th>

            </tr>
            </thead>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="commande" items="${commandes}">

                <tr>
                    <td><c:out value="${commande.uuid}" /></td>
                    <td><c:out value="${commande.dateAjoute}" /></td>
                    <td><c:out value="${commande.dateUpdate}" /></td>
                    <td><c:out value="${commande.client.name}" /></td>
                    <td><c:out value="${commande.commandeStatus}" /></td>
                    <td><c:out value="${commande.total_amount}" /></td>
                    <td><a href="<%=request.getContextPath()%>/Commande/edit?id=<c:out value='${commande.uuid}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="<%=request.getContextPath()%>/Commande/delete?id=<c:out value='${commande.uuid}' />">Delete</a>
                        <a
                                href="<%=request.getContextPath()%>/Commande/detail?id=<c:out value='${commande.uuid}' />">Detail</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>