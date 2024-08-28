package com.globant.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CyptoCurrencyModel {
    private String symbol;
    private BigDecimal marketPrice;
    private static final Map<String, CryptoCurrency> crypto = new HashMap<>();

    public CryptoCurrency(String symbol, BigDecimal marketPrice){
        this.symbol =symbol;
        this.marketPrice = marketPrice;
    }

    public String getSymbol() {return symbol;}
    public BigDecimal getMarketPrice() {return marketPrice;}
    /*Gives each crypto the average price, so that they can't be sold or purchased for less
    than that amount*/
    public void setMarketPrice(BigDecimal marketPrice){ this.marketPrice = marketPrice;}

    //Stating the only cryptos allowed on the system
    public void initialCryptoPrice(){
        //source of average prices on 28/08/2024 : https://coinranking.com/
        if (crypto.isEmpty()){
            crypto.put("Bitcoin" , new CryptoCurrency("BTC", new BigDecimal("59,484.02")));
            crypto.put("Ethereum" , new CryptoCurrency("ETH", new BigDecimal(" 2,477.88")));
            crypto.put("Ultima Ecosystem" , new CryptoCurrency("ULTIMA", new BigDecimal("6,705.91")));

        }
    }
    //Find cryptos by name
    public CryptoCurrency getBySymbol(String symbol){
        return CryptoCurrency.crypto.get(symbol);
    }
    public  static Map <String, CryptoCurrency> showAll (){
        return new HashMap<>(crypto);
    }
}
