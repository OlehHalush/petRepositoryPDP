package com.customertimes.config;

public enum RunMarket {

    FR,
    PT,
    AT,
    CH;

    public static RunMarket fromString(String name) {
        for (RunMarket market : RunMarket.values()) {
            if (market.name().equalsIgnoreCase(name)) {
                return market;
            }
        }
        throw new IllegalArgumentException("No constant with text " + name + " found");
    }


}
