package creational.abstract_factory;
//https://www.digitalocean.com/community/tutorials/abstract-factory-design-pattern-in-java
public class RunTest {
    public static void main(String[] args) {
        Computer pc = ComputerFactory.getComputer(new PCFactory("16", "1Tb", "Intel i9"));
        Computer laptop = ComputerFactory.getComputer(new LaptopFactory("16", "1Tb", "Intel i9"));
        System.out.println(pc);
        System.out.println(laptop);
    }
}
