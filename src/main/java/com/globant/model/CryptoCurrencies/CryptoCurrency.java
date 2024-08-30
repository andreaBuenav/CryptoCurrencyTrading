package com.globant.model.CryptoCurrencies;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CryptoCurrency {
private String symbol;
private BigDecimal marketPrice;
    private static final Map<String, BigDecimal> cryptoPrices = new HashMap<>();

    static {
        cryptoPrices.put("BTC", new BigDecimal("59474.14"));  
        cryptoPrices.put("ETH", new BigDecimal("2524.92"));
        cryptoPrices.put("ULTIMA", new BigDecimal("6801.43"));
    }

//Average prices 29/08/2024 source: https://coinranking.com/
     CryptoCurrency(String symbol, BigDecimal marketPrice){
        this.symbol =symbol;
        this.marketPrice = marketPrice;
    }
    public static BigDecimal getPrice(String symbol) {
        return cryptoPrices.get(symbol);
    }


    public String getSymbol() {return symbol;}
    public BigDecimal getMarketPrice() {return marketPrice;}







}
