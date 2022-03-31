package helpers.config2test;

public enum RunLanguage {

    ENGLISH,
    FRENCH;

    public static RunLanguage fromString(String name) {
        for (RunLanguage language : RunLanguage.values()) {
            if (language.name().equalsIgnoreCase(name)) {
                return language;
            }
        }
        throw new IllegalArgumentException("No constant with text " + name + " found");
    }



}
