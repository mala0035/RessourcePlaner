package sample;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.sql.*;

public class Controller {

    @FXML
    Pane calendarPane;

    //create connection to database
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL ="jdbc:mysql://localhost/RessourcePlaner";

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";


    public static void buildConnection () {

        final Connection conn;
        final Statement stmt;




        try{
            Class.forName(JDBC_DRIVER);

            System.out.println("Baut Verbindung auf");
            conn = DriverManager.getConnection( DB_URL, USER_NAME,PASSWORD);

            System.out.println("Create Statement");

            stmt = conn.createStatement();

            String sqlQuery = "INSERT INTO Event VALUES(1, 'Umzug', '2018-10-10', 'Bundenthal', 'L. Dragosa')";

            stmt.executeUpdate(sqlQuery);


            String getEvents = "SELECT * FROM Event";

            ResultSet resultSet = stmt.executeQuery(getEvents);

            while(resultSet.next()){
                String name = resultSet.getString("Name");

                System.out.println("Name of event is: " + name);
            }


        }catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();


        }



    }






}
