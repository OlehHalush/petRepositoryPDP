package com.customertimes.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.customertimes.config.Context.RUN_MARKET;

public class Messages {

    private static Map<String, Map<RunMarket, String>> messages;

    public static String getMessage(String title) {
        return getMessageForMarket(title, Context.RUN_MARKET);
    }

    private static String getMessageForMarket(String messageTitle, RunMarket market) {
        if (messages == null)
            messages = readMessageFromCSV();
        if (messages.get(messageTitle) == null)
            throw new IllegalStateException("No message found: " + messageTitle);
        if (messages.get(messageTitle).containsKey(market) &&
                !messages.get(messageTitle).get(market).isEmpty())
            return messages.get(messageTitle).get(market);
        return messageTitle;
    }

    private static Map<String, Map<RunMarket, String>> readMessageFromCSV() {
        int titleColumn = 0;
        int frColumn = 1;
        int ptColumn = 2;
        int atColumn = 3;
        int chColumn = 4;
        String csvFilePath = "localization/messages.csv";
        Map<String, Map<RunMarket, String>> resultMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine(); // skip header line
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) // skip comment
                    continue;
                String[] values = line.split(";");
                Map<RunMarket, String> messagesMap = new HashMap<>();
                messagesMap.put(RunMarket.FR, getArrayValueOrEmptyString(values, frColumn));
                messagesMap.put(RunMarket.PT, getArrayValueOrEmptyString(values, ptColumn));
                messagesMap.put(RunMarket.AT, getArrayValueOrEmptyString(values, atColumn));
                messagesMap.put(RunMarket.CH, getArrayValueOrEmptyString(values, chColumn));
                resultMap.put(values[titleColumn], messagesMap);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Messages csv does not exists: " + csvFilePath);
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
