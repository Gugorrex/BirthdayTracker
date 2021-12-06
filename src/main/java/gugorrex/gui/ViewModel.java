package gugorrex.gui;

import gugorrex.app.App;
import gugorrex.events.listeners.InitializationDoneListener;
import gugorrex.model.Model;
import gugorrex.model.data.Birthday;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.time.LocalDate;

public class ViewModel implements InitializationDoneListener {

    private App app;
    private Model model;
    private double originalWidth;
    private double originalHeight;
    private double originalSpacing;

    @FXML public HBox addHBOX;
    @FXML public Line addLine;
    @FXML public Line resultLine;
    @FXML public VBox mainVBox;

    @FXML public TextField addName;
    @FXML public TextField addDay;
    @FXML public TextField addMonth;
    @FXML public TextField addYear;

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

        model = Model.getInstance();

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        birthdayColumn.setCellValueFactory(cellData -> cellData.getValue().dateToString());

        nameColumn.prefWidthProperty().bind(bDaysView.widthProperty().multiply(0.498));
        birthdayColumn.prefWidthProperty().bind(bDaysView.widthProperty().multiply(0.498));

        Platform.runLater(() -> {
            for (Birthday birthday : model.getBirthdayList().getBirthdays()) {
                bDaysView.getItems().add(birthday);
            }
        });

        bDaysView.getSelectionModel().selectedItemProperty().addListener((observableValue, birthdayTableViewSelectionModel, t1) -> {
            Platform.runLater(() -> {
                Birthday birthday = bDaysView.getSelectionModel().getSelectedItem();
                if (birthday != null) {
                    selectedName.setText(birthday.getName());
                    selectedBD.setText(birthday.dateToString().getValue());
                    selectedAge.setText(Integer.toString(model.calculateYearDiff(birthday.getBirthday(), LocalDate.now())));
                }
            });
        });
    }

    @FXML
    public void addBirthday() {
        if (!addName.getText().isBlank()) {
            try {
                int year = Integer.parseInt(addYear.getText());
                int month = Integer.parseInt(addMonth.getText());
                int day = Integer.parseInt(addDay.getText());
                model.getBirthdayList().addBirthday(new Birthday(addName.getText(), LocalDate.of(year, month, day)));
                model.save();
                expensiveTableFullUpdate();
            } catch (NumberFormatException ignored) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("ERROR: Wrong Number Format!");
                a.setHeaderText("ERROR: Wrong Number Format!");
                a.setContentText("day, month and year must be an integer value (0-9) and no characters (a-z,...)");
                a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR: Missing Name!");
            a.setHeaderText("ERROR: Missing Name!");
            a.setContentText("the name must not be blank!");
            a.show();
        }
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

    public void onRemove() {
        Birthday birthday = bDaysView.getSelectionModel().getSelectedItem();
        bDaysView.getSelectionModel().clearSelection();
        selectedName.clear();
        selectedAge.clear();
        selectedBD.clear();
        model.getBirthdayList().removeBirthday(birthday);
        model.save();
        expensiveTableFullUpdate();
    }

    public void onExit() {
        Model.getInstance().exit(0);
    }

    // this is expensive but also a fallback if relative update (not implemented yet) fails
    private void expensiveTableFullUpdate() {
        Platform.runLater(() -> {
            bDaysView.getItems().clear();
            for (Birthday birthday : model.getBirthdayList().getBirthdays()) {
                bDaysView.getItems().add(birthday);
            }
        });
    }
}
