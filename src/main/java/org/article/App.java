package org.article;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
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
    private TextField maZone;
    private TextField maZone2;
    @Override
    public void start(Stage stage) throws BLLException {



        ChoiceBox cb = new ChoiceBox<>();
        cb.getItems().addAll("Pain", "Stylo", "Glace");
        maZone = new TextField();
        maZone2 = new TextField();

        Button boutonPrecedent = new Button("Précédent");
        Button boutonSuivant = new Button("Suivant");
        Button boutonEnregistrer = new Button("Enregistrer");
        Button boutonSupprimer = new Button("Supprimer");
        Button boutonNouveau = new Button("Nouveau");

        boutonNouveau.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(App.this.maZone.getText());
            }
        });

        ProduitManager test = ProduitManager.getInstance();


        Label label = new Label("Référence : ");
        Label libelle = new Label("Libellé");
        Label marque = new Label("Marque");
        Label prix = new Label("Prix");
        Label quantite = new Label("Quantité");



        FlowPane root = new FlowPane();
        root.getChildren().add(label);
        root.getChildren().add(maZone);
        root.getChildren().add(libelle);
        root.getChildren().add(maZone2);
        root.getChildren().add(marque);
        root.getChildren().add(prix);
        root.getChildren().add(quantite);
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
    public static void main(String[] args) {
        launch();
    }
}