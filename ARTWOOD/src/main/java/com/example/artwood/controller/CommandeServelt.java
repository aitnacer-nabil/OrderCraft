package com.example.artwood.controller;

import com.example.artwood.dto.OrderDTO;
import com.example.artwood.dto.ProduitDTO;
import com.example.artwood.model.Client;
import com.example.artwood.model.Commande;
import com.example.artwood.model.Produit;
import com.example.artwood.service.CommandeService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "Commande", urlPatterns = {"/", "/Commande", "/Commande/*"})
public class CommandeServelt extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(CommandeServelt.class);
    CommandeService commandeService;

    public CommandeServelt() {
        commandeService = new CommandeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath() + (req.getPathInfo() != null ? req.getPathInfo() : "");

        logger.info("Commande Action: " + action);

        switch (action) {
            case "/Commande/add":
                logger.info("Inserting Commande..."); // Log the case
                showAddForm(req, resp);
                break;
            case "/Commande/edit":
                logger.info("Show Edit Form Commande");
                showEditFormCommande(req,resp);
                break;


            default:
                logger.info("Listing Commande...");
                listCommandes(req, resp);
                break;
        }
    }

    private void changeStatutCommande(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String uuid = req.getParameter("commandeId");
        String status = req.getParameter("statut");
        if(commandeService.changeStatutCommande(uuid,status)){
            resp.sendRedirect("list");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Error.jsp");

            dispatcher.forward(req, resp);
        }
    }

    private void showEditFormCommande(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String uuid = req.getParameter("id");
        logger.info("showing edit form for commande id " + uuid );
        Commande commande = commandeService.getCommandeById(uuid);

        logger.info("showing edit form for commande  " +commande );
        resp.sendRedirect("list");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath() + (req.getPathInfo() != null ? req.getPathInfo() : "");

        logger.info(" POST Action: " + action);
        switch (action) {
            case "/Commande/add":
                addCommande(req, resp);
                break;
            case "/Commande/status":
                logger.info("Change status commande ");
                changeStatutCommande(req,resp);
                break;
            default:
                logger.info("Listing Commande...");
                listCommandes(req, resp);
                break;

        }
    }

    private void addCommande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Start To Add Commande to list");

        // Récupérer le corps de la requête
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // Utiliser Gson pour désérialiser le corps JSON en un objet Java
        Gson gson = new Gson();

        // Vous pouvez maintenant accéder aux propriétés de l'objet JSON
        OrderDTO orderDTO = gson.fromJson(requestBody.toString(), OrderDTO.class);

        // Maintenant, vous pouvez utiliser l'objet OrderDTO


        Commande commande = commandeService.addCommande(orderDTO);
        logger.info("commander return from service : " , commande);
        // Redirection après traitement
        response.sendRedirect("list");
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Client> clients = commandeService.getClients();
        List<Produit> produits = commandeService.getAllProduits();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/commande-form.jsp");
        request.setAttribute("clients", clients);
        request.setAttribute("produits", produits);
        dispatcher.forward(request, response);
        logger.info("Forwarded to commande-form.jsp");
    }

    private void listCommandes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     List< Commande> commandes = commandeService.getAllCommandes();
        request.setAttribute("commandes",commandes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/commande-list.jsp");
        dispatcher.forward(request, response);
        logger.info("Forwarded to commande-list.jsp");
    }
}
