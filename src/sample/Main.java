package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;



    @Override
    public void start(Stage primaryStage) throws Exception{

    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Ressource Planer");


    initRootLayout();
    setModality();
    }


    //Main method
    public static void main(String[] args) { launch(args); }

    //open RootLayout window
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene1 = new Scene(rootLayout, 600, 500);
            rootLayout.setBottom(new HBox(new FullCalendarView(LocalDate.now()).getView()));
            primaryStage.setScene(scene1);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setModality() {
        RootLayoutController.stage1.initModality(Modality.APPLICATION_MODAL);
        RootLayoutController.stage2.initModality(Modality.APPLICATION_MODAL);
        NewEventController.stage3.initModality(Modality.APPLICATION_MODAL);
        ChoseCategoryController.stage4.initModality(Modality.APPLICATION_MODAL);

    }
}


