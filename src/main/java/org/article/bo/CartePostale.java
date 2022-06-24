package org.article.bo;

import java.util.ArrayList;
import java.util.List;

public class CartePostale extends Produit{
    private String type;
    List<Auteur> lesAuteursDeLaCarte = new ArrayList<>();


    public CartePostale() {
        super();
    }

    public CartePostale(long refProd, String marque, String libelle, long qteStock, float prixUnitaire, List<Auteur> lesAuteursDeLaCarte, TypeCartePostale type) {
        super(refProd, libelle, marque, qteStock, prixUnitaire);
        this.setType(type.name());
        this.setLesAuteursDeLaCarte(lesAuteursDeLaCarte);
    }

    public CartePostale(long refProd, String marque, String libelle, long qteStock, float prixUnitaire, String type) {
        super(refProd, marque, libelle, qteStock, prixUnitaire);
        this.type = type;
    }

    public CartePostale(String marque, String libelle, long qteStock, float prixUnitaire, List<Auteur> lesAuteursDeLaCarte, TypeCartePostale type) {
        this(0, marque, libelle, qteStock, prixUnitaire, lesAuteursDeLaCarte, type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Auteur> getLesAuteursDeLaCarte() {
        return lesAuteursDeLaCarte;
    }

    public void setLesAuteursDeLaCarte(List<Auteur> lesAuteursDeLaCarte) {
        this.lesAuteursDeLaCarte = lesAuteursDeLaCarte;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CartePostale{");
        sb.append(super.toString());
        sb.append("type='").append(type).append('\'');
        sb.append(", lesAuteursDeLaCarte=").append(lesAuteursDeLaCarte);
        sb.append('}');
        return sb.toString();
    }
}
