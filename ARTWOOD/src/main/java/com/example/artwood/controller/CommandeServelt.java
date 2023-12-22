package com.example.artwood.controller;

import com.example.artwood.model.Client;
import com.example.artwood.model.Produit;
import com.example.artwood.service.CommandeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "commande",urlPatterns = {"/Commande","/Commande/*"})
public class CommandeServelt extends HttpServlet {
    private static   final Logger logger = LogManager.getLogger(CommandeServelt.class);
    CommandeService commandeService;

    public CommandeServelt() {
        commandeService = new CommandeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath() + (req.getPathInfo() != null ? req.getPathInfo() : "");

        logger.info("Commande Action: " + action);
        if (action.contains("/Commande/component/")) return;
        switch (action) {
            case "/Commande/add":
                logger.info("Inserting Commande..."); // Log the case
                showAddForm(req, resp);
                break;


            default:
                logger.info("Listing Commande...");

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
        String action = request.getServletPath() + (request.getPathInfo() != null ? request.getPathInfo() : "");

        logger.info("Action: " + action);
        switch (action) {
            case "/Commande/add":
                addCommande(request,response);
                break;

        }
    }

    private void addCommande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Start To Add Commande to list");
        logger.info(request.getParameter("para"));
        response.sendRedirect("add");
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Client> clients = commandeService.getClients();
        List<Produit> produits = commandeService.getAllProduits();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/commande-form.jsp");
        request.setAttribute("clients",clients);
        request.setAttribute("produits",produits);
        dispatcher.forward(request, response);
        logger.info("Forwarded to commande-form.jsp");
    }
}
