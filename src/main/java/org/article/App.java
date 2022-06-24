package org.article;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.article.bll.BLLException;
import org.article.bll.ProduitManager;
import org.article.bo.Produit;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {
    private TextField referenceZone = new TextField();
    private TextField libelleZone = new TextField();
    private TextField marqueZone = new TextField();
    private TextField prixZone = new TextField();
    private TextField quantiteZone = new TextField();

    private Label label = new Label("Référence : ");
    private Label libelle = new Label("Libellé");
    private Label marque = new Label("Marque");
    private Label prix = new Label("Prix");
    private Label quantite = new Label("Quantité");
    private ProduitManager produitManager = ProduitManager.getInstance();
    private List<Produit> produits = new ArrayList<>();
    private ChoiceBox cb = new ChoiceBox<>();
    private Produit produitSelect;
    private int count  = 0;
    @Override
    public void start(Stage stage) throws BLLException {

        cb.getItems().addAll("Pain", "Stylo", "Glace");

        Button boutonPrecedent = new Button("Précédent");
        Button boutonSuivant = new Button("Suivant");
        Button boutonEnregistrer = new Button("Enregistrer");
        Button boutonSupprimer = new Button("Supprimer");
        Button boutonNouveau = new Button("Nouveau");

        boutonSuivant.setOnAction( e -> {
            referenceZone.setText(String.valueOf(produitSelect.getRefProd()));
            libelleZone.setText(String.valueOf(produitSelect.getLibelle()));
            marqueZone.setText(String.valueOf(produitSelect.getMarque()));
            prixZone.setText(String.valueOf(produitSelect.getPrixUnitaire()));
            quantiteZone.setText(String.valueOf(produitSelect.getQteStock()));
        });

        boutonSupprimer.setOnAction(e -> {
            try {
                produitManager.deleteElement(produitSelect);
            } catch (BLLException ex) {
                throw new RuntimeException(ex);
            }
        });

        boutonNouveau.setOnAction(e -> {
            try {
                produitManager.ajouterElement(produitSelect = new Produit());

            } catch (BLLException ex) {
                throw new RuntimeException(ex);
            }
        });

        produits = produitManager.getLesProduits();
        produitSelect = produits.get(count);

        //Add textField
        VBox root = new VBox();
        root.getChildren().add(label);
        root.getChildren().add(referenceZone);
        root.getChildren().add(libelle);
        root.getChildren().add(libelleZone);
        root.getChildren().add(marque);
        root.getChildren().add(marqueZone);
        root.getChildren().add(prix);
        root.getChildren().add(prixZone);
        root.getChildren().add(quantite);
        root.getChildren().add(quantiteZone);
        root.getChildren().add(cb);
        root.getChildren().add(boutonPrecedent);
        root.getChildren().add(boutonEnregistrer);
        root.getChildren().add(boutonSuivant);
        root.getChildren().add(boutonSupprimer);
        root.getChildren().add(boutonNouveau);

        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();


    }
    public void menu () {



    }
    public static void main(String[] args) {
        launch();
    }
}