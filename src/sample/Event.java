package sample;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Event {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.GERMANY);

    private final String name;

    private final LocalDate date;

    private final String place;

    private final String contactPerson;



    //Constructor
    public Event(String name,String date,String place, String contactPerson){
        this.name=name;
        this.date=parse(date);
        this.place=place;
        this.contactPerson=contactPerson;

    }

    //parse the date
    private LocalDate parse (String dateToParse){
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





}
