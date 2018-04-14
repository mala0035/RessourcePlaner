package service;

import db.ArticleRepository;
import db.EventArticleRepository;
import model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import runner.AnchorPaneNode;

import java.util.*;
import java.util.stream.Collectors;

public class TodaysEventController extends AnchorPane {
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
    private TableColumn needArticles;
    @FXML
    private ListView<ArticleAmount> articlesTable;


    @FXML
    public void initialize() {
        events.setEditable(true);
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        eventPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        contactPersonColumn.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        events.getSelectionModel().selectedItemProperty().addListener((observable, oldVaue, newValue) -> setNeedArticles(newValue) );
    }


    @FXML
    private void handleOKButton() {
        AnchorPaneNode.stage4.close();
    }

    public void setEvents(AnchorPaneNode controller) {
        AnchorPaneNode date = new AnchorPaneNode();
        date.getDate();

        events.setItems(FXCollections.observableArrayList(controller.getTodaysEvents()));

    }

    private void setNeedArticles(Event event) {
        if (event != null) {
            Collection<EventArticle> alreadyBlockedArticlesForEvents = EventArticleRepository.loadBy(Arrays.asList(event.getId()));
            Collection<Article> articles = ArticleRepository.findBy(alreadyBlockedArticlesForEvents.stream().map(EventArticle::getArticleId).collect(Collectors.toSet()));
            Set<ArticleAmount> articleViewDetails = new HashSet<>();
            articles.stream().forEach(article -> articleViewDetails.add(new ArticleAmount(article, articles.stream().filter(art -> art.getArticleNr().equals(article.getArticleNr())).count())));
            articlesTable.setItems(FXCollections.observableArrayList(articleViewDetails));
        }

    }
}
