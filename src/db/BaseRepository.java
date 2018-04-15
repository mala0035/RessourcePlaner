package db;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class BaseRepository {

    //create connection to database
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/RessourcePlaner";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static Connection databaseConnection;

    //check if there is already a connection. If it's not, create one
    static {
        if (databaseConnection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                System.out.println("Baut Verbindung auf");
                databaseConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fehlermeldung");
                alert.setHeaderText(null);
                alert.setContentText("Es konnte keine Verbindung zur Datenbank aufgebaut werden!");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.showAndWait();
            }
        }
    }

    //constructor
    protected BaseRepository() {
    }

    protected static Connection getDatabaseConnection() {
        return databaseConnection;
    }
}



