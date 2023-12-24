package com.example.artwood.service;

import com.example.artwood.dao.ICommandeDao;
import com.example.artwood.dao.impl.CommandeDaoImp;
import com.example.artwood.dto.OrderDTO;
import com.example.artwood.model.Client;
import com.example.artwood.model.Commande;
import com.example.artwood.model.CommandeStatus;
import com.example.artwood.model.Produit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandeService {
    private static final Logger logger = LogManager.getLogger(CommandeService.class);
    ClientService clientService;
    ProduitService produitService;
    ICommandeDao commandeDao;

    public CommandeService() {
        clientService = new ClientService();
        produitService = new ProduitService();
        commandeDao = new CommandeDaoImp();
    }

    public List<Client> getClients() {
        return clientService.getAllClients();
    }

    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    public Commande addCommande(OrderDTO orderDTO) {
        logger.info("Order DTO: " + orderDTO);
        List<Produit> produitsCommande = getProduitsListFromOrderDTO(orderDTO);
        logger.info("Produit Commande " + produitsCommande);
        float totalAmount = produitsCommande.stream().mapToInt(Produit::getAmount).sum();
        Commande commande = new Commande(Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()),
                orderDTO.getClientId(),
                CommandeStatus.PREPARATION,
                totalAmount
        );
        commande.setClient(clientService.getClient(commande.getClientID()));
        commande.setProduitList(produitsCommande);
        logger.info("attempt to insert commande to db " + commande);
        if (commandeDao.insertCommande(commande)) {

            commande.getProduitList().forEach(produit -> {
                produitService.updateQtePRoduit(produit);
                commandeDao.insertInTableCommandeProduit(commande.getUuid(), produit.getUuid(), produit.getAmount());

            });
        } else {
            logger.error("Commade not insert commandeService");
        }


        return commande;
    }

    private List<Produit> getProduitsListFromOrderDTO(OrderDTO orderDTO) {
        List<Produit> list = new ArrayList<>();
        orderDTO.getListProduits().forEach(produitDTO -> {
                    list.add(getAllProduits().stream().
                            filter(produit -> produit.getUuid().equals(produitDTO.getUuid()))
                            .findFirst()
                            .map(
                                    produitMap -> {
                                        produitMap.setAmount(produitDTO.getProduitAmount());
                                        produitMap.setQte_order(produitDTO.getQteOrder());

                                        return produitMap;
                                    }
                            ).get());
                }

        );
        return list;
    }

    public List<Commande> getAllCommandes() {

        logger.info("Gell All Commande");
        return commandeDao.getAllCommandes().stream().map(commande ->
        {
            commande.setClient(clientService.getClient(commande.getClientID()));
            commande.setProduitList(produitService.getProduitsByCommande(commande.getUuid()));
            return commande;
        }).collect(Collectors.toList());

    }
    public Commande getCommandeById(String uuid){
        Commande commande = commandeDao.getCommande(uuid);
        commande.setClient(clientService.getClient(commande.getClientID()));
        commande.setProduitList(produitService.getProduitsByCommande(uuid));
        return commande;
    }
    public boolean changeStatutCommande(String commandeUUID,String status){
        CommandeStatus commandeStatus = CommandeStatus.valueOf(status);
        if (commandeDao.changeStatutCommande(commandeUUID,commandeStatus)){
            logger.info("Success Update Statut");
            return true;
        }
        return  false;
    }

}
