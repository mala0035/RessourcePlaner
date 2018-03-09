package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Event> eventData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {


        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Ressource Planer");

        initRootLayout();


        //build the databank connection
        // Controller.buildConnection();


    }

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


    public static void main(String[] args) {
        launch(args);


    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    // method to call NewEvent.fxml
    public boolean showNewEvent(Event event) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("NewEvent.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Neues Event");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            NewEventController controller = loader.getController();
            controller.setNewEventStage(dialogStage);
            controller.setMainApp(this);

            dialogStage.showAndWait();
            controller.setEvent(event);
            return controller.wasClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

    public ObservableList<Event> getEventData() {
        return eventData;

    }
}
