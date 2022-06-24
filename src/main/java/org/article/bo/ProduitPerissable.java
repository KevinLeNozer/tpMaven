package org.article.bo;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProduitPerissable extends Produit{
    private LocalDate dateLimiteDeConso;

    public ProduitPerissable() {
      super();
    }

    public ProduitPerissable(long refProd, String marque, String libelle, long qteStock,
                             float prixUnitaire, LocalDate dateLimiteDeConso) {
        super(refProd, marque, libelle, qteStock, prixUnitaire);
        this.setDateLimiteDeConso(dateLimiteDeConso);
    }

    public ProduitPerissable(String marque, String libelle, long qteStock, float prixUnitaire,
                             LocalDate dateLimiteDeConso) {
        this(0, marque, libelle, qteStock, prixUnitaire, dateLimiteDeConso);
    }

    public LocalDate getDateLimiteDeConso() {
        return dateLimiteDeConso;
    }

    public void setDateLimiteDeConso(LocalDate dateLimiteDeConso) {
        this.dateLimiteDeConso = dateLimiteDeConso;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("dateLimiteDeConso=").append(dateLimiteDeConso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        sb.append('}');
        return sb.toString();
    }
}
