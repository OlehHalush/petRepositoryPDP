package dip;

public class RunTest {
    public static void main(String[] args) {
        Bulb bulb = new Bulb();
        Fan fan = new Fan();
        new Switcher(bulb).turnOn();
        new Switcher(bulb).turnOff();
        new Switcher(fan).turnOn();
        new Switcher(fan).turnOff();
    }
}
