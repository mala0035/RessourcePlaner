package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteredChecklistController {

    @FXML
    private TableColumn articleNameColumn;

    @FXML
    private TableColumn articleAmountColumn;

    @FXML
    private TableView<Article> articles;

    private Event event;




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
        event = controller.getEvent();
    }

    @FXML
    private void save() {
        ObservableList<Article> uiArticles = articles.getItems();
        Collection<Event> todaysEvents = DatabaseController.searchTodaysEvents(event.getDate());

        Collection<EventArticle> alreadyBlockedArticlesForEvents = DatabaseController.loadBy(todaysEvents.stream().map(Event::getId).collect(Collectors.toList()));
        Collection<Article> alreadyUsedArticles = DatabaseController.findBy(alreadyBlockedArticlesForEvents.stream().map(EventArticle::getArticleId).collect(Collectors.toList()));

        Collection<Article> dbArticlesForGivenArticleNrs = DatabaseController.getBy(uiArticles.stream().map(Article::getArticleNr).collect(Collectors.toList()));
        Collection<EventArticle> eventArticles = new ArrayList<>();

        Collection<FailResult> failResults = new ArrayList<>();

        for(Article uiArticle : uiArticles){

            if(uiArticle.getAmount() > 0){

                long countAllArticlesForArticleNr = dbArticlesForGivenArticleNrs.stream().filter(article -> article.getArticleNr().equals(uiArticle.getArticleNr())).count();
                long countAlreadyUsedArticlesForArticleNr = alreadyUsedArticles.stream().filter(article -> article.getArticleNr().equals(uiArticle.getArticleNr())).count();

                long availableArticleAmount = countAllArticlesForArticleNr - countAlreadyUsedArticlesForArticleNr;
                boolean moreArticlesAvailableThanPicked = countAllArticlesForArticleNr - countAlreadyUsedArticlesForArticleNr - uiArticle.getAmount() >= 0;


                if(moreArticlesAvailableThanPicked){
                dbArticlesForGivenArticleNrs.stream()
                        .filter(article -> article.getArticleNr().equals(uiArticle.getArticleNr()))
                        .limit(uiArticle.getAmount())
                        .forEach(art -> eventArticles.add(new EventArticle(art.getId(),event.getId())) );

                } else{
                    failResults.add(new FailResult(uiArticle.getName(),availableArticleAmount,uiArticle.getAmount()));
                }
            }

        }
        if(failResults.isEmpty()){
            DatabaseController.insertEvent(event);
            DatabaseController.insertConnectionToEventArticleTable(eventArticles);

        }else{showAlert(failResults);}
        ChoseCategoryController.stage4.close();
    }


        public void closeFilteredCategoryButton (ActionEvent event6){

            ChoseCategoryController.stage4.close();

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



