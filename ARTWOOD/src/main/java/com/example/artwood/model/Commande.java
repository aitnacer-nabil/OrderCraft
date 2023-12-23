package com.example.artwood.model;

import com.example.artwood.shared.Utils;

import java.sql.Timestamp;
import java.util.List;

public class Commande {
    private String uuid;
    private Timestamp dateAjoute;
    private Timestamp dateUpdate;
    private Client client;
    private CommandeStatus commandeStatus;
    private List<Produit> produitList;
    private float total_amount;

    public Commande( Timestamp dateAjoute, Timestamp dateUpdate, Client client, CommandeStatus commandeStatus, List<Produit> produitList, float total_amount) {
        this.uuid = Utils.GenerateId();
        this.dateAjoute = dateAjoute;
        this.dateUpdate = dateUpdate;
        this.client = client;
        this.commandeStatus = commandeStatus;
        this.produitList = produitList;
        this.total_amount = total_amount;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Timestamp getDateAjoute() {
        return dateAjoute;
    }

    public void setDateAjoute(Timestamp dateAjoute) {
        this.dateAjoute = dateAjoute;
    }

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CommandeStatus getCommandeStatus() {
        return commandeStatus;
    }

    public void setCommandeStatus(CommandeStatus commandeStatus) {
        this.commandeStatus = commandeStatus;
    }

    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = produitList;
    }

    public float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "uuid='" + uuid + '\'' +
                ", dateAjoute=" + dateAjoute +
                ", dateUpdate=" + dateUpdate +
                ", client=" + client +
                ", commandeStatus=" + commandeStatus +
                ", produitList=" + produitList +
                ", total_amount=" + total_amount +
                '}';
    }
}
