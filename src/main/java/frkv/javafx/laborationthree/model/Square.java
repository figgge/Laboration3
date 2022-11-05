package frkv.javafx.laborationthree.model;

import frkv.javafx.laborationthree.controller.Position;
import frkv.javafx.laborationthree.controller.ShapeSelected;
import javafx.beans.property.ObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {

    public Square(ShapeSelected shape, Position pos, ObjectProperty<Color> col, double sizeValue) {
        super(shape, pos, col, sizeValue);
    }

    @Override
    public void drawShape(GraphicsContext context) {
        context.setFill(getColorObjectProperty());
        context.setStroke(borderColor.getValue());
        context.strokeRect(position.x() - halfSize(), position.y() - halfSize(), getSizeProperty(), getSizeProperty());
        context.fillRect(position.x() - halfSize(), position.y() - halfSize(),
                getSizeProperty(), getSizeProperty());
    }


    @Override
    public boolean isSelectable(Position position) {
        boolean xSelectable = position.x() >= this.position.x() - halfSize() && position.x() <= this.position.x() - halfSize() + getSizeProperty();
        boolean ySelectable = position.y() >= this.position.y() - halfSize() && position.y() <= this.position.y() - halfSize() + getSizeProperty();
        return xSelectable && ySelectable;
    }


}
