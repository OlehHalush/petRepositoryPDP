package creational.factory;

public class RunTest {
    public static void main(String[] args) {
        Computer pc = ComputerFactory.getComputer("PC", "16", "1Tb", "Intel i9");
        Computer laptop = ComputerFactory.getComputer("Laptop", "16", "256Gb", "Intel i5");
        System.out.println(pc);
        System.out.println(laptop);
    }
}
