package gugorrex.model;

import gugorrex.model.data.BirthdayList;
import gugorrex.util.ExceptionHandler;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Model {

    public static final String FILENAME = "birthdays.json";

    private static final Logger logger = LogManager.getLogger(Model.class);

    private static Model instance;

    private final BirthdayList birthdayList = new BirthdayList();

    public Model() {
        birthdayList.load(FILENAME);
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public void exit(int code) {
        logger.info("Exiting with code " + code + " ...");
        birthdayList.save(FILENAME);
        Platform.exit();
        logger.info("Exit");
        System.exit(code);
    }

    public BirthdayList getBirthdayList() {
        return birthdayList;
    }

    public void save() {
        birthdayList.save(FILENAME);
    }

    public void load() {
        birthdayList.load(FILENAME);
    }

    public int calculateYearDiff(LocalDate before, LocalDate after) {
        if (before.isAfter(after)) {
            ExceptionHandler.exception(new Exception("calculateYearDiff(): the date 'before' is after 'after'" +
                    ", but it should be the other way around!"));
        }

        int diff = after.getYear() - before.getYear();

        if (before.getDayOfYear() > after.getDayOfYear()) {
            diff--;
        }

        return diff;
    }
}
