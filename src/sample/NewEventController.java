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

    public void handleGenerateEventButton(ActionEvent event4){
        Event newEvent = new Event(nameOfEventField.getText(),dateField.getText().toString(),placeField.getText(),contactPersonField.getText());
        Controller.insert(newEvent);
    }

    public void closeWindow(){
        stage3.close();
    }

}
