/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

//import com.github.javafaker.Business;
import model.Business.*;

/**
 *
 * @author kal bugrara
 */
public class MarketCatalog {
    Business business;
    ArrayList<Market> markets;

    public MarketCatalog(Business business){
        this.business = business;
        markets = new ArrayList<Market>();
    }

    public MarketCatalog(){

        markets = new ArrayList<Market>();
    }

    public Market newMarket(String name){
        Market market = new Market(name);
        markets.add(market);
        return market;
    }

    public Business getBusiness() {
        return business;
    }

}
