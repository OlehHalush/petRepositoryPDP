package structural.composite;

//https://www.digitalocean.com/community/tutorials/composite-design-pattern-in-java
public class RunTest {
    public static void main(String[] args) {
        Shape square1 = new Square();
        Shape square2 = new Square();
        Shape square3 = new Square();
        Shape triangle = new Triangle();

        Drawing drawing = new Drawing();
        drawing.add(square1);
        drawing.add(square2);
        drawing.add(square3);
        drawing.add(triangle);

        drawing.fillShapeColor("red");

        drawing.remove(square3);

        drawing.fillShapeColor("green");

        drawing.clear();

        drawing.add(square1);
        drawing.add(triangle);
        drawing.fillShapeColor("yellow");
    }
}
