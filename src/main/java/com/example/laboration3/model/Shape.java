package com.example.laboration3.model;

import com.example.laboration3.controller.Position;
import com.example.laboration3.controller.ShapeSelected;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Shape {
    public ShapeSelected shapeSelected;
    public Position position;
    public ObjectProperty<Color> colorObjectProperty= new SimpleObjectProperty<>();
    public Color borderColor;
    public double size;
    public boolean isSelected;

    public Shape(ShapeSelected shapeSelected, Position position, ObjectProperty<Color> color, double size) {
        this.shapeSelected = shapeSelected;
        this.position = position;
//        this.color = color;
        this.borderColor = color.getValue();
        this.size = size;
        this.colorObjectProperty.setValue(color.getValue());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape shape)) return false;

        if (Double.compare(shape.size, size) != 0) return false;
        if (isSelected != shape.isSelected) return false;
        if (shapeSelected != shape.shapeSelected) return false;
        if (!Objects.equals(position, shape.position)) return false;
        if (!Objects.equals(colorObjectProperty, shape.colorObjectProperty))
            return false;
        return Objects.equals(borderColor, shape.borderColor);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = shapeSelected != null ? shapeSelected.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (colorObjectProperty != null ? colorObjectProperty.hashCode() : 0);
        result = 31 * result + (borderColor != null ? borderColor.hashCode() : 0);
        temp = Double.doubleToLongBits(size);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isSelected ? 1 : 0);
        return result;
    }

    public abstract void drawShape(GraphicsContext context);
    public abstract boolean isSelectable(Position position);

    double halfSize() {
        return size / 2;
    }

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

    public Color getColorObjectProperty() {
        return colorObjectProperty.get();
    }

    public ObjectProperty<Color> colorObjectPropertyProperty() {
        return colorObjectProperty;
    }

    public void setColorObjectProperty(Color colorObjectProperty) {
        this.colorObjectProperty.set(colorObjectProperty);
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
