package lsp.good.example;

public class Apple implements iFruit {
    @Override
    public void getColor() {
        System.out.println("Apple color is red");
    }
}
