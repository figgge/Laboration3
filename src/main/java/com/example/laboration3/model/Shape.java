package com.example.laboration3.model;

import com.example.laboration3.controller.Position;
import com.example.laboration3.controller.ShapeSelected;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Shape {
    public ShapeSelected shapeSelected;
    public Position position;
    public Color color;
    public double size;

    public Shape(ShapeSelected shapeSelected, Position position, Color color, double size) {
        this.shapeSelected = shapeSelected;
        this.position = position;
        this.color = color;
        this.size = size;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape shape)) return false;

        if (Double.compare(shape.size, size) != 0) return false;
        if (shapeSelected != shape.shapeSelected) return false;
        if (!Objects.equals(position, shape.position)) return false;
        return Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = shapeSelected != null ? shapeSelected.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        temp = Double.doubleToLongBits(size);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public abstract void drawShape(GraphicsContext context);
    public abstract boolean isSelectable(Position position);

    double halfSize() {
        return size / 2;
    }
}
