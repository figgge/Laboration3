package com.example.laboration3.model;


import com.example.laboration3.controller.Position;
import com.example.laboration3.controller.ShapeSelected;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintModel {
    private final ObservableList<Shape2> shapes = FXCollections.observableArrayList();
    private final ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
    private final ObservableList<ShapeSelected> shapeChoiceBox = FXCollections.observableArrayList(ShapeSelected.values());
    private final ObjectProperty<ShapeSelected> shapeSelected = new SimpleObjectProperty<>(ShapeSelected.CIRCLE);
    private final ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(10);

    public PaintModel() {
    }

    public ObjectProperty<ShapeSelected> shapeSelectedProperty() {
        return shapeSelected;
    }

    public ObservableList<ShapeSelected> getShapeChoiceBox() {
        return shapeChoiceBox;
    }


    public ObjectProperty<Color> colorProperty() {
        return color;
    }


    public Integer getSizeSpinner() {
        return sizeSpinner.get();
    }

    public ObjectProperty<Integer> sizeSpinnerProperty() {
        return sizeSpinner;
    }

    public ObservableList<Shape2> getShapes() {
        return shapes;
    }

    public Shape2 createShape(Position position) {
        return switch (shapeSelected.getValue()) {
            case CIRCLE -> new Circle2(ShapeSelected.CIRCLE, position, colorProperty().getValue(), getSizeSpinner());
            case SQUARE -> new Square2(ShapeSelected.SQUARE, position, colorProperty().getValue(), getSizeSpinner());
        };
    }


    public void drawShapes(GraphicsContext context) {
        for (Shape2 shape : shapes) {
            shape.drawShape(context);
        }
    }
}
