package com.example.artwood.service;

import com.example.artwood.model.Client;
import com.example.artwood.model.Produit;

import java.util.List;

public class CommandeService {
    ClientService clientService;
    ProduitService produitService;

    public CommandeService() {
        clientService = new ClientService();
        produitService = new ProduitService();
    }

    public List<Client> getClients(){
        return clientService.getAllClients();
    }
    public List<Produit> getAllProduits(){
        return  produitService.getAllProduits();
    }
}
