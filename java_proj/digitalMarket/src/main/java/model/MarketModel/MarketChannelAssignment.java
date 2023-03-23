/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

import model.OrderManagement.SolutionOrder;

/**
 *
 * @author kal bugrara
 */
public class MarketChannelAssignment {
    
    Market market;
    Channel channel;
    int adbudget;
    int targetrevenue;
    ArrayList<SolutionOrder> solutionOrders;
    
    public MarketChannelAssignment(Market m, Channel c){
        market = m;
        channel = c;
        if((market.isMatch("east") || market.isMatch("west")) && (channel.isMatch("tv") || channel.isMatch("internet"))){
            adbudget = 1000000;
        }
        else if((market.isMatch("north") || market.isMatch("south")) && (channel.isMatch("tv") || channel.isMatch("internet"))){
            adbudget = 800000;
        }
        else if((market.isMatch("east") || market.isMatch("west")) && (channel.isMatch("magazine") || channel.isMatch("newspaper") || channel.isMatch("radio"))){
            adbudget = 900000;
        }
        else{
            adbudget = 600000;
        }
        targetrevenue = adbudget * 3;
        solutionOrders = new ArrayList<SolutionOrder>();
    }

    public boolean isMatch(Market m, Channel c){
        if(market == m && channel == c){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Market getMarket() {
        return market;
    }

    public Channel getChannel() {
        return channel;
    }

    public int getAdbudget() {
        return adbudget;
    }

    public int getTargetrevenue() {
        return targetrevenue;
    }

    public ArrayList<SolutionOrder> getSolutionOrders() {
        return solutionOrders;
    }

    
}
