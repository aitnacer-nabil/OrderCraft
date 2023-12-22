package com.example.artwood;

import com.example.artwood.client.Client;
import com.example.artwood.client.ClientDaoImp;
import com.example.artwood.produit.Produit;
import com.example.artwood.produit.ProduitService;

public class Main {
    public static void main(String[] args) {
        ProduitService produitService = new ProduitService();
        Produit produit = new Produit("Example Set","tea set with intricate",350,12);
        String id ="3a12d3";
        String deleteId ="e20a50";
        Produit updated = new Produit("Updatedd Fes Example Set","tea set with intricate",350,12);

     //   System.out.println(produitService.addProduit(produit));
  //      System.out.println(produitService.deleteProduit(deleteId));
//    System.out.println(produitService.getProduitById(id));
   //   System.out.println(produitService.updateProduit(id,updated));
//       System.out.println(produitService.getAllProduits());
    }
}
