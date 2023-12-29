package ocp;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }


    @Override
    public void calculateArea() {
        System.out.printf("Calculating Rectangle area with width: %s and height: %s", this.width, this.height);
    }
}
