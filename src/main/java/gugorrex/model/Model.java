package gugorrex.model;

import gugorrex.model.data.BirthdayList;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}
