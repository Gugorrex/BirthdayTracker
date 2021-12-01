package gugorrex.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BirthdayTracker");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show();
    }
}
