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
        context.fillOval(position.x() - halfSize(), position.y() - halfSize(),
                size, size);
    }

    @Override
    public boolean isSelectable(Position position) {
        double xRange = position.x() - this.position.x();
        double yRange = position.y() - this.position.y();
        double circleRange = Math.sqrt((xRange * xRange) + (yRange * yRange));
        return circleRange <= halfSize();
    }

}

