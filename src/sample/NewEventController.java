package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.awt.*;

public class NewEventController {

    private Main mainApp;
    private Stage newEventStage;
    private boolean wasClicked = false;
    private Event event;
    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TextField nameOfEventField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField placeField;
    @FXML
    private TextField contactPersonField;

    @FXML
    private Label nameOfEventLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label placeLabel;
    @FXML
    private Label contactPersonLabel;

    @FXML
    private void handleNewEvent(){
        Event tempEvent = new Event();
        boolean wasClicked = mainApp.showNewEvent(tempEvent);
        if (wasClicked) {
            mainApp.getEventData().add(tempEvent);
        }
    }


    public void setNewEventStage(Stage newEventStage){
        this.newEventStage = newEventStage;
    }
    public boolean wasClicked(){
        return wasClicked;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        eventTable.setItems(mainApp.getEventData());

    }

    public void setEvent(Event event){
        this.event = event;

        nameOfEventField.setText(event.getNameOfEvent());
        dateField.setText(DateUtil.format(event.getDate()));
        dateField.setPromptText("TT.MM.YY");
        placeField.setPromptText(event.getPlace());
        contactPersonField.setText(event.getContactPerson());


    }
    private void initialize(){
        eventTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEventDetails(newValue)
        );


    }
    private void showEventDetails (Event event){
        if (event != null){
            nameOfEventLabel.setText(event.getNameOfEvent());
            dateLabel.setText(DateUtil.format(event.getDate()));
            placeLabel.setText(event.getPlace());
            contactPersonLabel.setText(event.getContactPerson());
        }else {
            nameOfEventLabel.setText("");
            dateLabel.setText("");
            placeLabel.setText("");
            contactPersonLabel.setText("");
        }
    }
}
