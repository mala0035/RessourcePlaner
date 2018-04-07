package sample;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    public static Stage stage5 = new Stage();



    // Date associated with this pane
    private LocalDate date;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> init());
    }

    @FXML
    private void init (){
        try {
            FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("TodaysEvent.fxml"));
            Parent root5 = (Parent) fxmlLoader4.load();
            TodaysEventController fcController = fxmlLoader4.getController();
            fcController.setEvents(this);

            stage5.setTitle("Events");
            stage5.setScene(new Scene(root5));
            stage5.show();


            DatabaseController.searchTodaysEvents(date);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kA(){
        DatabaseController.searchTodaysEvents(date);

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    Collection<Event> getTodaysEvents() {
        Collection<Event> bla = DatabaseController.searchTodaysEvents(date);
        return bla;
    }
}
