package com.example.laboration3.model;

import com.example.laboration3.controller.Position;
import com.example.laboration3.controller.ShapeSelected;
import javafx.beans.property.ObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    public Circle(ShapeSelected shape, Position pos, ObjectProperty<Color> col, Double sizeValue) {
        super(shape, pos, col, sizeValue);
    }

    @Override
    public void drawShape(GraphicsContext context) {
        context.setFill(getColorObjectProperty());
        context.setStroke(borderColor.getValue());
        context.strokeOval(position.x() - halfSize(), position.y() - halfSize(), getSizeProperty(), getSizeProperty());
        context.fillOval(position.x() - halfSize(), position.y() - halfSize(),
                getSizeProperty(), getSizeProperty());
    }

    @Override
    public boolean isSelectable(Position position) {
        double xRange = position.x() - this.position.x();
        double yRange = position.y() - this.position.y();
        double circleRange = Math.sqrt((xRange * xRange) + (yRange * yRange));
        return circleRange <= halfSize();
    }

}

