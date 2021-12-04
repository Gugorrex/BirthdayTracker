package gugorrex.gui;

import gugorrex.app.App;
import gugorrex.events.listeners.InitializationDoneListener;
import gugorrex.model.Birthday;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.time.LocalDate;

public class ViewModel implements InitializationDoneListener {

    private App app;
    private double originalWidth;
    private double originalHeight;
    private double originalSpacing;

    @FXML public HBox addHBOX;
    @FXML public Line addLine;
    @FXML public Line resultLine;
    @FXML public VBox mainVBox;

    @FXML public TableView<Birthday> bDaysView;
    @FXML public TableColumn<Birthday, String> nameColumn;
    @FXML public TableColumn<Birthday, String> birthdayColumn;

    @FXML public TextField selectedName;
    @FXML public TextField selectedBD;
    @FXML public TextField selectedAge;

    @FXML
    private void initialize() {
        app = App.getInstance();
        app.addListener(this);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().name());
        birthdayColumn.setCellValueFactory(cellData -> cellData.getValue().dateToString());

        nameColumn.prefWidthProperty().bind(bDaysView.widthProperty().multiply(0.498));
        birthdayColumn.prefWidthProperty().bind(bDaysView.widthProperty().multiply(0.498));

        // test data
        Platform.runLater(() -> bDaysView.getItems().add(new Birthday(new SimpleStringProperty("Max"), LocalDate.now() )));
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

        //addLine.endXProperty().bind(app.getStage().widthProperty());
        //resultLine.endXProperty().bind(app.getStage().widthProperty());
        app.getStage().minWidthProperty().bind(mainVBox.minWidthProperty());
        app.getStage().minHeightProperty().bind(mainVBox.minHeightProperty());
    }

    public void onRemove(ActionEvent actionEvent) {
    }

    public void onExit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
