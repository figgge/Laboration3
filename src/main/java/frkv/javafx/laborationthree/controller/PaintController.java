package frkv.javafx.laborationthree.controller;


import frkv.javafx.laborationthree.model.PaintModel;
import frkv.javafx.laborationthree.model.Shape;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PaintController {
    public Stage stage;
    private final PaintModel model = new PaintModel();
    public CheckBox selectShapeCheckBox;
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext context;
    @FXML
    private ChoiceBox<ShapeSelected> shapeChoiceBox;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Spinner<Integer> sizeSpinner;

    public void initialize() {
        context = canvas.getGraphicsContext2D();
        shapeChoiceBox.valueProperty().bindBidirectional(model.shapeSelectedProperty());
        shapeChoiceBox.setItems(model.getShapeChoiceBox());
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(model.sizeSpinnerProperty());
        model.getShapes().addListener((ListChangeListener<? super Shape>) change -> model.drawShapes(context));


    }


    public void onCanvasClicked(MouseEvent mouseEvent) {
        Position position = new Position(mouseEvent.getX(), mouseEvent.getY());
//        Shape shape = model.createShape(position);

        if (selectShapeCheckBox.isSelected()) {
            selectShape(mouseEvent);
        } else {
            Shape shape = model.createShape(position);
            model.getShapes().add(shape);
            model.addUndoRedoRunnable(shape, selectShapeCheckBox.isSelected());
        }
    }

    private void selectShape(MouseEvent mouseEvent) {
        model.getShapes().forEach(shape1 -> shape1.setSelected(false));
        model.getShapes().stream().filter(shape1 -> shape1.isSelectable(new Position(mouseEvent.getX(), mouseEvent.getY())))
                .limit(1)
                .forEach(shape1 -> shape1.setSelected(true));
    }


    public boolean isSelectMode(ActionEvent actionEvent) {
        model.getShapes().forEach(shape1 -> shape1.setSelected(false));
        return actionEvent.isConsumed();
    }

    public void closeApplication() {
        Platform.exit();
    }

    public void onUndoClicked() {
        model.undo();
    }

    public void onRedoClicked() {
        model.redo();
    }

    public void changeSelected() {
        model.addUndoRedoRunnable(model.getSelectedShape(), selectShapeCheckBox.isSelected());
        model.changeSelectedShapes();
    }

    public void onSaveAction() {
        FileChooser fileChooser = getFileChooser();

        File file = fileChooser.showSaveDialog(stage);
        if (file != null)
            model.saveToFile(file.toPath());
    }

    private static FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG", "*.svg"));
        return fileChooser;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}