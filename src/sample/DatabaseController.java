package sample;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DatabaseController {

    //create connection to database
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/RessourcePlaner";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static Connection databaseConnection;


    private DatabaseController(){}


    public static Collection<Article> readCategory(String selectedCategory){
        createDatabaseConnection();
        Collection<Article> articles = new ArrayList<>();

        try {
            Statement statement = databaseConnection.createStatement();

            String getCategory = "SELECT ArticleName, Category FROM Storage WHERE Category='Ausschank' ";

            ResultSet categoryResult = statement.executeQuery(getCategory);

            while(categoryResult.next()){
                String articleName = categoryResult.getString("ArticleName");
                Article article = new Article(articleName);
                articles.add(article);

                String categoryName = categoryResult.getString("Category");
                System.out.println(articleName +", " + categoryName);



            }



        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return articles;
    }


    public static void insertEvent(Event event){

        createDatabaseConnection();



        try{
            Statement statement = databaseConnection.createStatement();

            String sqlQuery = "INSERT INTO Event (EventName,EventDate,Place,ContactPerson) VALUES ('"+event.getName()+"','"+event.getDate().toString()+"','"+event.getPlace()+"','"+event.getContactPerson()+"')";

            statement.executeUpdate(sqlQuery);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    private static void createDatabaseConnection(){

        if(databaseConnection == null) {

            try {
                Class.forName(JDBC_DRIVER);

                System.out.println("Baut Verbindung auf");
                databaseConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

            } catch (ClassNotFoundException |SQLException ex) {
                    ex.printStackTrace();


            }
        }
    }
}

