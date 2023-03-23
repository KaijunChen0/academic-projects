/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.ProductManagement.SolutionOffer;

/**
 *
 * @author kal bugrara
 */
public class Market {
    String name; //since market is defined as customers from a indentical area, different markets are made of groups of customers from different areas.
    ArrayList<Channel> validchannels;//
    ArrayList<CustomerProfile> cprofile;
    int size; //no need

    public Market(String name) {
        this.name = name;
        validchannels = new ArrayList<Channel>(); 
        cprofile = new ArrayList<CustomerProfile>();
    }

    public void addValidChannel(Channel c){
        validchannels.add(c);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Channel> getValidchannels() {
        return validchannels;
    }

    public int getSize() {
        return size;
    }

    //
    public boolean isMatch(String name){
        if(name.equalsIgnoreCase(name)){
            return true;
        }
        else{
            return false;
        }
    }




    
}
