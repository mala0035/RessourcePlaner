package sample;
import java.sql.*;
import java.time.LocalDate;
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


    private DatabaseController() {
    }


    public static Collection<Article> readCategory(List<Categories> categoryList) {
        createDatabaseConnection();
        Collection<Article> articles = new ArrayList<>();

        try {
            Statement statement = databaseConnection.createStatement();
            String collect = categoryList.stream().map(Categories::name).collect(Collectors.joining("','"));
            String getCategory = "SELECT * FROM Storage WHERE Category IN ('" + collect + "') Group by ArticleNr";

            ResultSet categoryResult = statement.executeQuery(getCategory);
            while (categoryResult.next()) {
                String articleName = categoryResult.getString("ArticleName");
                int storageId = categoryResult.getInt("StorageID");
                String articleNr = categoryResult.getString("ArticleNr");

                articles.add(new Article(articleName, storageId, articleNr));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return articles;
    }

    public static Collection<Article> findBy(Collection<Integer> ids) {
        createDatabaseConnection();
        Collection<Article> articles = new ArrayList<>();


        try {
            Statement statement = databaseConnection.createStatement();
            String collect = ids.stream().map(id -> id.toString()).collect(Collectors.joining(","));
            String getArticlesFromIds = "SELECT * FROM Storage WHERE StorageID IN ('"+ collect + "')";

            ResultSet resultSet = statement.executeQuery(getArticlesFromIds);
            while (resultSet.next()) {
                String articleName = resultSet.getString("ArticleName");
                int id = resultSet.getInt("StorageID");
                String articleNr = resultSet.getString("ArticleNr");

                Article article = new Article(articleName, id,articleNr);
                articles.add(article);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return articles;
    }


    //put the Userinput into database
    public static void insertEvent(Event event) {
        //Create connection to Database
        createDatabaseConnection();

        //Insert Userinput into Database
        try {
            Statement statement = databaseConnection.createStatement();

            String sqlQuery = "INSERT INTO Event (id,EventName,Place,ContactPerson,EventDate) VALUES ('"+event.getId()+"','" + event.getName() + "','" + event.getPlace() + "','" + event.getContactPerson()+ "','" +event.getDate().toString() + "')";

            statement.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    //check if there is already a connection. If it's not, create one
    private static void createDatabaseConnection() {

        if (databaseConnection == null) {

            try {
                Class.forName(JDBC_DRIVER);

                System.out.println("Baut Verbindung auf");
                databaseConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();


            }
        }
    }


    public static Collection<Event> searchTodaysEvents(LocalDate date) {
        createDatabaseConnection();
        Collection<Event> events = new ArrayList<>();
        try {

            Statement statement = databaseConnection.createStatement();

            String findTodaysEvent = "SELECT * FROM Event WHERE EventDate = '" + date + "'";

            ResultSet resultSet = statement.executeQuery(findTodaysEvent);

            while (resultSet.next()) {

                String name = resultSet.getString("EventName");
                String eventDate = resultSet.getDate("EventDate").toString();
                String place = resultSet.getString("Place");
                String contactPerson = resultSet.getString("contactPerson");
                String id = resultSet.getString("id");


                System.out.println(name + ", " + eventDate + ", " + place + ", " + contactPerson);

                Event event = new Event(id, name, eventDate, place, contactPerson);
                events.add(event);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return events;
    }


    public static Collection<EventArticle> loadBy (Collection<String> ids){
        createDatabaseConnection();
       Collection<EventArticle> eventArticles = new ArrayList<>();

        String eventIds = ids.stream().collect(Collectors.joining("','"));

        try {
            Statement statement = databaseConnection.createStatement();

            String sqlQuery = "SELECT * FROM Event_Article WHERE event_id IN('"+eventIds+"')";

            ResultSet resultSet = statement.executeQuery(sqlQuery);


            while(resultSet.next()){

                int articleId = resultSet.getInt("article_id");
                String eventId = resultSet.getString("event_id");
                eventArticles.add(new EventArticle(articleId,eventId));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }return eventArticles;
    }

    public static Collection<Article> getBy(Collection<String> articleNrs){
        createDatabaseConnection();
        Collection<Article> articles = new ArrayList<>();

        try {


            String collect = articleNrs.stream().collect(Collectors.joining("','"));

            Statement statement = databaseConnection.createStatement();

            String sqlQuery ="SELECT * FROM Storage WHERE ArticleNr IN ('"+collect+"') ";

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()){


                String articleName = resultSet.getString("ArticleName");
                String articleNr = resultSet.getString("ArticleNr");
                int articleId = resultSet.getInt("StorageId");

                Article article = new Article(articleName,articleId,articleNr);
                articles.add(article);


            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return articles;
    }

    public static void insertConnectionToEventArticleTable (Collection<EventArticle> eventArticles){
        createDatabaseConnection();

        try{
            Statement statement = databaseConnection.createStatement();
           for(EventArticle asda : eventArticles) {
               String sqlQuery = "INSERT INTO event_article (article_id,event_id) VALUES ('" + asda.getArticleId() + "','" + asda.getEventId()+"')";

               statement.executeUpdate(sqlQuery);
           }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

}



