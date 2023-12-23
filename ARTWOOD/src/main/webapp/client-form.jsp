<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <c:if test="${client != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${client == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${client != null}">
                                Edit client
                            </c:if>
                            <c:if test="${client == null}">
                                Add New client
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${client != null}">
                        <input type="hidden" name="id" value="<c:out value='${client.uuid}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Client Name</label> <input type="text"
                                                          value="<c:out value='${client.name}' />" class="form-control"
                                                          name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Client Email</label> <input type="text"
                                                           value="<c:out value='${client.email}' />"
                                                           class="form-control"
                                                           name="email">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Client Adress</label> <input type="text"
                                                            value="<c:out value='${client.adress}' />"
                                                            class="form-control"
                                                            name="adress">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Client phone</label> <input type="text"
                                                            value="<c:out value='${client.phone}' />"
                                                            class="form-control"
                                                            name="phone">
                    </fieldset>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>