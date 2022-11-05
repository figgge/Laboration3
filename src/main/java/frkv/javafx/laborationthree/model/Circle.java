package frkv.javafx.laborationthree.model;

import frkv.javafx.laborationthree.controller.Position;
import frkv.javafx.laborationthree.controller.ShapeSelected;
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

    @Override
    public String svg() {
        return "<circle" +
                " cx=\"" + position.x() + "\"" +
                " cy=\"" + position.y() + "\"" +
                " r=\"" + sizeProperty.getValue() + "\"" +
                "fill=\"" + colorObjectProperty.getValue() + "\"/>";
    }

}

