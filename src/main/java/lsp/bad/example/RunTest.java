package lsp.bad.example;

public class RunTest {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Orange orange = new Apple();
        Orange realOrange = new Orange();

        System.out.println("========Apple color========");
        apple.getColor();
        //principle break here
        System.out.println("========Orange color========");
        orange.getColor();
        System.out.println("========Real orange color========");
        realOrange.getColor();

    }

}
