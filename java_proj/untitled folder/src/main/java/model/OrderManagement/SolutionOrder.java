package model.OrderManagement;

import java.util.Random;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.MarketChannelAssignment;
import model.ProductManagement.ProductBundle;
import model.ProductManagement.SolutionOffer;

public class SolutionOrder {
    int orderNumber;
    SolutionOffer selectedSolutionOffer;
    CustomerProfile customerpro; //
    Random random = new Random();
    //int quantity; no need 
    MarketChannelAssignment mca;
    
    public SolutionOrder(CustomerProfile cp, SolutionOffer selectedSolutionOffer) {
        customerpro = cp;//
        orderNumber = pickRandomNumber();
        this.selectedSolutionOffer = selectedSolutionOffer;
        mca = selectedSolutionOffer.getMarketchannelcomb();
    }

    //since the default setting is that only one offer will be provided for one order, we can ingnore to calculate the quantity of offers. 
    public int getSolutionRevenue(){
        return selectedSolutionOffer.getOfferPrice();
    }
    
    public int pickRandomNumber(){
        int min = 100000;
        int max = 999999;
        int randomNumber = min + random.nextInt(max-min);
        return randomNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public SolutionOffer getSelectedSolutionOffer() {
        return selectedSolutionOffer;
    }

    public void printSolutionOrderInfo(){
        System.out.println("Order Number:" + getOrderNumber() + "  Customer:" + customerpro.getPerson().getName() + " (Market:" + getMca().getMarket().getName() + ", Channel:" + getMca().getChannel().getChannelType() + ")");
        System.out.println("Details:" + selectedSolutionOffer.getSelectedBundle().getBundleName() + "  *Offer Price:$" + selectedSolutionOffer.getOfferPrice()+ "  *Sales:$"+ getSolutionRevenue());
        System.out.println();
    }

    public CustomerProfile getCustomerpro() {
        return customerpro;
    }

    public MarketChannelAssignment getMca() {
        return mca;
    }

    public boolean isMatchProductBundle(String bundleID){
        if(selectedSolutionOffer.getSelectedBundle().getBundleID().equalsIgnoreCase(bundleID)){
            return true;
        }
        else{
            return false;
        }
    }

}
