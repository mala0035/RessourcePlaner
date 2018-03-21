package sample;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;


import java.io.IOException;


public class ChoseCategoryController {

    @FXML private CheckBox standardCheckbox;
    @FXML private CheckBox othersCheckbox;
    @FXML private CheckBox merchandiseCheckbox;
    @FXML private CheckBox musicCheckbox;
    @FXML private CheckBox servingCheckbox;



 private Stage stage4 = new Stage();





    public void cancelChoseCategoryButton(ActionEvent event) {

        NewEventController bla = new NewEventController();
        bla.closeWindow();

    }


    public void createFilteredChecklistButton(ActionEvent event3) {
        Categories newCategory = new Categories(servingCheckbox.isSelected(),musicCheckbox.isSelected(),merchandiseCheckbox.isSelected(),othersCheckbox.isSelected(),standardCheckbox.isSelected());
        if (servingCheckbox.isSelected() == true){
        DatabaseController.insertCategory("Ausschank");

        }




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
