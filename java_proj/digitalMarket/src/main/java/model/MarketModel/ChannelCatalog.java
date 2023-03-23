/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.io.IOException;
//import java.nio.channels.Channel;
import java.util.ArrayList;

import model.Business.Business;

//import com.github.javafaker.Business;

/**
 *
 * @author kal bugrara
 */
public class ChannelCatalog {
    Business business;
    ArrayList<Channel> channels;

    public ChannelCatalog(Business business) {
        this.business = business;
        channels = new ArrayList<Channel>();
    }

    public ChannelCatalog() {

        channels = new ArrayList<Channel>();
    }

    public Business getBusiness() {
        return business;
    }

    public Channel newChannel(String type){
        Channel channel = new Channel(type);
        channels.add(channel);
        return channel;

    }

    public Channel findChannel(String type){
        for(Channel channel: channels){
            if(channel.getChannelType().equalsIgnoreCase(type)){
                return channel;
            }
        }
        return null;
    }

    public int calculateCostOfChannels(){
        int sum = 0;
        for(Channel c:channels){
            sum += c.getCost();
        }
        return sum;
    }

    

}



    
    
