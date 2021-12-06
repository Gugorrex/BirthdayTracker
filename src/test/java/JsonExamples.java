import gugorrex.model.data.Birthday;
import gugorrex.model.data.BirthdayList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class JsonExamples {

    @Test
    public void birthdayJson() {
        System.out.println(new Birthday("Max Muster", LocalDate.of(2000, 12, 24)).toPrettyJson());
    }

    @Test
    public void birthdayListJson() {
        BirthdayList list = new BirthdayList();
        list.addBirthday(new Birthday("Max Muster", LocalDate.of(2000, 12, 24)));
        list.addBirthday(new Birthday("Maxine Muster", LocalDate.of(1999, 6, 12)));
        System.out.println(list.toPrettyJson());
    }
}
