package org.article.bo;

import java.util.ArrayList;
import java.util.List;

public class Achat {
    private Double montant = 0d;

    List<Ligne> lignes = new ArrayList<>();

    public Double caclulMontant() {
        montant = 0d;
        for (Ligne ligne : lignes) {
            montant += ligne.getPrix();
        }
        return montant;
    }

    public Achat(Ligne ligne) {
        lignes.add(ligne);
        caclulMontant();
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public Ligne getLigne(int index) {
        return lignes.get(index);
    }

    public void ajouteLigne(Produit p, int qte) {
        lignes.add(new Ligne(p, qte));
        caclulMontant();
    }

    public void modifieLigne(int index, int nouvelleQte) {
        lignes.get(index).setQuantite(nouvelleQte);
        caclulMontant();
    }

    public void supprimeLigne(int index) {
        lignes.remove(lignes.get(index));
        caclulMontant();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Achat{");
        sb.append(", lignes=").append(lignes);
        sb.append(", Total de l'achat :").append((double)Math.round(montant * 100)/100 + "€");
        sb.append('}');
        return sb.toString();
    }
}
