package runner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ChoseCategoryController;
import service.NewEventController;
import service.RootLayoutController;

import java.io.IOException;
import java.time.LocalDate;

public class Main extends Application {

    private Stage primaryStage;
    private static BorderPane rootLayout;
    private static FullCalendarView calendarView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Ressource Planer");
        initRootLayout();
        setModality();
    }

    //open RootLayout window
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getClassLoader().getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene1 = new Scene(rootLayout, 600, 500);
            calendarView = new FullCalendarView(LocalDate.now());
            rootLayout.setBottom(new HBox(calendarView.getView()));
            primaryStage.setScene(scene1);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setModality() {
        RootLayoutController.stage1.initModality(Modality.APPLICATION_MODAL);
        NewEventController.stage2.initModality(Modality.APPLICATION_MODAL);
        ChoseCategoryController.stage3.initModality(Modality.APPLICATION_MODAL);
    }

    public static void refresh(){
    calendarView.populateCalendar(LocalDate.now());
    }

}


