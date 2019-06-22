package com.michak.simplaex.model;

import java.math.BigDecimal;

public class ProducerEntry {

    private final String uuid;
    private final String base64Date;
    private final BigDecimal floatingPointValue;
    private final BigDecimal anInteger;
    private final BigDecimal anotherInteger;

    public ProducerEntry(String line) {

        final String[] strings = line.split(",");

        this.uuid = strings[0];
        this.base64Date = strings[1];
        this.floatingPointValue = new BigDecimal(strings[2]);
        this.anInteger = new BigDecimal(strings[3]);
        this.anotherInteger = new BigDecimal(strings[4]);
    }

    public String getUuid() {
        return uuid;
    }

    public String getBase64Date() {
        return base64Date;
    }

    public BigDecimal getFloatingPointValue() {
        return floatingPointValue;
    }

    public BigDecimal getAnInteger() {
        return anInteger;
    }

    public BigDecimal getAnotherInteger() {
        return anotherInteger;
    }
}
