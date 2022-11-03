package com.example.laboration3.model;

import com.example.laboration3.controller.Position;
import com.example.laboration3.controller.ShapeSelected;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape{

    public Square(ShapeSelected shape, Position pos, Color col, double sizeValue) {
        super(shape, pos, col, sizeValue);
    }

    @Override
    public void drawShape(GraphicsContext context) {
        context.setFill(color.get());
        context.fillRect(position.get().x() - (size.doubleValue()/ 2), position.get().y() - (size.doubleValue() / 2),
                size.doubleValue(), size.doubleValue());
    }
}
