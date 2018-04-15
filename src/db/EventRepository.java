package db;

import model.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class EventRepository extends BaseRepository {

    //Query to put the Userinput into database
    public static void insertEvent(Event event) {

        try {
            Statement statement = getDatabaseConnection().createStatement();
            String sqlQuery = "INSERT INTO Event (id,EventName,Place,ContactPerson,EventDate) " +
                    "VALUES ('" + event.getId() + "','" + event.getName() + "','" + event.getPlace() + "','" + event.getContactPerson() + "','" + event.getDate().toString() + "')";
            statement.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Query to search the Event on a given date
    public static Collection<Event> searchTodaysEvents(LocalDate date) {
        Collection<Event> events = new ArrayList<>();
        try {
            Statement statement = getDatabaseConnection().createStatement();
            String findTodaysEvent = "SELECT * FROM Event WHERE EventDate = '" + date + "'";
            ResultSet resultSet = statement.executeQuery(findTodaysEvent);

            while (resultSet.next()) {
                String name = resultSet.getString("EventName");
                String eventDate = resultSet.getDate("EventDate").toString();
                String place = resultSet.getString("Place");
                String contactPerson = resultSet.getString("contactPerson");
                String id = resultSet.getString("id");
                Event event = new Event(id, name, eventDate, place, contactPerson);
                events.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return events;
    }
}
