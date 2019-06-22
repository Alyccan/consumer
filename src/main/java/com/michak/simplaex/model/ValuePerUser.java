package com.michak.simplaex.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ValuePerUser {

    private String uuid;
    private BigDecimal sum;
    private BigDecimal count;
    private BigDecimal recentValue4;

    public ValuePerUser(final String uuid, final BigDecimal sum, final BigDecimal recentValue4) {
        this.uuid = uuid;
        this.sum = sum;
        this.count = new BigDecimal(1);
        this.recentValue4 = recentValue4;
    }

    public BigDecimal getRecentValue4() {
        return recentValue4;
    }

    public void setRecentValue4(final BigDecimal recentValue4) {
        this.recentValue4 = recentValue4;
    }

    public BigDecimal getAverageValue() {
        return sum.divide(count, 16, RoundingMode.HALF_UP);
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(final BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(final BigDecimal sum) {
        this.sum = sum;
    }
}
