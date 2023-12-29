package ocp;

public class RunTest {
    public static void main(String[] args) {
        AreaCalculator squareCalculator = new AreaCalculator(new Square(1.0d));
        AreaCalculator circleCalculator = new AreaCalculator(new Circle(2.0d));
        AreaCalculator rectangleCalculator = new AreaCalculator(new Rectangle(3.0d, 4.0d));

        squareCalculator.calculateShapeArea();
        circleCalculator.calculateShapeArea();
        rectangleCalculator.calculateShapeArea();
    }
}
