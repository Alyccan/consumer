package com.michak.simplaex.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Result {

    private Set<String> uuidSet = new HashSet<>();
    private BigDecimal sumOfDataPoint5 = new BigDecimal(0);
    private Map<String, ValuePerUser> valuePerUserMap = new HashMap<>();

    public void addToUuidSet(String value) {
        uuidSet.add(value);
    }

    public Set<String> getUuidSet() {
        return uuidSet;
    }

    public BigDecimal getSumOfDataPoint5() {
        return sumOfDataPoint5;
    }

    public void setSumOfDataPoint5(final BigDecimal sumOfDataPoint5) {
        this.sumOfDataPoint5 = sumOfDataPoint5;
    }

    public Map<String, ValuePerUser> getValuePerUserMap() {
        return valuePerUserMap;
    }

    public void setValuePerUserMap(final Map<String, ValuePerUser> valuePerUserMap) {
        this.valuePerUserMap = valuePerUserMap;
    }
}
