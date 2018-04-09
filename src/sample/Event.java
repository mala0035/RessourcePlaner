package sample;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;


public class Event {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.GERMANY );

    private final String name;

    private final LocalDate date;

    private final String place;

    private final String contactPerson;

    private final String id;


    //Constructor
    public Event(String name,String date,String place, String contactPerson){
        this.name=name;
        this.date=parse(date);
        this.place=place;
        this.contactPerson=contactPerson;
        this.id = UUID.randomUUID().toString();
    }

    public Event(String id, String name, String date, String place, String contactPerson){
        this.id = id;
        this.date= parse(date);
        this.name = name;
        this.place = place;
        this.contactPerson = contactPerson;
    }



    //parse the date
    public LocalDate parse (String dateToParse){
    return LocalDate.parse(dateToParse,FORMAT);
    }



    //getter
    public String getName(){
        return name;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getPlace(){
        return place;
    }

    public String getContactPerson(){
        return contactPerson;
    }


    public String getId (){
        return id;
    }


}
