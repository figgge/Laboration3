package com.example.laboration3.model;

import com.example.laboration3.controller.Position;
import com.example.laboration3.controller.ShapeSelected;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Shape2 {
    public ShapeSelected shapeSelected;
    public Position position;
    public Color color;
    public double size;

    public Shape2(ShapeSelected shapeSelected, Position position, Color color, double size) {
        this.shapeSelected = shapeSelected;
        this.position = position;
        this.color = color;
        this.size = size;
    }


    //    public Shape2(ShapeSelected shapeSelected, Position position, Color color, double size) {
//        this.shapeSelected = shapeSelected;
//        this.position = position;
//        this.color = color;
//        this.size = size;
//    }

    public ShapeSelected getShapeSelected() {
        return shapeSelected;
    }

    public void setShapeSelected(ShapeSelected shapeSelected) {
        this.shapeSelected = shapeSelected;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape2 shape2)) return false;

        if (Double.compare(shape2.size, size) != 0) return false;
        if (shapeSelected != shape2.shapeSelected) return false;
        if (!Objects.equals(position, shape2.position)) return false;
        return Objects.equals(color, shape2.color);
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
}
