package dip;

public class Switcher {
    private Switchable switchableDevice;

    public Switcher(Switchable switchable){
        this.switchableDevice = switchable;
    }

    public void turnOn(){
        switchableDevice.turnOn();
    }

    public void turnOff(){
        switchableDevice.turnOff();
    }
}
