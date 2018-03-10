package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{

    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Ressource Planer");

    initRootLayout();
//    Controller.buildConnection();
    }


    public static void main(String[] args) { launch(args); }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene1 = new Scene(rootLayout, 1024, 720);
            primaryStage.setScene(scene1);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


