package ocp;

public class AreaCalculator {
    private Shape shape;

    public AreaCalculator(Shape shape) {
        this.shape = shape;
    }

    public void calculateShapeArea() {
        this.shape.calculateArea();
    }
}
