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


public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // this is a fancy comment
    window = primaryStage;
    window.setTitle("Ressource Planer");

        HBox topMenu = new HBox();
        Button createNewEvent = new Button("+");
        Button editChecklist = new Button ("=");
        Button dayView = new Button ("T");
        Button weekView = new Button ("W");
        Button monthView = new Button ("M");
        topMenu.getChildren().addAll(createNewEvent,editChecklist,dayView,weekView,monthView);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        topMenu.setAlignment(Pos.BASELINE_CENTER);
        dayView.setScaleX(2);
        createNewEvent.setScaleX(2);



        Scene scene1 = new Scene(borderPane, 1024,720);
        window.setScene(scene1);
        window.show();







    }


    public static void main(String[] args) {
        launch(args);
    }
}
