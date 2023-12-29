package structural.composite;

public class Square implements Shape {
    @Override
    public void fillShapeColor(String fillColor) {
        System.out.println("Filling square with color: " + fillColor);
    }
}
