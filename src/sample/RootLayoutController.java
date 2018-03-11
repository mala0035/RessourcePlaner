package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;


public class RootLayoutController {

    private Stage primaryStage;


    public void handleNewEventButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewEvent.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Neues Event");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("cant load");
        }

    }


    public void handleEditChecklistButton(ActionEvent event2) {
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("ChecklistEdit.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();

            stage2.setTitle("Kategorien Bearbeiten");
            stage2.setScene(new Scene(root2));
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

