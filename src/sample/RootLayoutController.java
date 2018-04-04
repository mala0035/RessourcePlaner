package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;



public class RootLayoutController {

    public static Stage stage1 = new Stage();
    public static Stage stage2 = new Stage();



    //Open newEventWindow
    public void handleNewEventButton(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewEvent.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            stage1.setTitle("Neues Event");
            stage1.setScene(new Scene(root1));
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //Open editChecklistWindow
    public void handleEditChecklistButton(ActionEvent event2) {
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("ChecklistEdit.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();

            stage2.setTitle("Kategorien Bearbeiten");
            stage2.setScene(new Scene(root2));
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Close newEventWindow
    public void closeNewEventWindow(){
        stage1.close();

    }

}

