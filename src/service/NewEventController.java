package service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Event;

import java.io.IOException;

public class NewEventController {

    public static Stage stage2 = new Stage();
    @FXML
    private TextField nameOfEventField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField placeField;
    @FXML
    private TextField contactPersonField;

    /**
     * Button to open ChoseCategory window.
     */
    public void openChoseCategoryWindow(Event event) {
        try {
            FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getClassLoader().getResource("view/ChoseCategory.fxml"));
            Parent root3 = fxmlLoader3.load();
            ChoseCategoryController controller = fxmlLoader3.getController();
            controller.setEvent(event);
            stage2.setTitle("Kategorien Wählen");
            stage2.setScene(new Scene(root3));
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Button to create an event
     */
    @FXML
    private void handleGenerateEventButton() {
        //save user input to Event object
        String eventName = nameOfEventField.getText();
        String eventDate = dateField.getText().toString();
        String eventPlace = placeField.getText();
        String contactPerson = contactPersonField.getText();

        //check if user input is valid
        if (eventName.isEmpty() | eventDate.isEmpty() | eventPlace.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehlermeldung");
            alert.setHeaderText(null);
            alert.setContentText("Leere Eingabe! Bitte füllen Sie alle Felder aus");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        }
        try {
            Event newEvent = new Event(eventName, eventDate, eventPlace, contactPerson);
            newEvent.parse(eventDate);
            openChoseCategoryWindow(newEvent);

        } catch (RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehlermeldung");
            alert.setHeaderText(null);
            alert.setContentText("Falsches Datum! Bitte halten Sie das vorgesehene Format ein");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        }
    }

    //Button to close NewEventWindow
    public void closeNewEventButton() {
        RootLayoutController.stage1.close();
    }
}
