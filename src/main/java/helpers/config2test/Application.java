package helpers.config2test;

public enum Application {
    AMALIA("Amalia", "com.ct.bacardi") {
        @Override
        public String getBundleId() {
            return String.valueOf(Driver.getDriver().getSessionDetail("CFBundleIdentifier"));
        }
    },
    SAFARI("Safari", "com.apple.mobilesafari");

    private String bundleId, name;

    Application(String name, String bundleId) {
        this.name = name;
        this.bundleId = bundleId;
    }

    public String getBundleId() {
        return bundleId;
    }

    public String getName() {
        return name;
    }

    public static Application getByName(String name) {
        for (Application application : Application.values()) {
            if (application.name.equalsIgnoreCase(name)) {
                return application;
            }
        }
        throw new IllegalArgumentException("No value for application: " + name);
    }
}