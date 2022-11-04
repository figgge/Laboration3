package com.example.laboration3.controller;

import com.example.laboration3.model.PaintModel;
import com.example.laboration3.model.Shape;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayDeque;
import java.util.Deque;

public class PaintController {
    private final PaintModel model = new PaintModel();
    private final DoubleProperty size = new SimpleDoubleProperty();
    public CheckBox selectShapeCheckBox;
    public Button undoButton;
    public Button redoButton;
    @FXML
    private MenuItem saveAsClicked;
    @FXML
    private MenuItem menuClose;
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
    private static final Deque<Runnable> undoStack = new ArrayDeque<>();

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
        Shape shape = model.createShape(position);


        if (selectShapeCheckBox.isSelected()) {
            for (int i = 0; i < model.getShapes().size(); i++) {
                if (model.getShapes().get(i).isSelectable(new Position(mouseEvent.getX(), mouseEvent.getY())))
                    System.out.println("Is selectable");
            }
        } else {
            model.getShapes().add(shape);
            model.addUndoShape(shape);
        }
    }


    public boolean isSelectMode(ActionEvent actionEvent) {
        return actionEvent.isConsumed();
    }

    public void closeApplication() {
        Platform.exit();
    }

    public void onUndoClicked(ActionEvent actionEvent) {
        System.out.println("undo clicked");
        model.undo();
    }

    public void onRedoClicked(ActionEvent actionEvent) {
    }
}