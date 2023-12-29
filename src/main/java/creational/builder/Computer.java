package creational.builder;

public class Computer {
    private String ram;
    private String hdd;
    private String cpu;
    private boolean isGraphicCardEnabled;

    public String getRAM() {
        return this.ram;
    }

    public String getHDD() {
        return this.hdd;
    }

    public String getCPU() {
        return this.cpu;
    }

    public boolean isGraphicCardEnabled() {
        return this.isGraphicCardEnabled;
    }

    ;

    public String toString() {
        return String.format("RAM=%s , HDD=%s, CPU=%s, IsGraphicCardEnabled=%s", this.getRAM(), this.getHDD(), this.getCPU(), this.isGraphicCardEnabled);
    }

    private Computer(ComputerBuilder computerBuilder) {
        this.ram = computerBuilder.ram;
        this.hdd = computerBuilder.hdd;
        this.cpu = computerBuilder.cpu;
        this.isGraphicCardEnabled = computerBuilder.isGraphicCardEnabled;
    }

    public static class ComputerBuilder {
        private String ram;
        private String hdd;
        private String cpu;
        private boolean isGraphicCardEnabled;

        public ComputerBuilder(String ram, String hdd, String cpu) {
            this.ram = ram;
            this.hdd = hdd;
            this.cpu = cpu;
        }

        public ComputerBuilder setIsGraphicCardEnabled(boolean isGraphicCard) {
            this.isGraphicCardEnabled = isGraphicCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

}
