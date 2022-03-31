package helpers.picklist;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionType {
    YES_NO("Yes / No", "02"),
    FREE_TEXT("Free Text", "06");

    private final String value;
    private final String apiValue;

    public static QuestionType getByValue(String value) {
        for (QuestionType pulseMarket : QuestionType.values()) {
            if (pulseMarket.value.equalsIgnoreCase(value)) {
                return pulseMarket;
            }
        }
        throw new IllegalArgumentException("No Question Type for " + value);
    }
}
