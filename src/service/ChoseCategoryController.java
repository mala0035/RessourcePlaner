package service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import model.Category;
import model.Event;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChoseCategoryController {

    public static Stage stage3 = new Stage();
    @FXML
    private CheckBox standardCheckbox;
    @FXML
    private CheckBox othersCheckbox;
    @FXML
    private CheckBox merchandiseCheckbox;
    @FXML
    private CheckBox musicCheckbox;
    @FXML
    private CheckBox servingCheckbox;
    private model.Event event;

    //Button to close the ChoseCategory Window
    public void cancelChoseCategoryButton() {
        NewEventController.stage2.close();
    }

    //Button to open the FilteredChecklist Window and initialize values from the selected Categories
    public void createFilteredChecklistButton() {

        //load the FilteredChecklist Window
        try {
            FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getClassLoader().getResource("view/FilteredChecklist.fxml"));
            Parent root4 = fxmlLoader3.load();
            FilteredChecklistController fcController = fxmlLoader3.getController();
            fcController.setItems(this);
            stage3.setTitle("Gefilterte Checkliste");
            stage3.setScene(new Scene(root4));
            stage3.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public model.Event getEvent() {
        return event;
    }

    public void setEvent(Event thisEvent) {
        event = thisEvent;
    }

    List<Category> getSelectedCategories() {
        return Stream.of(servingCheckbox, musicCheckbox, merchandiseCheckbox, othersCheckbox, standardCheckbox)
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .map(String::toUpperCase)
                .map(Category::valueOf)
                .collect(Collectors.toList());
    }
}
