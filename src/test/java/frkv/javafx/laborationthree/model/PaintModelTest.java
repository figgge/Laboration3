package frkv.javafx.laborationthree.model;


import frkv.javafx.laborationthree.controller.Position;
import frkv.javafx.laborationthree.controller.ShapeSelected;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaintModelTest {



    @Test
    void setShapeSelectedToCircle() {
        PaintModel model = new PaintModel();

        model.shapeSelectedProperty().setValue(ShapeSelected.CIRCLE);
        model.createShape(new Position(100,100));

        assertEquals(ShapeSelected.CIRCLE,model.shapeSelectedProperty().getValue(),"Selected shape should be Circle");
    }

    @Test
    void addShapeToUndoStack() {
        PaintModel model = new PaintModel();
        Circle circle = new Circle(ShapeSelected.CIRCLE, new Position(0.0,0.0), model.colorProperty(), 25.0);

        model.addUndoRedoRunnable(circle, false);

        var expected = 1;
        var actual = model.undoStack.size();

        assertEquals(expected,actual, "undoStack size should be 1");
    }

    @Test
    void removeShapeFromUndoStack() {
        PaintModel model = new PaintModel();
        Circle circle = new Circle(ShapeSelected.CIRCLE, new Position(0.0,0.0), model.colorProperty(), 25.0);

        model.addUndoRedoRunnable(circle, false);
        model.undo();

        var expected = 0;
        var actual = model.undoStack.size();

        assertEquals(expected,actual, "undoStack should be empty");
    }






}