package sample;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ChoseCategoryController {

    @FXML private CheckBox standardCheckbox;
    @FXML private CheckBox othersCheckbox;
    @FXML private CheckBox merchandiseCheckbox;
    @FXML private CheckBox musicCheckbox;
    @FXML private CheckBox servingCheckbox;

 private static Stage stage4 = new Stage();

    //Button to close the ChoseCateroy Window
    public void cancelChoseCategoryButton(ActionEvent event) {

        NewEventController close = new NewEventController();
        close.closeWindow();

    }

    //Button to open the FilteredChecklist Window and initialize values from the selected Categories
    public void createFilteredChecklistButton(ActionEvent event3) {

        //load the FilteredChecklist Window
        try {
            FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("FilteredChecklist.fxml"));
            Parent root4 = (Parent) fxmlLoader3.load();
            FilteredChecklistController fcController = fxmlLoader3.getController();
            fcController.setItems(this);

            stage4.setTitle("Gefilterte Checkliste");
            stage4.setScene(new Scene(root4));
            stage4.initModality(Modality.APPLICATION_MODAL);
            stage4.show();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Categories> getSelectedCategories() {
        return Stream.of(servingCheckbox, musicCheckbox,merchandiseCheckbox,othersCheckbox,standardCheckbox)
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .map(String::toUpperCase)
                .map(Categories::valueOf)
                .collect(Collectors.toList());
    }

    public void closeFilteredChecklistWindow(){
        stage4.close();
    }

}
