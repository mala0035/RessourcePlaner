package sample;
import java.sql.*;

public class Controller {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL ="jdbc:mysql://localhost/RessourcePlaner";

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123";



    public static void buildConnection () {

        final Connection conn;
        final Statement stmt;




        try{
            Class.forName(JDBC_DRIVER);

            System.out.println("Baut Verbindung auf");
            conn = DriverManager.getConnection( DB_URL, USER_NAME,PASSWORD);

            System.out.println("Create Statement");

            stmt = conn.createStatement();

            String sqlQuery = "INSERT INTO Event VALUES(1, 'TestEvent', '2018-10-10', 'Pirmasens', 'M. Landau')";

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