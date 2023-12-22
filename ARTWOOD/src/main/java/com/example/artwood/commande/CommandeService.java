package com.example.artwood.commande;

import com.example.artwood.client.Client;
import com.example.artwood.client.ClientService;
import com.example.artwood.produit.Produit;
import com.example.artwood.produit.ProduitService;

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
