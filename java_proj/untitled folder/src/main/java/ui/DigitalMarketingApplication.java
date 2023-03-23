/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.github.javafaker.Faker;

import model.Business.Business;
import model.Business.ConfigureABusiness;

/**
 *
 * @author kal bugrara
 */
public class DigitalMarketingApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConfigureABusiness configure = new ConfigureABusiness();

        Business business = configure.initialize(); //including 20 orders 
        //1. Customers can search for solutions that matched their expectations
        //customers can see bundles by this list
        business.getProductBundleCatalog().printBundleListInfo();
        
        //2.1 show bundles'price targeting different combination of markets and channels
        //to show my pricing strategy
        business.getProductBundleCatalog().showBundlePriceOfList();

        //2.2 show the solution offers list and price
        business.getSolutionoffercatalog().printSolutionOfferList();//improve 

        //3. autogenerate solution orders and show them including prices targeting different marketchannelcombo
        configure.autoGenerateSolutionOrders(business, 10,"east");
        configure.autoGenerateSolutionOrders(business, 100,"west");
        configure.autoGenerateSolutionOrders(business, 10,"south");
        configure.autoGenerateSolutionOrders(business, 10,"north");
        
        //4. generate reports to know sales revenues
        business.getSolutionorderlist().showSolutionOrdersAndReprot();
        business.getMarketChannelComboCatalog().sumAndprintAllBudgetOfMCA();

        
        


        

    }
}
