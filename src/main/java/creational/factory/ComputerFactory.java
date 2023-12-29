package creational.factory;

public class ComputerFactory {
    public static Computer getComputer(String type, String ram, String hdd, String cpu) {
        if (type.equalsIgnoreCase("PC"))
            return new PC(ram, hdd, cpu);
        else if (type.equalsIgnoreCase("Laptop"))
            return new Laptop(ram, hdd, cpu);
        return null;
    }
}
