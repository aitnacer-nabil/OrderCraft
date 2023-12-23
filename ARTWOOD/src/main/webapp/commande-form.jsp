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
            <a href="#" class="navbar-brand"> Commande Management App </a>
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
<div class="container col-md-10">
    <div class="card">
        <div class="card-body" id="card-bod">
            <h3 class="text-center">Ajouter une commande</h3>
            <hr>
            <form class="form-horizontal" id="frmClient">

                <table class="table table-bordered">
                    <tr>
                        <th>Client Nom</th>
                        <th>Option</th>
                    </tr>
                    <tr>
                        <td>

                            <!---    <input type="text" class="form-control" placeholder="barcode" id="barcode" name="barcode"  size="25px"  required>-->
                            <select class="form-control" id="clientId" name="clientId"
                                    required>
                                <option value="">Please Select Client</option>
                                <c:forEach var="client" items="${clients}">
                                    <option value='<c:out value='${client.uuid}' />'><c:out
                                            value='${client.name}'/></option>
                                </c:forEach>
                                <script></script>
                            </select>
                        </td>

                        <td>
                            <div class="focusguard" id="focusguard-2" tabindex="8"></div>

                            <button class="btn btn-success" type="button" onclick="addClient()">Add
                            </button>
                    </tr>
                </table>
            </form>
            <form class="form-horizontal" id="frmProduit">

                <table class="table table-bordered">

                    <tr>
                        <th>Product Name</th>
                        <th>Product Description</th>
                        <th>Price</th>
                        <th>Qty Stock</th>
                        <th>QTy</th>
                        <th>Amount</th>
                        <th>Option</th>
                    </tr>
                    <tr>


                        <td>


                            <!---    <input type="text" class="form-control" placeholder="barcode" id="barcode" name="barcode"  size="25px"  required>-->
                            <select class="form-control" id="produitId" name="produitId" onchange="updateProduitInfo()"
                                    required>
                                <option value="">Please Select Produit</option>
                                <c:forEach var="produit" items="${produits}">
                                    <option value='<c:out value='${produit.uuid}' />'><c:out
                                            value='${produit.name}'/></option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>

                            <label id="pdescription" name="pdescription"></label>

                        </td>
                        <td>
                            <label id="pro_price" name="pro_price"></label>

                        </td>
                        <td>
                            <label id="pro_qte_stock" name="pro_qte_stock"></label>

                        </td>
                        <td>
                            <input type="number" class="form-control pro_price" id="qty" name="qty"
                                   placeholder="qty" min="1" value="1" onchange="checkMaxQty()" required>
                        </td>
                        <td>
                            <input type="text" class="form-control" placeholder="total_cost" id="total_cost"
                                   name="total_cost" disabled>
                        </td>
                        <td>
                            <div class="focusguard" id="focusguard-3" tabindex="8"></div>

                            <button class="btn btn-success" type="button" onclick="addproduct()">Add
                            </button>


                    </tr>
                </table>
            </form>
            <table class="table table-bordered" id="product_list">

                <thead>
                <tr>
                    <th style="width: 40px">Remove</th>
                    <th>Nom Client</th>
                    <th>Product Name</th>
                    <th>QTY</th>
                    <th>Amount</th>
                </tr>
                </thead>

                <tbody>

                </tbody>
            </table>
            <div class="focusguard" id="btnAjoute" tabindex="8" ></div>


            <form id="myForm" action="<%=request.getContextPath()%>/Commande/add" method="POST" style="display: none">
                <!-- Ajoutez vos champs de formulaire ici -->
                <input type="hidden" name="para" id="para" value='' /> <!-- Remplacez par vos données JSON réelles -->
                <button  class="btn btn-info" id="submitButton">Ajouter</button>
            </form>
        </div>
    </div>
</div>

<script
        src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>



<script>

    let selectedProduit = null;
    let produits = [];
    let selectedClient = null;
    function Client(id,nom){
        this.nom = nom;
        this.id = id;
    };

    function ProduitOrder(id, nom, qteOrder, amount) {
        this.id = id;
        this.nom = nom;
        this.qteOrder = qteOrder;
        this.amount = amount;
    }

    var produitsArray = [];
    <c:forEach var="produit" items="${produits}">
    var produit = {
        uuid: '${produit.uuid}',
        name: '${produit.name}',
        description: '${produit.description}',
        prix: ${produit.prix},
        qte_stock: ${produit.qte_stock},
        qte_order: 0,
        amount: 0
    };
    produitsArray.push(produit);
    </c:forEach>

    function checkMaxQty() {
        var condition = Number(document.getElementById("qty").value) > selectedProduit.qte_stock;
        if (condition) {

            document.getElementById('qty').value = "";
        } else {
            selectedProduit.qte_order = Number(document.getElementById("qty").value);
            selectedProduit.amount = selectedProduit.qte_order * selectedProduit.prix;
            document.getElementById('total_cost').value = Number(document.getElementById("qty").value) * selectedProduit.prix;
        }

    }

    function addClient() {
// Récupérer les valeurs sélectionnées
        var clientId = document.getElementById('clientId').value;
        var clientName = document.getElementById('clientId').options[document.getElementById('clientId').selectedIndex].text;

        selectedClient = new Client(clientId,clientName);
// Vous pouvez utiliser ces valeurs comme vous le souhaitez (par exemple, les envoyer à la servlet via AJAX)

// Cacher la table client et afficher la table produit
        if (clientId != "") {
            document.getElementById('frmClient').style.display = 'none';
            document.getElementById('frmProduit').style.display = 'display';


// Vous pouvez également utiliser les valeurs clientId et clientName comme vous le souhaitez dans la table produit
// Par exemple, les afficher dans une nouvelle ligne de la table produit
            console.log(clientId + " " + clientName);
        }

    }

    function updateProduitInfo() {
// Récupérer la valeur sélectionnée dans la liste déroulante
        var produitId = document.getElementById('produitId').value;
        console.log("id " + produitId)
// Rechercher le produit correspondant dans la liste produits

// Convertir la liste produits en tableau JavaScript

        console.log("select Index " + document.getElementById('produitId').selectedIndex);
        for (var i = 0; i < produitsArray.length; i++) {

            if (produitsArray[i].uuid === produitId) {
                selectedProduit = produitsArray[i];
                console.log("Select P :" + selectedProduit)
                break;
            }
        }

// Mettre à jour les champs de description, stock et prix avec les valeurs du produit sélectionné
        if (document.getElementById('produitId').selectedIndex === 0) {
            resetProduitTable();
        } else if (selectedProduit) {
            document.getElementById('pdescription').innerText = selectedProduit.description;
            document.getElementById('pro_price').innerText = selectedProduit.prix;
            document.getElementById('pro_qte_stock').innerText = selectedProduit.qte_stock;
            document.getElementById('qty').value = "";
            document.getElementById('total_cost').value = "total_cost";
        } else {
// Réinitialiser les champs si aucun produit n'est sélectionné
            document.getElementById('pdescription').innerText = "";
            document.getElementById('pro_price').innerText = "";
            document.getElementById('pro_qte_stock').innerText = "";
        }
    }

    function addproduct() {
        console.log("Select produit " + selectedProduit.qte_order);
        console.log("includes produit " + produits.includes(selectedProduit));
        if (!selectedProduit || !selectedClient || produits.includes(selectedProduit) || selectedProduit.qte_order === 0 ) {
            return;
        }

        produits.push(selectedProduit);
        showAjouterButton();
        resetProduitTable();
        var uuid = selectedProduit.uuid;
        var table1 =
            "<tr>" +
            "<td><button type='button' name='record' class='btn btn-warning' onclick='deleterow(this)' uuid="+uuid+" >Delete </td>" +
            "<td>" + selectedClient.nom + "</td>" +
            "<td>" + selectedProduit.name + "</td>" +
            "<td>" + selectedProduit.qte_order + "</td>" +
            "<td>" + selectedProduit.amount + "</td>" +

            "</tr>";


        $("#product_list tbody").append(table1);





        $('#subtotal').val(total);
        $('#barcode').val('');
        $('#total').val(total);
        $('#qty').val("1");
        $('#pname').html('');
        $('#pro_price').html('');
        $('#qty').val("1");
        $('#total_cost').val('');
        $('#barcode').focus();
        selectedProduit = null;

    }

    function resetProduitTable() {
        document.getElementById('produitId').selectedIndex = 0;
        document.getElementById('pdescription').innerText = "";
        document.getElementById('pro_price').innerText = "";
        document.getElementById('pro_qte_stock').innerText = "";
        document.getElementById('qty').value = "";
        document.getElementById('total_cost').value = "total_cost";
    }
    let rows = [];
    function deleterow(e)
    {
        // console.log(produits);
        $(e).parent().parent().remove();
        rows.push(e);
     //
        var uuid = $(e).attr('uuid');


        // console.log( typeof (uuid));
        // console.log(uuid);
        produits = produits.filter(produit=> produit.uuid !== uuid);
        showAjouterButton();
        console.log(produits)

    }
    function showAjouterButton(){
        if(produits.length === 0){
            document.getElementById("myForm").style.display ="none";
        } else {
            document.getElementById("myForm").style.display ="inline-block";
        }
    }
    var jsonString;
    function addCommande(){
        if(produits.length === 0 || selectedClient === null)return;

        let sendProduit = produits.map(produi =>({
            uuid:produi.uuid,
            qte_order:produi.qte_order,
            produit_amount : produi.amount
        }));
        let commande ={
            client_id:selectedClient.id,
            list_produits: sendProduit,
        }

        jsonString = JSON.stringify(commande);


    }

    document.getElementById("submitButton").addEventListener("click", function(event) {
        event.preventDefault(); // Empêche le comportement par défaut du bouton
     addCommande();
console.log(JSON.stringify({ data: jsonString }));
console.log("----------------------------------------------");
console.log(jsonString);
        // Vous pouvez ajouter d'autres données au besoin, par exemple :
        // formData.append("autreChamp", "valeur");
        fetch('<%=request.getContextPath()%>/Commande/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: jsonString,
        })
            .then(response => {
                if (response.ok) {
                    // Redirection côté client
                    window.location.href = '<%=request.getContextPath()%>/Commande/list';
                } else {
                    console.error('Erreur lors de la requête fetch : ', response.statusText);
                }
            })
            .catch(error => {
                console.error('Erreur lors de la requête fetch : ', error);
            });
    });
</script>
</body>
</html>