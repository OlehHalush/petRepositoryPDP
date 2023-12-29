package structural.composite;

public class Triangle implements Shape {
    @Override
    public void fillShapeColor(String fillColor) {
        System.out.println("Filling triangle with color: " + fillColor);
    }
}
