package ocp;

public class Square implements Shape{
    private double side;
    public Square(double side){
        this.side = side;
    }


    @Override
    public void calculateArea() {
        System.out.println("Calculating square area with side: " + this.side);
    }
}
