package gugorrex.app;

import gugorrex.events.listeners.InitializationDoneListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * App initializes our window and holds the corresponding data.
 * App is a singleton.
 * @author Gugorrex
 */
public class App extends Application {

    private static App instance;
    private static final Logger logger = LogManager.getLogger(App.class);

    private Stage stage = null;
    private List<InitializationDoneListener> listeners = new ArrayList<InitializationDoneListener>();

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        logger.info("Setting up stage...");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BirthdayTracker");
        stage.setScene(scene);
        stage.setResizable(true);

        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show();

        instance.stage = stage;

        logger.info("stage is set up");

        // We need to use instance.listeners because start creates an own instance of App
        // This Singleton pattern violation seems to be unavoidable
        for (InitializationDoneListener l : instance.listeners) {
            l.initializationDone();
        }
    }

    public Stage getStage() {
        if (stage == null) {
            logger.warn("stage is null (probably start() was not called yet)");
        }
        return stage;
    }

    public void addListener(InitializationDoneListener listener) {
        listeners.add(listener);
    }
}
