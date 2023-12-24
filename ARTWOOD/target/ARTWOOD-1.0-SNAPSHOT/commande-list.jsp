<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        .status-complet {
            color: white;
            background-color: green;
        }
        .custom-modal .modal-dialog {
            max-width: 80%; /* Ajustez la largeur maximale selon vos besoins */
        }
        .status-annulle {
            color: white;
            background-color: red;
        }

        .status-preparation {
            color: white;
            background-color: orange;
        }
        table{
            text-align: center;
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
        <h3 class="text-center">liste de commandes</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/Commande/add" class="btn btn-success">Add
                nouveau  Commande</a>
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
                    <td><c:out value="${commande.uuid}"/></td>
                    <td><c:out value="${commande.dateAjoute}"/></td>
                    <td><c:out value="${commande.dateUpdate}"/></td>
                    <td><c:out value="${commande.client.name}"/></td>
                    <td class="status-${commande.commandeStatus.name().toLowerCase()}">
                         <c:out value="${commande.commandeStatus}"/></td>
                    <td><c:out value="${commande.total_amount}"/></td>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#changeStatusModal" uuid="${commande.uuid}" onclick="setCommandeId(this)">
                            Changer le statut
                        </button>
                    </td>
                    <td><a
                            href="<%=request.getContextPath()%>/Commande/delete?id=<c:out value='${commande.uuid}' />">Delete</a>
                    </td>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-commande='<c:out value="${commande.toJson().toString()}"/>'
                                data-target="#detailModal" onclick="showDetail(this)">
                            Detail
                        </button>
                    </td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
        <!-- Bouton pour afficher la fenêtre contextuelle -->


        <!-- Fenêtre contextuelle -->
        <div class="modal fade" id="changeStatusModal" tabindex="-1" role="dialog"
             aria-labelledby="changeStatusModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="changeStatusModalLabel">Sélectionner un nouveau statut</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Formulaire pour sélectionner un nouveau statut -->
                        <form action="status" method="POST">
                            <input type="hidden" id="commandeId" name="commandeId" value="">
                            <div class="form-group">
                                <label for="statusSelect">Statut :</label>
                                <select class="form-control" id="statusSelect" name="statut">
                                    <option value="PREPARATION">PREPARATION</option>
                                    <option value="COMPLET">COMPLET</option>
                                    <option value="ANNULLE">ANNULLE</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Soumettre</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <!-- Fenêtre modale pour les détails -->
        <div class="modal fade custom-modal" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="detailModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title" id="detailModalLabel">Detail Pour Commande : <span id="uuid"></span></h1>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Fermer">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h2>Client</h2>
                        <!-- Contenu des détails dans un tableau -->
                        <table class="table table-bordered" id="table_client">
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

                            </tbody>
                        </table>
                        <h2>Produits</h2>
                        <table class="table table-bordered" id="table_produit">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Prix</th>

                            </tr>
                            </thead>
                            <tbody>

                            <!-- } -->
                            </tbody>
                        </table>

                    </div>
                    <div class="modal-footer">
                        <!-- Bouton pour fermer la fenêtre modale -->
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Inclure les fichiers JavaScript Bootstrap (jQuery requis) -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

        <!-- Inclure les fichiers JavaScript Bootstrap (jQuery requis) -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script>
            function setCommandeId(button) {

                var uuid = button.getAttribute('uuid');
                document.getElementById("commandeId").value = uuid;
                // Utiliser la valeur de uuid comme nécessaire
                console.log('UUID de la commande : ' + uuid);

            }

            function afficherDetails(uuid,client,produits) {
                // Récupérer l'objet commande depuis l'attribut data-commande


                // Utiliser l'objet commande comme nécessaire
                console.log(uuid);
                console.log(client);
                console.log(produits);

                // Ici, vous pouvez mettre à jour le contenu de la fenêtre modale avec les détails de la commande
                // Par exemple, en utilisant jQuery pour manipuler le DOM

            }
            function showDetail(button) {
                var commandeDataString = button.getAttribute('data-commande');

                // Parsez la chaîne JSON en un objet JavaScript
                var commandeObj = JSON.parse(commandeDataString);
                $("#detailModal #uuid").innerText = commandeObj.uuid;

                var table1 =
                    "<tr>" +
                    "<td>" + commandeObj.client.uuid + "</td>" +
                    "<td>" + commandeObj.client.name + "</td>" +
                    "<td>" + commandeObj.client.email + "</td>" +
                    "<td>" + commandeObj.client.adress + "</td>" +
                    "<td>" + commandeObj.client.phone + "</td>" +
                    "</tr>";
                $("#table_client tbody").append(table1);
                console.log(commandeObj);
                commandeObj.produitList.forEach(produit => {
                    var table2 =
                        "<tr>" +
                        "<td>" + produit.uuid + "</td>" +
                        "<td>" + produit.name + "</td>" +
                        "<td>" + produit.description + "</td>" +
                        "<td>" + produit.prix + "</td>" +
                        "</tr>";
                    $("#table_produit tbody").append(table2);
                })

            }
            $('#detailModal').on('hidden.bs.modal', function () {
                // Code à exécuter lorsque la fenêtre modale se ferme
                console.log("La fenêtre modale s'est fermée");
                $("#table_client tbody").empty();
                $("#table_produit tbody").empty();
            });
        </script>
    </div>
</div>
</body>
</html>