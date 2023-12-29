package isp;

public class Dog implements Walkable, Eatable {
    @Override
    public void walk() {
        System.out.println("The Dog can walk");
    }

    @Override
    public void eat() {
        System.out.println("The Dog can eat");
    }
}
