package isp;

public class Bird implements Flyable, Walkable, Eatable{
    @Override
    public void fly() {
        System.out.println("The Bird can fly");
    }

    @Override
    public void walk() {
        System.out.println("The Bird can walk");
    }

    @Override
    public void eat(){
        System.out.println("The Bird can eat");
    }
}
