/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.Random;

import com.github.javafaker.Faker;

import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.MarketModel.ChannelCatalog;
import model.MarketModel.Market;
import model.MarketModel.MarketCatalog;
import model.MarketModel.MarketChannelAssignment;
import model.MarketModel.MarketChannelComboCatalog;
import model.OrderManagement.SolutionOrder;
import model.OrderManagement.SolutionOrderList;
import model.Personnel.Person;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductBundle;
import model.ProductManagement.ProductBundleCatalog;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.Service;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;

/**
 *
 * @author kal bugrara
 */
public class ConfigureABusiness {
    Faker fakeTool = new Faker();
    Random random = new Random();
    
    public Business initialize() {
        Business business = new Business("Blue Nile");

        //add suppliers to supplier directory
        SupplierDirectory supplierdirectory = business.getSupplierDirectory();

        Supplier supplierBrill = supplierdirectory.newSupplier("S01", "Brilliance");
        
        //generate product lists and populate data  
        ProductCatalog productlistOfSupplierBrill = supplierBrill.getProductCatalog();
        
        Product p1SupplierBrill = productlistOfSupplierBrill.newProduct("S01P01", "stud earrings", 3000, 7000, 5000);
        Product p2SupplierBrill = productlistOfSupplierBrill.newProduct("S01P02", "hoop earrings", 3500, 8000, 6000);
        Product p3SupplierBrill = productlistOfSupplierBrill.newProduct("S01P03", "drop earrings", 4000, 9000, 7000);
        Product p4SupplierBrill = productlistOfSupplierBrill.newProduct("S01P04", "round ring", 10000, 18000, 14000);
        Product p5SupplierBrill = productlistOfSupplierBrill.newProduct("S01P05", "oval ring", 15000, 23000, 19000);
        Product p6SupplierBrill = productlistOfSupplierBrill.newProduct("S01P06", "princess ring", 20000, 40000, 35000);
        Product p7SupplierBrill = productlistOfSupplierBrill.newProduct("S01P07", "gold bracelet", 7000, 13000, 9000);
        Product p8SupplierBrill = productlistOfSupplierBrill.newProduct("S01P08", "silver bracelet", 3000, 5000, 4000);
        Product p9SupplierBrill = productlistOfSupplierBrill.newProduct("S01P09", "pearl bracelet", 12000, 18000, 16000);

        //generate a Service to make a bundle with product
        Service service = new Service(business);

        //generate a product bundle catalog and add bundles in it
        ProductBundleCatalog bundlecatalog = business.getProductBundleCatalog();

        ProductBundle bundle1 = bundlecatalog.newProductBundle(p1SupplierBrill, service);
        ProductBundle bundle2 = bundlecatalog.newProductBundle(p2SupplierBrill, service);
        ProductBundle bundle3 = bundlecatalog.newProductBundle(p3SupplierBrill, service);
        ProductBundle bundle4 = bundlecatalog.newProductBundle(p4SupplierBrill, service);
        ProductBundle bundle5 = bundlecatalog.newProductBundle(p5SupplierBrill, service);
        ProductBundle bundle6 = bundlecatalog.newProductBundle(p6SupplierBrill, service);
        ProductBundle bundle7 = bundlecatalog.newProductBundle(p7SupplierBrill, service);
        ProductBundle bundle8 = bundlecatalog.newProductBundle(p8SupplierBrill, service);
        ProductBundle bundle9 = bundlecatalog.newProductBundle(p9SupplierBrill, service);

        //get person directory and add person in it
        PersonDirectory personList = business.getPersonDirectory();

        Person person1 = personList.newPerson("0001", "Mike", "east");
        Person person2 = personList.newPerson("0002", "John", "west");
        Person person3 = personList.newPerson("0003", "Austin", "north");
        Person person4 = personList.newPerson("0004", "Chris", "south");
         
        //generate customer directory and add customers in it
        CustomerDirectory customerList = business.getCustomerDirectory();
 
        CustomerProfile customerprof1 = customerList.newCustomerProfile(person1);
        CustomerProfile customerprof2 = customerList.newCustomerProfile(person2);
        CustomerProfile customerprof3 = customerList.newCustomerProfile(person3);
        CustomerProfile customerprof4 = customerList.newCustomerProfile(person4);

 
        //create a market catalog and add four markets in it
        MarketCatalog marketcatalog = business.getMarketcatalog();

        Market marketEast = marketcatalog.newMarket("East");
        Market marketWest = marketcatalog.newMarket("West");
        Market marketNorth = marketcatalog.newMarket("North");
        Market marketSouth = marketcatalog.newMarket("South");

        //generate a channel catalog and add five channels in it
        ChannelCatalog channelcatalog = business.getChannelcatalog();

        Channel channelInternet = channelcatalog.newChannel("Internet");
        Channel channelTV = channelcatalog.newChannel("TV");
        Channel channelMagazine = channelcatalog.newChannel("Magazine");
        Channel channelNewspaper = channelcatalog.newChannel("Newspaper");
        Channel channelRadio = channelcatalog.newChannel("Radio");

        //pass channel to specific markets
        marketEast.addValidChannel(channelInternet);
        marketWest.addValidChannel(channelInternet);
        marketNorth.addValidChannel(channelInternet);
        marketSouth.addValidChannel(channelInternet);

        marketEast.addValidChannel(channelTV);
        marketWest.addValidChannel(channelTV);
        marketNorth.addValidChannel(channelTV);
        marketSouth.addValidChannel(channelTV);

        marketEast.addValidChannel(channelNewspaper);
        marketWest.addValidChannel(channelNewspaper);
        marketNorth.addValidChannel(channelNewspaper);
        marketSouth.addValidChannel(channelNewspaper);

        marketEast.addValidChannel(channelRadio);
        marketWest.addValidChannel(channelRadio);
        marketNorth.addValidChannel(channelRadio);
        marketSouth.addValidChannel(channelRadio);

        marketEast.addValidChannel(channelMagazine);
        marketWest.addValidChannel(channelMagazine);
        marketNorth.addValidChannel(channelMagazine);
        marketSouth.addValidChannel(channelMagazine);

        //generate a marketchannelcombo catalog and add assignments in it
        MarketChannelComboCatalog mcComboCatalog = business.getMarketChannelComboCatalog();

        MarketChannelAssignment mcaOfEastAndInternet = mcComboCatalog.newMarketChannelAssignment(marketEast, channelInternet);
        MarketChannelAssignment mcaOfWestAndInternet = mcComboCatalog.newMarketChannelAssignment(marketWest, channelInternet);
        MarketChannelAssignment mcaOfSouthAndInternet = mcComboCatalog.newMarketChannelAssignment(marketSouth, channelInternet);
        MarketChannelAssignment mcaOfNorthAndInternet = mcComboCatalog.newMarketChannelAssignment(marketNorth, channelInternet);
        
        MarketChannelAssignment mcaOfEastAndTV = mcComboCatalog.newMarketChannelAssignment(marketEast, channelTV);
        MarketChannelAssignment mcaOfWestAndTV = mcComboCatalog.newMarketChannelAssignment(marketWest, channelTV);
        MarketChannelAssignment mcaOfSouthAndTV = mcComboCatalog.newMarketChannelAssignment(marketSouth, channelTV);
        MarketChannelAssignment mcaOfNorthAndTV = mcComboCatalog.newMarketChannelAssignment(marketNorth, channelTV);
        
        MarketChannelAssignment mcaOfEastAndNewspaper = mcComboCatalog.newMarketChannelAssignment(marketEast, channelNewspaper);
        MarketChannelAssignment mcaOfWestAndNewspaper = mcComboCatalog.newMarketChannelAssignment(marketWest, channelNewspaper);
        MarketChannelAssignment mcaOfSouthAndNewspaper = mcComboCatalog.newMarketChannelAssignment(marketSouth, channelNewspaper);
        MarketChannelAssignment mcaOfNorthAndNewspaper = mcComboCatalog.newMarketChannelAssignment(marketNorth, channelNewspaper);
        
        MarketChannelAssignment mcaOfEastAndMagazine = mcComboCatalog.newMarketChannelAssignment(marketEast, channelMagazine);
        MarketChannelAssignment mcaOfWestAndMagazine = mcComboCatalog.newMarketChannelAssignment(marketWest, channelMagazine);
        MarketChannelAssignment mcaOfSouthAndMagazine= mcComboCatalog.newMarketChannelAssignment(marketSouth, channelMagazine);
        MarketChannelAssignment mcaOfNorthAndMagazine = mcComboCatalog.newMarketChannelAssignment(marketNorth, channelMagazine);
        
        MarketChannelAssignment mcaOfEastAndRadio = mcComboCatalog.newMarketChannelAssignment(marketEast, channelRadio);
        MarketChannelAssignment mcaOfWestAndRadio = mcComboCatalog.newMarketChannelAssignment(marketWest, channelRadio);
        MarketChannelAssignment mcaOfSouthAndRadio = mcComboCatalog.newMarketChannelAssignment(marketSouth, channelRadio);
        MarketChannelAssignment mcaOfNorthAndRadio = mcComboCatalog.newMarketChannelAssignment(marketNorth, channelRadio);
        
        //generate a solution catalog and add solutions in it
        SolutionOfferCatalog offerCatalog = business.getSolutionoffercatalog();
        //one solution offer targeting one marketchannelassignmnet!
        //solutionoffer1: East and Internet
        SolutionOffer offerEastInternet = offerCatalog.newSolutionOffer(mcaOfEastAndInternet, bundle1);
       
        //solutionoffer2: East and TV
        SolutionOffer offerEastTV = offerCatalog.newSolutionOffer(mcaOfEastAndTV, bundle1);

        //solutionoffer3: East and magazine
        SolutionOffer offerEastMagazine = offerCatalog.newSolutionOffer(mcaOfEastAndMagazine, bundle1);
        
        //solutionoffer4: East and newspaper
        SolutionOffer offerEastNewspaper = offerCatalog.newSolutionOffer(mcaOfEastAndNewspaper, bundle1);

        //solutionoffer5: East and radio
        SolutionOffer offerEastRadio = offerCatalog.newSolutionOffer(mcaOfEastAndRadio, bundle1);
      
        //solutionoffer6: west and internet
        SolutionOffer offerWestInternet = offerCatalog.newSolutionOffer(mcaOfWestAndInternet, bundle6);
       
        //solutionoffer7: west and tv
        SolutionOffer offerWestTV = offerCatalog.newSolutionOffer(mcaOfWestAndTV, bundle7);

        //solutionoffer8: west and magazine
        SolutionOffer offerWestMagazine = offerCatalog.newSolutionOffer(mcaOfWestAndMagazine, bundle8);

        //solutionoffer9: west and newspaper
        SolutionOffer offerWestNewspaper = offerCatalog.newSolutionOffer(mcaOfWestAndNewspaper, bundle9);

        //solutionoffer10: west and radio
        SolutionOffer offerWestRadio = offerCatalog.newSolutionOffer(mcaOfWestAndRadio, bundle1);
        
        //solutionoffer11: north and internet
        SolutionOffer offerNorthInternet = offerCatalog.newSolutionOffer(mcaOfNorthAndInternet,bundle1);
        
        //solutionoffer12: north and tv
        SolutionOffer offerNorthTV = offerCatalog.newSolutionOffer(mcaOfNorthAndTV,bundle2);
        
        //solutionoffer13: north and magazine
        SolutionOffer offerNorthMagazine = offerCatalog.newSolutionOffer(mcaOfNorthAndMagazine,bundle3);
        
        //solutionoffer14: north and newspaper
        SolutionOffer offerNorthNewspaper = offerCatalog.newSolutionOffer(mcaOfNorthAndNewspaper,bundle4);
        
        //solutionoffer15: north and radio
        SolutionOffer offerNorthRadio = offerCatalog.newSolutionOffer(mcaOfNorthAndRadio,bundle5);
       
        //solutionoffer16: south and internet
        SolutionOffer offerSouthInternet = offerCatalog.newSolutionOffer(mcaOfSouthAndInternet,bundle6);
        
        //solutionoffer17: south and tv
        SolutionOffer offerSouthTV = offerCatalog.newSolutionOffer(mcaOfSouthAndTV,bundle7);
       
        //solutionoffer18: south and magazine
        SolutionOffer offerSouthMagazine = offerCatalog.newSolutionOffer(mcaOfSouthAndMagazine,bundle8);
        
        //solutionoffer19: south and newspaper
        SolutionOffer offerSouthNewspaper = offerCatalog.newSolutionOffer(mcaOfSouthAndNewspaper,bundle9);
        
        //solutionoffer20: south and radio
        SolutionOffer offerSouthRadio = offerCatalog.newSolutionOffer(mcaOfSouthAndRadio,bundle1);
        
        //generate a solution order list and populate it(20 orders for test)
        SolutionOrderList solutionorderlist = business.getSolutionorderlist();
        //solution order from eastern market
        SolutionOrder sorderEastInternet = solutionorderlist.newSolutionOrder(customerprof1, offerEastInternet);
        SolutionOrder sorderEastTV = solutionorderlist.newSolutionOrder(customerprof1,offerEastTV);
        SolutionOrder sorderEastMagazine = solutionorderlist.newSolutionOrder(customerprof1,offerEastMagazine);
        SolutionOrder sorderEastNewspaper = solutionorderlist.newSolutionOrder(customerprof1,offerEastNewspaper);
        SolutionOrder sorderEastRadio = solutionorderlist.newSolutionOrder(customerprof1,offerEastRadio);
        //solution order from western market
        SolutionOrder sorderWestInternet = solutionorderlist.newSolutionOrder(customerprof2,offerWestInternet);
        SolutionOrder sorderWestTV = solutionorderlist.newSolutionOrder(customerprof2,offerWestTV);
        SolutionOrder sorderWestMagazine = solutionorderlist.newSolutionOrder(customerprof2,offerWestMagazine);
        SolutionOrder sorderWestNewspaper = solutionorderlist.newSolutionOrder(customerprof2,offerWestNewspaper);
        SolutionOrder sorderWestRadio = solutionorderlist.newSolutionOrder(customerprof2,offerWestRadio);
        //solution order from northern market
        SolutionOrder sorderNorthInternet = solutionorderlist.newSolutionOrder(customerprof3,offerNorthInternet);
        SolutionOrder sorderNorthTV = solutionorderlist.newSolutionOrder(customerprof3,offerNorthTV);
        SolutionOrder sorderNorthMagazine = solutionorderlist.newSolutionOrder(customerprof3,offerNorthMagazine);
        SolutionOrder sorderNorthNewspaper = solutionorderlist.newSolutionOrder(customerprof3,offerNorthNewspaper);
        SolutionOrder sorderNorthRadio = solutionorderlist.newSolutionOrder(customerprof3,offerNorthRadio);
        //solution order from southern market
        SolutionOrder sorderSouthInternet = solutionorderlist.newSolutionOrder(customerprof4,offerSouthInternet);
        SolutionOrder sorderSouthTV = solutionorderlist.newSolutionOrder(customerprof4,offerSouthTV);
        SolutionOrder sorderSouthMagazine = solutionorderlist.newSolutionOrder(customerprof4,offerSouthMagazine);
        SolutionOrder sorderSouthNewspaper = solutionorderlist.newSolutionOrder(customerprof4,offerSouthNewspaper);
        SolutionOrder sorderSouthRadio = solutionorderlist.newSolutionOrder(customerprof4,offerSouthRadio);

        return business;
    }

    public void autoGenerateSolutionOrders(Business business, int customerCount, String areaName){
        PersonDirectory personList = business.getPersonDirectory();
        CustomerDirectory customerList = business.getCustomerDirectory();
        for(int i = 0; i < customerCount; i++){
            Person p = personList.newPerson(fakeTool.number().digit()+fakeTool.number().digit()+fakeTool.number().digit()+fakeTool.number().digit(), fakeTool.name().firstName(), areaName);
            CustomerProfile cp = customerList.newCustomerProfile(p);
            MarketChannelComboCatalog mccc = business.getMarketChannelComboCatalog();
            MarketChannelAssignment mca = mccc.randomPickAMarketChannelAssignment(); //20 mca already in the list, just pick one of them
            ProductBundleCatalog pbc = business.getProductBundleCatalog();
            ProductBundle pb = pbc.pickArandomBundle(); //9 bundles already in the list, just pick one of them
            SolutionOfferCatalog soc = business.getSolutionoffercatalog();
            SolutionOffer soff = soc.newSolutionOffer(mca, pb);
            SolutionOrderList sol = business.getSolutionorderlist();
            SolutionOrder sorder = sol.newSolutionOrder(cp, soff);
        }
    }

    

    
}
