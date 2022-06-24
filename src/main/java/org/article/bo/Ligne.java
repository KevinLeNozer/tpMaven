package org.article.bo;

public class Ligne {
    private int quantite;
    Produit produit;

    public Ligne( Produit produit, int quantite) {
        this.setProduit(produit);
        this.setQuantite(quantite);
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Double getPrix() {
        double objetTotal = (produit.getPrixUnitaire() * getQuantite());
        return objetTotal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ligne{");
        sb.append("quantite=").append(quantite);
        sb.append(", produit=").append(produit);
        sb.append(", prix=").append((double)Math.round(getPrix() * 100)/100 + "€");
        sb.append('}');
        return sb.toString();
    }
}
