package creational.factory;

public abstract class Computer {
    public abstract String getRAM();

    public abstract String getHDD();

    public abstract String getCPU();

    @Override
    public String toString() {
        return String.format("RAM=%s , HDD=%s, CPU=%s", this.getRAM(), this.getHDD(), this.getCPU());
    }
}
