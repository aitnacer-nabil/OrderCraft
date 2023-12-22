package com.example.artwood.commande;

import com.example.artwood.client.Client;
import com.example.artwood.client.ClientServlet;
import com.example.artwood.produit.Produit;
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

        showAddForm(req,resp);
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
