package gugorrex.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record Birthday(StringProperty name, LocalDate birthday) {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public StringProperty dateToString() {
        return new SimpleStringProperty(birthday.format(formatter));
    }
}
