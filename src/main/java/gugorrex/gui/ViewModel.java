package gugorrex.gui;

import gugorrex.app.App;
import gugorrex.events.listeners.InitializationDoneListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class ViewModel implements InitializationDoneListener {

    private App app;
    private double originalWidth;
    private double originalHeight;
    private double originalSpacing;

    @FXML public HBox addHBOX;
    @FXML public Line addLine;
    @FXML public Line resultLine;
    @FXML public VBox mainVBox;
    @FXML public ListView bDaysListView;

    @FXML
    private void initialize() {
        app = App.getInstance();
        app.addListener(this);
    }

    @FXML
    public void addBirthday(ActionEvent actionEvent) {

    }

    // Everything bound to stage must be initialized after initialization of app is done!
    @Override
    public void initializationDone() {
        originalWidth = app.getStage().getWidth();
        originalHeight = app.getStage().getHeight();
        originalSpacing = addHBOX.getSpacing();

        app.getStage().widthProperty().addListener((observableValue, number, t1) -> Platform.runLater(() -> {
                    addHBOX.setSpacing(Math.max((app.getStage().getWidth() - originalWidth) / addHBOX.getChildren().size(), originalSpacing));
                }));

        addLine.endXProperty().bind(app.getStage().widthProperty());
        resultLine.endXProperty().bind(app.getStage().widthProperty());
        app.getStage().minWidthProperty().bind(mainVBox.minWidthProperty());
        app.getStage().minHeightProperty().bind(mainVBox.minHeightProperty());
    }
}
