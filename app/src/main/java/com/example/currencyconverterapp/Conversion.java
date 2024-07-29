package com.example.currencyconverterapp;

public class Conversion {
    private String baseCurrencyCode, targetCurrencyCode;
    private Double baseAmount, targetAmount, changeRate;
    private boolean isExpanded;


    public Conversion(String baseCurrencyCode, String targetCurrencyCode, Double baseAmount, Double targetAmount, Double changeRate) {
        this.baseCurrencyCode = baseCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.baseAmount = baseAmount;
        this.targetAmount = targetAmount;
        this.changeRate = changeRate;
        this.isExpanded= false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getBaseCurrencyCode() {
        return baseCurrencyCode;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    public Double getBaseAmount() {
        return baseAmount;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public Double getChangeRate() {
        return changeRate;
    }

    public void setBaseCurrencyCode(String baseCurrencyCode) {
        this.baseCurrencyCode = baseCurrencyCode;
    }

    public void setTargetCurrencyCode(String targetCurrencyCode) {
        this.targetCurrencyCode = targetCurrencyCode;
    }

    public void setBaseAmount(Double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public void setChangeRate(Double changeRate) {
        this.changeRate = changeRate;
    }

    @Override
    public String toString() {
        return "Conversion{" +
                "baseCurrencyCode='" + baseCurrencyCode + '\'' +
                ", targetCurrencyCode='" + targetCurrencyCode + '\'' +
                ", baseAmount=" + baseAmount +
                ", targetAmount=" + targetAmount +
                ", changeRate=" + changeRate +
                '}';
    }
}
