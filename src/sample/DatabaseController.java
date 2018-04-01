package sample;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseController {

    //create connection to database
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/RessourcePlaner";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static Connection databaseConnection;


    private DatabaseController(){}


     public static Collection<Article> readCategory(List<Categories> categoryList){
        createDatabaseConnection();
        Collection<Article> articles = new ArrayList<>();

       try {
            Statement statement = databaseConnection.createStatement();
           String collect = categoryList.stream().map(Categories::name).collect(Collectors.joining("','"));
           String getCategory = "SELECT * FROM Storage WHERE Category IN ('"+collect+"')";

           ResultSet categoryResult = statement.executeQuery(getCategory);
            while(categoryResult.next()){
                String articleName = categoryResult.getString("ArticleName");
                int storageId= categoryResult.getInt("StorageID");
                articles.add(new Article(articleName,storageId,0));

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return articles;
    }

    public static Collection<Article> findBy(Collection<Integer> ids){
        createDatabaseConnection();
        Collection<Article> articles = new ArrayList<>();


        try{
            Statement statement = databaseConnection.createStatement();
            String collect = ids.stream().map(id -> id.toString()).collect(Collectors.joining(","));
            String getArticlesFromIds = "SELECT * FROM Storage WHERE StorageID IN ("+collect+")";

            ResultSet resultSet = statement.executeQuery(getArticlesFromIds);
            while(resultSet.next()){
                String articleName = resultSet.getString("ArticleName");
                int id = resultSet.getInt("StorageID");
                int articleAmount = resultSet.getInt("Amount");
                Article article = new Article(articleName,id,articleAmount);
                articles.add(article);

            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return articles;
    }


    //put the Userinput into database
    public static void insertEvent(Event event){
        //Create connection to Database
        createDatabaseConnection();

        //Insert Userinput into Database
        try{
            Statement statement = databaseConnection.createStatement();

            String sqlQuery = "INSERT INTO Event (EventName,EventDate,Place,ContactPerson) VALUES ('"+event.getName()+"','"+event.getDate().toString()+"','"+event.getPlace()+"','"+event.getContactPerson()+"')";

            statement.executeUpdate(sqlQuery);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    //check if there is already a connection. If it's not, create one
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

    public static void updateDB (int value, Article article) {
        try {
            Statement statement = databaseConnection.createStatement();

            String update = "UPDATE Storage SET Amount = "+value+" WHERE StorageID = "+article.getId()+" ";


            statement.executeUpdate(update);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}

