package org.article;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.article.bll.BLLException;
import org.article.bll.ProduitManager;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws BLLException {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);

        ProduitManager test = ProduitManager.getInstance();

        System.out.println(test.getLesElements());
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}