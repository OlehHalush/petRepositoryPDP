package lsp.good.example;

public class RunTest {
    public static void main(String[] args) {
        iFruit apple = new Apple();
        iFruit orange = new Orange();

        ColorPrinter colorPrinter = new ColorPrinter();
        colorPrinter.printColor(apple);
        colorPrinter.printColor(orange);
    }

}
