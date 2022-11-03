package com.example.laboration3.model;

import com.example.laboration3.controller.Position;
import com.example.laboration3.controller.ShapeSelected;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {

    public Square(ShapeSelected shape, Position pos, Color col, double sizeValue) {
        super(shape, pos, col, sizeValue);
    }

    @Override
    public void drawShape(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(position.x() - halfSize(), position.y() - halfSize(),
                size, size);
    }


    @Override
    public boolean isSelectable(Position position) {
        boolean xSelectable = position.x() >= this.position.x() - halfSize() && position.x() <= this.position.x() - halfSize() + size;
        boolean ySelectable = position.y() >= this.position.y() - halfSize() && position.y() <= this.position.y() - halfSize() + size;
        return xSelectable && ySelectable;
    }

}
