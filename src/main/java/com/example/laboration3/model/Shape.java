package com.example.laboration3.model;

import com.example.laboration3.controller.Position;
import com.example.laboration3.controller.ShapeSelected;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Shape {

    ObjectProperty<ShapeSelected> shapeSelected = new SimpleObjectProperty<>();
    ObjectProperty<Position> position = new SimpleObjectProperty<>();
    ObjectProperty<Color> color = new SimpleObjectProperty<>();
    DoubleProperty size = new SimpleDoubleProperty();


    public Shape(ShapeSelected shape, Position pos, Color col,  double sizeValue) {
        shapeSelected.set(shape);
        position.set(pos);
        color.set(col);
        size.set(sizeValue);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape shape)) return false;

        if (!Objects.equals(shapeSelected, shape.shapeSelected))
            return false;
        if (!Objects.equals(position, shape.position)) return false;
        if (!Objects.equals(color, shape.color)) return false;
        return Objects.equals(size, shape.size);
    }

    @Override
    public int hashCode() {
        int result = shapeSelected != null ? shapeSelected.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }

    public abstract void drawShape(GraphicsContext context);




}
