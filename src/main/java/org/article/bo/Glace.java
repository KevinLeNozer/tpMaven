package org.article.bo;


import java.time.LocalDate;

public class Glace extends ProduitPerissable{
    private String parfum;
    private int temperatureConservation;

    public Glace() {
        super();
    }

    public Glace(long refProd, String marque, String libelle, long qteStock, float prixUnitaire,
                 LocalDate dateLimiteDeConso, String parfum, int temperatureConservation) {
        super(refProd, marque,libelle, qteStock, prixUnitaire, dateLimiteDeConso);
        this.setParfum(parfum);
        this.setTemperatureConservation(temperatureConservation);
    }

    public Glace(LocalDate dateLimiteDeConso, String marque, String libelle, int temperatureConservation, String parfum, long qteStock, float prixUnitaire) {
        this(0, marque, libelle, qteStock, prixUnitaire, dateLimiteDeConso, parfum, temperatureConservation);
    }

    public String getParfum() {
        return parfum;
    }

    public void setParfum(String parfum) {
        this.parfum = parfum;
    }

    public int getTemperatureConservation() {
        return temperatureConservation;
    }

    public void setTemperatureConservation(int temperatureConservation) {
        this.temperatureConservation = temperatureConservation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Glace{");
        sb.append(super.toString());
        sb.append("parfum='").append(parfum).append('\'');
        sb.append(", temperatureConservation=").append(temperatureConservation);
        sb.append('}');
        return sb.toString();
    }
}
