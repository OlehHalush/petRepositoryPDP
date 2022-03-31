package com.customertimes.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private static Map<String, Map<RunLanguage, String>> translations;

    public static String translate(String phrase) {
        return translateFromEnglishToLanguage(phrase, Context.RUN_LANGUAGE);
    }

    public static String translate(Boolean yesNo) {
        if (yesNo == null)
            return translateFromEnglishToLanguage("No", Context.RUN_LANGUAGE);
        return translateFromEnglishToLanguage((yesNo ? "Yes" : "No"), Context.RUN_LANGUAGE);
    }

    private static String translateFromEnglishToLanguage(String phraseTitle, RunLanguage language) {
        if (translations == null)
            translations = readTranslationsFromCSV();
        if (translations.get(phraseTitle) == null)
            throw new IllegalStateException("No phrase found: " + phraseTitle);
        if (translations.get(phraseTitle).containsKey(language) &&
                !translations.get(phraseTitle).get(language).isEmpty())
            return translations.get(phraseTitle).get(language);
        return phraseTitle;
    }

    private static Map<String, Map<RunLanguage, String>> readTranslationsFromCSV() {
        int enColumn = 0;
        int frColumn = 1;
        String csvFilePath = "localization/dictionary.csv";
        Map<String, Map<RunLanguage, String>> resultMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine(); // skip header line
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) // skip comment
                    continue;
                String[] values = line.split(";");
                Map<RunLanguage, String> translations = new HashMap<>();
                translations.put(RunLanguage.FRENCH, getArrayValueOrEmptyString(values, frColumn));
                resultMap.put(values[enColumn], translations);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Dictionary csv does not exists: " + csvFilePath);
        }
        return resultMap;
    }

    private static String getArrayValueOrEmptyString(String[] array, int index) {
        try {
            return array[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }
}
