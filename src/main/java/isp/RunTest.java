package isp;

public class RunTest {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Bird bird = new Bird();

        dog.eat();
        dog.walk();
        bird.eat();
        bird.walk();
        bird.fly();
    }
}
