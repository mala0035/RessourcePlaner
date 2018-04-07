package sample;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.util.converter.IntegerStringConverter;


import java.util.ArrayList;
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

    private Map<Integer, Article> uiArticles;
    private Collection<Article> dbArticles;
    private Collection<FailResult> failResults;
    private int result;
    Article dbArticle = new Article(null, 0, 0);
    Article uiArticle;
    NewEventController callSetEvent = new NewEventController();
    Article articleRecord;

    @FXML
    private void initialize() {
        articles.setEditable(true);
        articleNameColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("name"));
        articleAmountColumn.setCellValueFactory(new PropertyValueFactory<Article, Integer>("amount"));
        articleAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        articleAmountColumn.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Article, Integer>>) editEvent -> editEvent.getTableView().getItems().get(editEvent.getTablePosition().getRow()).setAmount(editEvent.getNewValue()));
    }

    public void setItems(ChoseCategoryController controller) {
        articles.setItems(FXCollections.observableArrayList(DatabaseController.readCategory(controller.getSelectedCategories())));
    }

    @FXML
    private void save() {
        uiArticles = this.articles.getItems().stream().collect(Collectors.toMap(articles -> articles.getId(), article -> article));
        dbArticles = DatabaseController.findBy(uiArticles.keySet());
        failResults = new ArrayList<>();

        for (Article articleRecord : dbArticles) {
            uiArticle = uiArticles.get(articleRecord.getId());
            dbArticle = new Article(articleRecord.getName(), articleRecord.getId(), articleRecord.getAmount());

            result = articleRecord.getAmount() - uiArticle.getAmount();

            if (result < 0) {
                failResults.add(new FailResult(dbArticle, uiArticle.getAmount()));
            } else {
                articleRecord.setAmount(result);

            }if (failResults.isEmpty()) {
                DatabaseController.updateDB(result, uiArticle); //result,uiArticles //
                callSetEvent.setEvent();
            }else {
                showAlert(failResults);

        }

        }
    }


        public void closeFilteredCategoryButton (ActionEvent event6){
            ChoseCategoryController closeThisThing = new ChoseCategoryController();
            closeThisThing.closeFilteredChecklistWindow();

        }
        private void showAlert (Collection < FailResult > failResults) {
            String printMessage = failResults.stream().map(FailResult::toString).collect(Collectors.joining(",\n"));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setResizable(true);
            alert.setTitle("Fehler beim Aktualisieren");
            alert.setHeaderText("Die Aktualisierung der Datenbank ist fehlgeschlagen.");
            alert.setContentText("Die Datenbank konnte nicht aktualisiert werden, " +
                    "da mehr Artikel ausgewählt wurden als vorhanden sind: \n\n" + printMessage);

            alert.showAndWait();
        }

    }



