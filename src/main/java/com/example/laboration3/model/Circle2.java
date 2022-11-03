package com.example.laboration3.model;

import com.example.laboration3.controller.Position;
import com.example.laboration3.controller.ShapeSelected;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle2 extends Shape2{
    public Circle2(ShapeSelected shape, Position pos, Color col, double sizeValue) {
        super(shape, pos, col, sizeValue);
    }

    @Override
    public void drawShape(GraphicsContext context) {
        context.setFill(color);
        context.fillOval(position.x() - (size/ 2), position.y() - (size / 2),
                size, size);
    }

}
