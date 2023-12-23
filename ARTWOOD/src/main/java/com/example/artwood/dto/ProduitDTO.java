package com.example.artwood.dto;

import com.google.gson.annotations.SerializedName;

public class ProduitDTO {
    private String uuid;
    @SerializedName("qte_order")
    private  int qteOrder;
    @SerializedName("produit_amount")
    private int produitAmount;

    public ProduitDTO(String uuid, int qteOrder, int produitAmount) {
        this.uuid = uuid;
        this.qteOrder = qteOrder;
        this.produitAmount = produitAmount;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getQteOrder() {
        return qteOrder;
    }

    public void setQteOrder(int qteOrder) {
        this.qteOrder = qteOrder;
    }

    public int getProduitAmount() {
        return produitAmount;
    }

    public void setProduitAmount(int produitAmount) {
        this.produitAmount = produitAmount;
    }

    @Override
    public String toString() {
        return "ProduitDTO{" +
                "uuid='" + uuid + '\'' +
                ", qteOrder=" + qteOrder +
                ", produitAmount=" + produitAmount +
                '}';
    }
}
