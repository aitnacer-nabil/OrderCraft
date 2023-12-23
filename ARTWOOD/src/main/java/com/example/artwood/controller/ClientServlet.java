package com.example.artwood.controller;

import com.example.artwood.model.Client;
import com.example.artwood.service.ClientService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Client", urlPatterns = {"/Client","/Client/*"})
public class ClientServlet extends HttpServlet {
    private static   final Logger logger = LogManager.getLogger(ClientServlet.class);
    private ClientService clientService;

    public void init() {
        this.clientService = new ClientService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    logger.info(request.getServletPath());

        String action = request.getServletPath() + (request.getPathInfo() != null ? request.getPathInfo() : "");

        logger.info("Action: " + action);
        switch (action) {
            case "/Client/new":
                logger.info("Showing add form...");
                showAddForm(request,response);
                break;
            case "/Client/delete":
                logger.info("Deleting client..."); // Log the case
                deleteClient(request,response);
                break;
            case "/Client/edit":
                logger.info("Showing edit form..."); // Log the case
                showEditForm(request,response);
                break;
            default:
                logger.info("Listing clients...");
                listClients(request,response);
                break;


        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath() + (request.getPathInfo() != null ? request.getPathInfo() : "");

        logger.info("Action: " + action);
        switch (action) {
            case "/Client/insert":
                logger.info("Inserting client..."); // Log the case

                insertClient(request,response);
                break;

            case "/Client/update":
                logger.info("Updating client...");
                updateClient(request,response);
                break;
            default:
                logger.info("Listing clients...");
                listClients(request,response);
                break;


        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/client-form.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Client client = clientService.getClient(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/client-form.jsp");
        request.setAttribute("client",client);
        dispatcher.forward(request, response);
        logger.info("Forwarded to client-form.jsp");
    }
    private void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Inserting client...");
        clientService.addClient(generateClientFromRequestPatameter(request));
        response.sendRedirect("list");
        logger.info("Client inserted and redirected to list");
    }
    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        clientService.updateClient(getIdFromRequestParam(request),generateClientFromRequestPatameter(request));
        response.sendRedirect("list");
    }
    private Client generateClientFromRequestPatameter(HttpServletRequest request){

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String adress = request.getParameter("adress");

        return new Client(name, email, phone, adress);
    }
    private String getIdFromRequestParam(HttpServletRequest request){
        return   request.getParameter("id");
    }
    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        clientService.deleteClient(id);
        response.sendRedirect("list");
    }

    private void listClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      List<Client> clients = clientService.getAllClients();
        request.setAttribute("listClient",clients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/client-list.jsp");
        dispatcher.forward(request, response);
        logger.info("Forwarded to client-list.jsp");
    }
}
//05493c	Sanaa Benjelloun	sanaa.benjelloun@example.com	Rue Mohammed El Baamrani, Tetouan	+212601234567