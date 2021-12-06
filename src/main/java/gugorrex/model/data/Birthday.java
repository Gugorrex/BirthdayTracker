package gugorrex.model.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// changed Record to class and correct all code lines (because since Java 15 Deserialization of Records is broken because of finalizing elements)
public class Birthday {
    private String name;
    private LocalDate birthday;

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Birthday(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public StringProperty dateToString() {
        return new SimpleStringProperty(birthday.format(formatter));
    }

    public String toJson() {
        return BirthdayGsonProxy.getGson().toJson(this);
    }

    public String toPrettyJson() {
        return BirthdayGsonProxy.getPrettyGson().toJson(this);
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Birthday birthday1 = (Birthday) o;

        if (!Objects.equals(name, birthday1.name)) return false;
        return Objects.equals(birthday, birthday1.birthday);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}
