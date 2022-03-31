package utils;

import com.github.javafaker.Faker;

import java.util.Random;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class GenerateFakeDataUtils {
    private static final String AUTO_STRING = "Auto " + generateRandomStringNumber() + " ";
    private static final String SURVEY_STRING = "Survey ";
    private static final String BRAND_STRING = "Brand ";
    private static final String LABEL_STRING = "Label ";
    private static final String QUESTION_STRING = "Question ";
    private static final String ANSWER_STRING = "Answer ";
    private static final String MULTI_ITEM_ACTIVITY_QUESTION_STRING = "Multi-Item Activity Question ";
    private static final String COMMENT = "Comment ";
    private static final String ACCOUNT = "Account ";

    public static String generateCompanyName() {
        return AUTO_STRING + ACCOUNT + new Faker().company().name().replaceAll("'", EMPTY);
    }

    public static String generateSurveyName() {
        return AUTO_STRING + SURVEY_STRING + new Faker().name().firstName().replaceAll("'", EMPTY);
    }

    public static String generateMultiItemActivityQuestion() {
        return AUTO_STRING + MULTI_ITEM_ACTIVITY_QUESTION_STRING + new Faker().name().firstName().replaceAll("'", EMPTY);
    }

    public static String generateBrandName() {
        return AUTO_STRING + BRAND_STRING + new Faker().name().firstName().replaceAll("'", EMPTY);
    }

    public static String generateLabelName() {
        return AUTO_STRING + LABEL_STRING + new Faker().name().firstName().replaceAll("'", EMPTY);
    }

    public static String generateQuestionName() {
        return AUTO_STRING + SURVEY_STRING + QUESTION_STRING + new Faker().name().firstName().replaceAll("'", EMPTY);
    }

    public static String generateStringAnswer() {
        return AUTO_STRING + SURVEY_STRING + QUESTION_STRING + ANSWER_STRING + new Faker().name().firstName().replaceAll("'", EMPTY);
    }

    public static String generateRandomStringNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(100);

        return String.format("%01d", number);
    }

    public static String generateComment() {
        return AUTO_STRING + COMMENT + new Faker().name().firstName().replaceAll("'", EMPTY);
    }
}