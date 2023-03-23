/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */

public class SolutionOffer {

    ProductBundle selectedBundle;
    int price; // floor, ceiling, and target ideas //total price for this comb
    MarketChannelAssignment marketchannelcomb;
    //String ads; //some words to tell customers

    public SolutionOffer(MarketChannelAssignment m, ProductBundle selectedBundle) {
        marketchannelcomb = m;
        this.selectedBundle = selectedBundle;
        if((marketchannelcomb.getMarket().isMatch("east") || marketchannelcomb.getMarket().isMatch("west")) && (marketchannelcomb.getChannel().isMatch("tv") || marketchannelcomb.getChannel().isMatch("internet"))){
            price = selectedBundle.getPriceOfEWIT();
        }
        else if((marketchannelcomb.getMarket().isMatch("north") || marketchannelcomb.getMarket().isMatch("south")) && (marketchannelcomb.getChannel().isMatch("tv") || marketchannelcomb.getChannel().isMatch("internet"))){
            price = selectedBundle.getPriceOfNSIT();
        }
        else if((marketchannelcomb.getMarket().isMatch("east") || marketchannelcomb.getMarket().isMatch("west")) && (marketchannelcomb.getChannel().isMatch("magazine") || marketchannelcomb.getChannel().isMatch("newspaper") || marketchannelcomb.getChannel().isMatch("radio"))){
            price = selectedBundle.getPriceOfEWMNR();
        }
        else{
            price = selectedBundle.getPriceOfNSMNR();
        }
    }

    // public void addProductBundle(ProductBundle pb) {
    //     bundles.add(pb);
    // }

    // public int setOfferPrice() {
    //     return price;
    // }

    public int getOfferPrice() {
        return price;
    }

    //
    public boolean isOfferTargetMarketChannel(MarketChannelAssignment mca) {
        if (marketchannelcomb == mca){
            return true;
        }
        else{
            return false;
        }    
    }

    //
    public void printSolutionOffer(){
        System.out.println("Targeting Market:" + marketchannelcomb.getMarket().getName() + " - Channel:" +marketchannelcomb.getChannel().getChannelType() );
        System.out.println("Name:" + selectedBundle.getBundleName() + "  Price:$" + getOfferPrice());
        System.out.println();
    }

    public ProductBundle getSelectedBundle() {
        return selectedBundle;
    }

    public MarketChannelAssignment getMarketchannelcomb() {
        return marketchannelcomb;
    }

    
   
}
