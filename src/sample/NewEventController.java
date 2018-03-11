package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class NewEventController {

    public void handleChoseCategoryButton(ActionEvent event3) {
        try {
            FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("ChoseCategory.fxml"));
            Parent root3 = (Parent) fxmlLoader3.load();
            Stage stage3 = new Stage();

            stage3.setTitle("Kategorien Wählen");
            stage3.setScene(new Scene(root3));
            stage3.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
