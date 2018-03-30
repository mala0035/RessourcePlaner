package sample;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Article;

public class FilteredChecklistController {

    @FXML
    private TableColumn articleNameColumn;

    @FXML
    private TableView<Article> articles;



    @FXML
    private void initialize() {
        articleNameColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("name"));
        }

        public void setItems(ChoseCategoryController controller){
            articles.setItems(FXCollections.observableArrayList(DatabaseController.readCategory(controller.getSelectedCategories())));
        }




}


