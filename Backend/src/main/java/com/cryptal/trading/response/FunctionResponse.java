package com.cryptal.trading.response;

import lombok.Data;

@Data
public class FunctionResponse {
    private String functionName;
    private String currencyName;
    private String currencyData;

    public String getCurrencyData() {
        return currencyData;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setCurrencyData(String currencyData) {
        this.currencyData = currencyData;
    }
}
