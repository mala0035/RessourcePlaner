package sample;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;


public class Event {

    private final StringProperty nameOfEvent;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty place;
    private final StringProperty contactPerson;

    public Event(){
        this.nameOfEvent = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>();
        this.place = new SimpleStringProperty();
        this.contactPerson = new SimpleStringProperty();
    }


//Teständerung
//Teständerung 2

    public String getNameOfEvent() {
        return nameOfEvent.get();
    }

    public void setNameOfEvent(String nameOfEvent){
        this.nameOfEvent.set(nameOfEvent);
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public String getPlace() {
        return place.get();
    }

    public void setPlace(String nameOfEvent) {
        this.place.set(nameOfEvent);
    }

    public String getContactPerson() {
        return contactPerson.get();
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson.set(contactPerson);
    }
}
