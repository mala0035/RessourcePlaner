package runner;
import db.EventRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Event;
import service.TodaysEventController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    public static Stage stage4 = new Stage();
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
            FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getClassLoader().getResource("view/TodaysEvent.fxml"));
            Parent root5 = fxmlLoader4.load();
            TodaysEventController fcController = fxmlLoader4.getController();
            fcController.setEvents(this);

            stage4.setTitle("Events");
            stage4.setScene(new Scene(root5));
            stage4.show();

            EventRepository.searchTodaysEvents(date);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Collection<Event> getTodaysEvents() {
        Collection<Event> todaysEvents = EventRepository.searchTodaysEvents(date);
        return todaysEvents;
    }
}
