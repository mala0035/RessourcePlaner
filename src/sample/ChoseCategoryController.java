package sample;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Collection;


public class ChoseCategoryController {

    @FXML private CheckBox standardCheckbox;
    @FXML private CheckBox othersCheckbox;
    @FXML private CheckBox merchandiseCheckbox;
    @FXML private CheckBox musicCheckbox;
    @FXML private CheckBox servingCheckbox;

 private Stage stage4 = new Stage();

    //Button to close the ChoseCateroy Window
    public void cancelChoseCategoryButton(ActionEvent event) {

        NewEventController close = new NewEventController();
        close.closeWindow();

    }

    //Button to open the FilteredChecklist Window and initialize values from the selected Categories
    public void createFilteredChecklistButton(ActionEvent event3) {
        //Check if the Categories are selected
        Categories newCategory = new Categories(servingCheckbox.isSelected(),musicCheckbox.isSelected(),merchandiseCheckbox.isSelected(),othersCheckbox.isSelected(),standardCheckbox.isSelected());

        //load the FilteredChecklist Window
        try {
            FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("FilteredChecklist.fxml"));
            Parent root4 = (Parent) fxmlLoader3.load();
            stage4.setTitle("Gefilterte Checkliste");
            stage4.setScene(new Scene(root4));
            stage4.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
