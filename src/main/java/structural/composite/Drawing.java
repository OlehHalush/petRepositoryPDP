package structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Drawing implements Shape {

    List<Shape> shapes = new ArrayList<>();

    @Override
    public void fillShapeColor(String fillColor) {
        for (Shape shape : shapes) {
            shape.fillShapeColor(fillColor);
        }
    }

    public void add(Shape shape){
        shapes.add(shape);
    }

    public void remove(Shape shape) {
        shapes.remove(shape);
    }

    public void clear() {
        System.out.println("Clearing all shapes");
        this.shapes.clear();
    }
}
