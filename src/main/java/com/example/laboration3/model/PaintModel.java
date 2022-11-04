package com.example.laboration3.model;


import com.example.laboration3.controller.Position;
import com.example.laboration3.controller.ShapeSelected;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Deque;

public class PaintModel {
    private final ObservableList<Shape> shapes = FXCollections.observableArrayList();
    private final ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
    private final ObservableList<ShapeSelected> shapeChoiceBox = FXCollections.observableArrayList(ShapeSelected.values());
    private final ObjectProperty<ShapeSelected> shapeSelected = new SimpleObjectProperty<>(ShapeSelected.CIRCLE);
    private final ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(10);
    private static final Deque<Runnable> undoStack = new ArrayDeque<>();



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

    public ObservableList<Shape> getShapes() {
        return shapes;
    }

    public Shape createShape(Position position) {
        return switch (shapeSelected.getValue()) {
            case CIRCLE -> new Circle(ShapeSelected.CIRCLE, position, colorProperty().getValue(), getSizeSpinner());
            case SQUARE -> new Square(ShapeSelected.SQUARE, position, colorProperty().getValue(), getSizeSpinner());
        };
    }


    public void drawShapes(GraphicsContext context) {
        context.clearRect(0,0,800,600);
        for (Shape shape : shapes) {
            shape.drawShape(context);
        }
    }

    public void addUndoShape(Shape shape) {
        Runnable undo = () -> shapes.remove(shape);
        undoStack.push(undo);
    }

    public void undo() {
        if (undoStack.size() > 0) {
            Runnable undo = undoStack.pop();
            undo.run();
        }
        System.out.println(shapes);
    }
}
