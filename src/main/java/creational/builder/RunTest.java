package creational.builder;
//https://www.digitalocean.com/community/tutorials/builder-design-pattern-in-java
public class RunTest {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder("16", "1Tb", "Inter i9").setIsGraphicCardEnabled(true).build();
        System.out.println(computer);
    }
}
