package sample;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;


import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class FilteredChecklistController {

    @FXML
    private TableColumn articleNameColumn;

    @FXML
    private TableColumn articleAmountColumn;

    @FXML
    private TableView<Article> articles;





    @FXML
    private void initialize() {
        articles.setEditable(true);
        articleNameColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("name"));
        articleAmountColumn.setCellValueFactory(new PropertyValueFactory<Article, Integer>("amount"));
        articleAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        articleAmountColumn.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Article, Integer>>) editEvent -> editEvent.getTableView().getItems().get(editEvent.getTablePosition().getRow()).setAmount(editEvent.getNewValue()));
    }

    public void setItems(ChoseCategoryController controller){
        articles.setItems(FXCollections.observableArrayList(DatabaseController.readCategory(controller.getSelectedCategories())));
    }

    @FXML
    private void save (){
        Map<Integer,Article> uiArticles = this.articles.getItems().stream().collect(Collectors.toMap(articles -> articles.getId(), article -> article));
        Collection<Article> dbArticles = DatabaseController.findBy(uiArticles.keySet());

        for(Article articleRecord : dbArticles){
           Article uiArticle = uiArticles.get(articleRecord.getId());

           int result = articleRecord.getAmount() - uiArticle.getAmount();

            if(result < 0){
                throw new IllegalStateException("you can't pick more articles than available");
            }else {
               DatabaseController.updateDB(result,uiArticle);
            }
        }

    }


    public void closeFilteredCategoryButton(ActionEvent event6){
        ChoseCategoryController closeThisThing= new ChoseCategoryController();
        closeThisThing.closeFilteredChecklistWindow();

    }


}


