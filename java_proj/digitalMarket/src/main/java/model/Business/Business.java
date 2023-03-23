/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.ArrayList;

import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomersReport;
import model.MarketModel.ChannelCatalog;
import model.MarketModel.MarketCatalog;
import model.MarketModel.MarketChannelComboCatalog;
import model.MarketingManagement.MarketingPersonDirectory;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.SolutionOrderList;
import model.Personnel.EmployeeDirectory;
import model.Personnel.PersonDirectory;
import model.ProductManagement.ProductBundleCatalog;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.Service;//
import model.ProductManagement.SolutionOfferCatalog;
import model.SalesManagement.SalesPersonDirectory;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    PersonDirectory persondirectory;
    MasterOrderList masterorderlist;
    CustomerDirectory customers;
    SupplierDirectory suppliers;
    MarketCatalog marketcatalog; //
    ChannelCatalog channelcatalog; //
    SolutionOfferCatalog solutionoffercatalog; //
    CustomerDirectory customerdirectory;
    EmployeeDirectory employeedirectory;
    SalesPersonDirectory salespersondirectory;
    UserAccountDirectory useraccountdirectory;
    MarketingPersonDirectory marketingpersondirectory;
    CustomersReport customersreport;
    SolutionOrderList solutionorderlist; //
    ProductBundleCatalog bundlecatalog;//
    MarketChannelComboCatalog mcCombocatalog;//
    Service service;//

    public Business(String name) {
        this.name = name;
        masterorderlist = new MasterOrderList();
        suppliers = new SupplierDirectory();
        solutionoffercatalog = new SolutionOfferCatalog(); //
        persondirectory = new PersonDirectory();
        customerdirectory = new CustomerDirectory(this);
        salespersondirectory = new SalesPersonDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        marketingpersondirectory = new MarketingPersonDirectory(this);
        employeedirectory = new EmployeeDirectory(this);
        customersreport = new CustomersReport(this);
        marketcatalog = new MarketCatalog(this); //
        channelcatalog = new ChannelCatalog(this); //
        solutionorderlist = new SolutionOrderList(); //
        bundlecatalog = new ProductBundleCatalog();//
        mcCombocatalog = new MarketChannelComboCatalog();//
    }

    public int getSalesVolume() {
        return masterorderlist.getSalesVolume();
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }

    public MarketingPersonDirectory getMarketingPersonDirectory() {
        return marketingpersondirectory;
    }

    public SupplierDirectory getSupplierDirectory() {
        return suppliers;
    }

    public ProductsReport getSupplierPerformanceReport(String n) {
        Supplier supplier = suppliers.findSupplier(n);
        if (supplier == null) {
            return null;
        }
        return supplier.prepareProductsReport();
    }

    public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(
            String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n);
        return productsreport.getProductsAlwaysAboveTarget();
    }

    public int getHowManySupplierProductsAlwaysAboveTarget(String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n); // see above
        int i = productsreport.getProductsAlwaysAboveTarget().size(); // return size of the arraylist
        return i;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerdirectory;
    }

    public SalesPersonDirectory getSalesPersonDirectory() {
        return salespersondirectory;
    }

    public MasterOrderList getMasterOrderList() {
        return masterorderlist;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    public void printBusinessInformation() {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Company Name: " + name);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("--------------------------------- List of Suppliers -----------------------------------");
        SupplierDirectory sd = getSupplierDirectory();
        sd.printSupplierList();

    }

    public void printCustomerList() {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("--------------------------------- List of Customer Orders ------------------------------");
        customerdirectory.printAllCustomers();
    }

    public void printAllCustomersReport() {
        customersreport.printCustomersReport();
    }

    public CustomersReport getCustomersReport() {
        return customersreport;
    }

    public MarketCatalog getMarketcatalog() {
        return marketcatalog;
    }

    public ChannelCatalog getChannelcatalog() {
        return channelcatalog;
    }

    public SolutionOfferCatalog getSolutionoffercatalog() {
        return solutionoffercatalog;
    }

    public SolutionOrderList getSolutionorderlist() {
        return solutionorderlist;
    }

    public ProductBundleCatalog getProductBundleCatalog(){  //
        return bundlecatalog;
    }

    public MarketChannelComboCatalog getMarketChannelComboCatalog(){ //
        return mcCombocatalog;
    }
    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
   


}
