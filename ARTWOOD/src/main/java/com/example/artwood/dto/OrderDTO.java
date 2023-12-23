package com.example.artwood.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDTO {
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("list_produits")
    private List<ProduitDTO> listProduits;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<ProduitDTO> getListProduits() {
        return listProduits;
    }

    public void setListProduits(List<ProduitDTO> listProduits) {
        this.listProduits = listProduits;
    }

    public OrderDTO(String clientId, List<ProduitDTO> listProduits) {
        this.clientId = clientId;
        this.listProduits = listProduits;
    }


    @Override
    public String toString() {
        return "OrderDTO{" +
                "clientId='" + clientId + '\'' +
                ", listProduits=" + listProduits +
                '}';
    }



}
