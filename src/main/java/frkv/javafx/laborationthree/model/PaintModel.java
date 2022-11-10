package frkv.javafx.laborationthree.model;


import frkv.javafx.laborationthree.controller.Position;
import frkv.javafx.laborationthree.controller.ShapeSelected;
import frkv.javafx.laborationthree.file.File;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;

public class PaintModel {
    private final ObservableList<Shape> shapes = FXCollections.observableArrayList(param -> new Observable[]{
            param.colorObjectProperty, param.sizeProperty, param.borderColor
    });
    private final ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
    private final ObservableList<ShapeSelected> shapeChoiceBox = FXCollections.observableArrayList(ShapeSelected.values());
    private final ObjectProperty<ShapeSelected> shapeSelected = new SimpleObjectProperty<>(ShapeSelected.CIRCLE);
    private final ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(25);
    protected final Deque<Runnable> undoStack = new ArrayDeque<>();
    private final Deque<Runnable> redoStack = new ArrayDeque<>();
    private final Circle notNull = new Circle(ShapeSelected.CIRCLE, new Position(0,0), colorProperty(), 0.0);


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
            case CIRCLE -> new Circle(ShapeSelected.CIRCLE, position, colorProperty(), getSizeSpinner().doubleValue());
            case SQUARE -> new Square(ShapeSelected.SQUARE, position, colorProperty(), getSizeSpinner().doubleValue());
        };
    }

    public Shape createShape(Shape shape) {
        return switch (shapeSelected.getValue()) {
            case CIRCLE ->
                    new Circle(shape.shapeSelected, shape.position, shape.colorObjectProperty, shape.sizeProperty.getValue());
            case SQUARE ->
                    new Square(shape.shapeSelected, shape.position, shape.colorObjectProperty, shape.sizeProperty.getValue());
        };
    }


    public void drawShapes(GraphicsContext context) {
        context.clearRect(0, 0, 800, 600);
        for (Shape shape : shapes) {
            shape.drawShape(context);
        }
    }


    public void addUndoRedoRunnable(Shape shape, boolean isSelectMode) {
        if (isSelectMode) {
            Shape oldShape = createShape(shape);
            Runnable undo = () -> {
                shapes.remove(shape);
                shapes.add(oldShape);
                Runnable redo = () -> {
                    shapes.remove(oldShape);
                    shapes.add(shape);
                };
                redoStack.push(redo);
            };
            undoStack.push(undo);
        } else {
            Runnable undo = () -> {
                shapes.remove(shape);
                Runnable redo = () -> shapes.add(shape);
                redoStack.push(redo);
            };
            undoStack.push(undo);

        }
    }

    public void undo() {
        if (undoStack.size() > 0) {
            Runnable undo = undoStack.pop();
            undo.run();
        }
    }


    public Shape getSelectedShape() {
        return getShapes().stream().filter(shape -> shape.isSelected).findFirst().orElse(notNull);
    }

    public void redo() {
        if (redoStack.size() > 0) {
            Runnable redo = redoStack.pop();
            redo.run();
        }
    }

    public void changeSelectedShapes() {
        getShapes().stream().filter(shape -> shape.isSelected)
                .forEach(this::setChanges);
    }

    public void saveToFile(Path file) {
        System.out.println(file);
        File.saveSVGFile(file, getShapes());
    }

    public void setChanges(Shape shape) {
        Color color = getChangedColor();
        setNewColor(shape, color);
        shape.setSizeProperty(getSize());
        shape.setSelected(false);
    }

    private static void setNewColor(Shape shape, Color color) {
        shape.setColorObjectProperty(color);
        shape.setBorderColor(color);
    }

    private double getSize() {
        return getSizeSpinner().doubleValue();
    }

    private Color getChangedColor() {
        return colorProperty().getValue();
    }


}
