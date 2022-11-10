package frkv.javafx.laborationthree.model;

import frkv.javafx.laborationthree.controller.Position;
import frkv.javafx.laborationthree.controller.ShapeSelected;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;


public abstract class Shape {
    public ShapeSelected shapeSelected;
    public Position position;
    public ObjectProperty<Color> colorObjectProperty = new SimpleObjectProperty<>();
    public ObjectProperty<Color> borderColor = new SimpleObjectProperty<>();
    public ObjectProperty<Double> sizeProperty = new SimpleObjectProperty<>();
    public boolean isSelected;

    public Shape(ShapeSelected shapeSelected, Position position, ObjectProperty<Color> color, Double size) {
        this.shapeSelected = shapeSelected;
        this.position = position;
        this.sizeProperty.setValue(size);
        this.colorObjectProperty.setValue(color.getValue());
        this.borderColor.setValue(color.getValue());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape shape)) return false;

        if (isSelected != shape.isSelected) return false;
        if (shapeSelected != shape.shapeSelected) return false;
        if (!Objects.equals(position, shape.position)) return false;
        if (!Objects.equals(colorObjectProperty, shape.colorObjectProperty))
            return false;
        if (!Objects.equals(borderColor, shape.borderColor)) return false;
        return Objects.equals(sizeProperty, shape.sizeProperty);
    }

    @Override
    public int hashCode() {
        int result = shapeSelected != null ? shapeSelected.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (colorObjectProperty != null ? colorObjectProperty.hashCode() : 0);
        result = 31 * result + (borderColor != null ? borderColor.hashCode() : 0);
        result = 31 * result + (sizeProperty != null ? sizeProperty.hashCode() : 0);
        result = 31 * result + (isSelected ? 1 : 0);
        return result;
    }

    public abstract void drawShape(GraphicsContext context);

    public abstract void drawBorderColor(GraphicsContext context);

    public abstract boolean isSelectable(Position position);

    public abstract String svg();

    double halfSize() {
        return sizeProperty.getValue() / 2;
    }

    public Color getColorObjectProperty() {
        return colorObjectProperty.get();
    }


    public void setColorObjectProperty(Color colorObjectProperty) {
        this.colorObjectProperty.set(colorObjectProperty);
    }

    public Double getSizeProperty() {
        return sizeProperty.get();
    }


    public void setSizeProperty(Double sizeProperty) {
        this.sizeProperty.set(sizeProperty);
    }

    public void setSelected(boolean selected) {
        if (selected)
            setBorderColor();
        else
            setBorderColor(colorObjectProperty.getValue());
        isSelected = selected;
    }

    public void setBorderColor() {
        if (borderColor.getValue().equals(Color.BLACK))
            this.borderColor.set(Color.FUCHSIA);
        else
            this.borderColor.set(Color.BLACK);
    }

    public void setBorderColor(Color color) {
        this.borderColor.set(color);
    }

}
