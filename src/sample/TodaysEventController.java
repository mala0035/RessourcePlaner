package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class TodaysEventController {
    @FXML
    private TableView<Event> events;
    @FXML
    private TableColumn eventNameColumn;
    @FXML
    private TableColumn eventDateColumn;
    @FXML
    private TableColumn eventPlaceColumn;
    @FXML
    private TableColumn contactPersonColumn;

    @FXML
    public void initialize() {
        events.setEditable(true);
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("EventDate"));
        eventPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
        contactPersonColumn.setCellValueFactory(new PropertyValueFactory<>("ContactPerson"));
    }


    @FXML
    private void handleOKButton(){
        AnchorPaneNode.stage5.close();
    }

    public void setEvents(AnchorPaneNode controller){
        AnchorPaneNode date = new AnchorPaneNode();
        date.getDate();

        events.setItems(FXCollections.observableArrayList(controller.getTodaysEvents()));
    }

}
