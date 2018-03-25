package sample;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;

public class FilteredChecklistController {


    @FXML
    private String articleNameColumn;
    @FXML
    private String amountColumn;
    @FXML
    private String checkboxColumn;



    public void getValues (String aNameColumn){
        this.articleNameColumn = aNameColumn;



    }







}
