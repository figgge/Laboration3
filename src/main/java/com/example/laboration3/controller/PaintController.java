package com.example.laboration3.controller;

import com.example.laboration3.model.PaintModel;
import com.example.laboration3.model.Shape2;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;

public class PaintController {
    private final PaintModel model = new PaintModel();
    private final DoubleProperty size = new SimpleDoubleProperty();
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

    public void initialize() {
        context = canvas.getGraphicsContext2D();
        shapeChoiceBox.valueProperty().bindBidirectional(model.shapeSelectedProperty());
        shapeChoiceBox.setItems(model.getShapeChoiceBox());
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(model.sizeSpinnerProperty());
        context.clearRect(0,0,800,600);
        model.getShapes().addListener((ListChangeListener<? super Shape2>) change -> model.drawShapes(context));



    }


    public void onCanvasClicked(MouseEvent mouseEvent) {
        Position position = new Position(mouseEvent.getX(), mouseEvent.getY());
        Shape2 shape = model.createShape(position);
        model.getShapes().add(shape);





    }

}