/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

/**
 *
 * @author kal bugrara
 */
public class Channel {
    String channelType; //including internet, TV, newspaper, magazine, radio
    int cost; // cost for its channel 
  
    public Channel(String channelType) {
        this.channelType = channelType;
        if(channelType.equalsIgnoreCase("internet")){
            cost = 1000000; 
        }
        else if(channelType.equalsIgnoreCase("tv")){
            cost = 1000000;
        }
        else if(channelType.equalsIgnoreCase("newspaper")){
            cost = 500000;
        }
        else if(channelType.equalsIgnoreCase("magazine")){
            cost = 300000;
        }
        else if(channelType.equalsIgnoreCase("radio")){
            cost = 600000;
        }
        else{
            cost = 0; //wrong channel that beyond our expectation
            System.out.println("errors in a channel");
        }   
        
    }

    public boolean isMatch(String type){
        if(channelType.equalsIgnoreCase(type)){
            return true;
        } 
        else{
            return false;
        }
    }

    public String getChannelType(){
        return channelType;
    }
      
    public int getCost() {
        return cost;
    }


}
