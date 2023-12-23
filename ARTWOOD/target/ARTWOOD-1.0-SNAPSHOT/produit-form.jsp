<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Produits Management Application</title>
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
            <a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${produit != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${produit == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${produit != null}">
                                Edit produit
                            </c:if>
                            <c:if test="${produit == null}">
                                Add New produit
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${produit != null}">
                        <input type="hidden" name="id" value="<c:out value='${produit.uuid}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label> Produit name </label> <input type="text"
                                                             value="<c:out value='${produit.name}' />"
                                                             class="form-control"
                                                             name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Produit description</label> <input type="text"
                                                                  value="<c:out value='${produit.description}' />"
                                                                  class="form-control"
                                                                  name="description">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Produit prix</label> <input type="number"
                                                          value="<c:out value='${produit.prix}' />"
                                                          class="form-control"
                                                          name="prix">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Produit qte_stock</label> <input type="text"
                                                            value="<c:out value='${produit.qte_stock}' />"
                                                            class="form-control"
                                                            name="qte_stock">
                    </fieldset>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>