package com.globant.model.CryptoCurrencies;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CryptoCurrency {
private String symbol;
private BigDecimal marketPrice;
    private static final Map<String, CryptoCurrency> cryptoPrices = new HashMap<>();

    static {
        cryptoPrices.put("BTC", new Bitcoin(new BigDecimal("59474.14")));
        cryptoPrices.put("ETH", new Ethereum(new BigDecimal("2524.92")));
        cryptoPrices.put("ULTIMA", new UltimaEcosystem(new BigDecimal("6801.43")));
    }

//Average prices 29/08/2024 source: https://coinranking.com/

     CryptoCurrency(String symbol, BigDecimal marketPrice){
        this.symbol =symbol;
        this.marketPrice = marketPrice;
    }

    public static CryptoCurrency getCryptoBySymbol(String symbol) {
        return cryptoPrices.get(symbol);
    }

    @Override
    public String toString() {
        return symbol;
    }

    public String getSymbol() {return symbol;}
    public BigDecimal getMarketPrice() {return marketPrice;}


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CryptoCurrency that = (CryptoCurrency) obj;
        return symbol.equals(that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }






}
