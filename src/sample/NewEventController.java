package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;



public class NewEventController {


    @FXML
    private TextField nameOfEventField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField placeField;
    @FXML
    private TextField contactPersonField;

    private static Stage stage3 = new Stage();


    //Button to open ChoseCategory window
    public void handleChoseCategoryButton(ActionEvent event3) {
        try {
            FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("ChoseCategory.fxml"));
            Parent root3 = (Parent) fxmlLoader3.load();
            stage3.setTitle("Kategorien Wählen");
            stage3.setScene(new Scene(root3));
            stage3.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Button to create an event
    public void handleGenerateEventButton(ActionEvent event4){
        //save user input to Event object
        Event newEvent = new Event(nameOfEventField.getText(),dateField.getText().toString(),placeField.getText(),contactPersonField.getText());
        //call insertEvent to insert user input to database
        DatabaseController.insertEvent(newEvent);
    }

    //Button to close NewEventWindow
    public void closeNewEventButton(ActionEvent event5){
    RootLayoutController close= new RootLayoutController();
    close.closeNewEventWindow();


    }

    //close Window
    public void closeWindow(){
        stage3.close();
    }

}
