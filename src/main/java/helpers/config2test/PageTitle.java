package helpers.config2test;

public enum PageTitle {
    IONIC_APP("Ionic App"),
    LOGIN("Login"),
    ALLOW_ACCESS("Allow Access");

    private final String title;

    PageTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}