package com.example.artwood.produit;


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

@WebServlet(name = "Produit", urlPatterns = {"/Produit", "/Produit/*"})

public class ProduitServelt extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ProduitServelt.class);
    private ProduitService produitService;

    public void init() {
        this.produitService = new ProduitService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info(request.getServletPath());

        String action = request.getServletPath() + (request.getPathInfo() != null ? request.getPathInfo() : "");

        logger.info("Action: " + action);
        switch (action) {
            case "/Produit/new":
                logger.info("Showing add form Produit...");
                showAddForm(request, response);
                break;
            case "/Produit/delete":
                logger.info("Deleting Produit..."); // Log the case
                deleteProduit(request, response);
                break;
            case "/Produit/edit":
                logger.info("Showing edit form Produit..."); // Log the case
                showEditForm(request, response);
                break;
            default:
                logger.info("Listing Produit...");
                listProduits(request, response);
                break;


        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath() + (request.getPathInfo() != null ? request.getPathInfo() : "");

        logger.info("Action: " + action);
        switch (action) {

            case "/Produit/insert":
                logger.info("Inserting Produit..."); // Log the case

                insertProduit(request, response);
                break;

            case "/Produit/update":
                logger.info("Updating Produit...");
                updateProduit(request, response);
                break;
            default:
                logger.info("Listing Produits...");
                listProduits(request, response);
                break;


        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/produit-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Produit produit = produitService.getProduitById(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/produit-form.jsp");
        request.setAttribute("produit", produit);
        dispatcher.forward(request, response);
        logger.info("Forwarded to produit-form.jsp");
    }

    private void insertProduit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Inserting produit...");
        produitService.addProduit(generateProduitFromRequestParam(request));
        response.sendRedirect("list");
        logger.info("produit inserted and redirected to list");
    }

    private void updateProduit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        produitService.updateProduit(getIdFromRequestParam(request), generateProduitFromRequestParam(request));
        response.sendRedirect("list");
    }

    private Produit generateProduitFromRequestParam(HttpServletRequest request) {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String prix = request.getParameter("prix");
        String qte_stock = request.getParameter("qte_stock");

        return new Produit(name,description,Float.parseFloat(prix),Integer.parseInt(qte_stock));
    }

    private String getIdFromRequestParam(HttpServletRequest request) {
        return request.getParameter("id");
    }

    private void deleteProduit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        produitService.deleteProduit(id);
        response.sendRedirect("list");
    }

    private void listProduits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Produit> produits = produitService.getAllProduits();
        request.setAttribute("listProduits", produits);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/produit-list.jsp");
        dispatcher.forward(request, response);
        logger.info("Forwarded to produit-list.jsp");
    }
}