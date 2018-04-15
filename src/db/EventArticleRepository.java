package db;
import model.EventArticle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class EventArticleRepository extends BaseRepository {

    //Query to load Article ID and EventID  by Event ID
    public static Collection<EventArticle> loadBy(Collection<String> ids) {
        Collection<EventArticle> eventArticles = new ArrayList<>();
        String eventIds = ids.stream().collect(Collectors.joining("','"));

        try {
            Statement statement = getDatabaseConnection().createStatement();
            String sqlQuery = "SELECT * FROM Event_Article WHERE event_id IN('" + eventIds + "')";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int articleId = resultSet.getInt("article_id");
                String eventId = resultSet.getString("event_id");
                eventArticles.add(new EventArticle(articleId, eventId));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return eventArticles;
    }

    //Query to insert the connection between Article ID and Event ID
    public static void insertConnectionToEventArticleTable(Collection<EventArticle> eventArticles) {
        try {
            Statement statement = getDatabaseConnection().createStatement();
            for (EventArticle asda : eventArticles) {
                String sqlQuery = "INSERT INTO event_article (article_id,event_id) VALUES ('" + asda.getArticleId() + "','" + asda.getEventId() + "')";
                statement.executeUpdate(sqlQuery);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
