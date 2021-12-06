import gugorrex.model.data.Birthday;
import gugorrex.model.data.BirthdayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BirthdayListTest {

    static BirthdayList birthdayList = new BirthdayList();

    @BeforeAll
    public static void setUp() {
        birthdayList.addBirthday(new Birthday("Max Muster", LocalDate.of(2000, 12, 24)));
        birthdayList.addBirthday(new Birthday("Maxine Muster", LocalDate.of(1999, 6, 12)));
    }

    @Test
    public void testSaveNoException() {
        try {
            birthdayList.save("BirthdayListTest.json");
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    public void testLoadNoException() {
        try {
            new BirthdayList().load("BirthdayListTest.json");
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    public void testSaveLoad() {
        birthdayList.save("BirthdayListTest.json");
        BirthdayList newList = new BirthdayList();
        newList.load("BirthdayListTest.json");

        Assertions.assertEquals(birthdayList.toJson(), newList.toJson());
        Assertions.assertEquals(birthdayList.toPrettyJson(), newList.toPrettyJson());
        Assertions.assertEquals(birthdayList, newList);
    }

}
