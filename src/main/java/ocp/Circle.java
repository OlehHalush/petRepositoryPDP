package ocp;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }


    @Override
    public void calculateArea() {
        System.out.println("Calculating circle area with radius:" + this.radius);
    }
}
