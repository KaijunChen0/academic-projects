package model.ProductManagement;

import java.util.Random;

import model.Business.Business;
import model.Supplier.Supplier;

public class ProductBundle {
    private String bundleID;
    private String bundleName;
    Product product;
    Service service;
    int priceOfEWIT; //price sold in the area of east and west via Internet and TV channels
    int priceOfNSIT; //price sold in the area of north and south via Internet and TV channels
    int priceOfEWMNR; //price sold in the area of east and west via magazines, newspapers and radio channels
    int priceOfNSMNR; //price sold in the area of north and south via magazines, newspapers and radio channels

    public ProductBundle(Product product, Service service) {
        this.product = product;
        this.service = service;
        bundleID = product.getProductID() + service.getServiceCode();
        bundleName = product.getName() + " holiday set";
    }

    public String getBundleID() {
        return bundleID;
    }

    public String getBundleName() {
        return bundleName;
    }

    public Product getProduct() {
        return product;
    }

    public Service getService() {
        return service;
    }
    
    public int getPriceOfEWIT() {
        return product.getCeilingPrice() + service.getCeilingprice();
    }

    public int getPriceOfNSIT() {
        return product.getTargetPrice() + service.getCeilingprice();
    }

    public int getPriceOfEWMNR() {
        return product.getCeilingPrice() + service.getTargetprice();
    }

    public int getPriceOfNSMNR() {
        return product.getTargetPrice() + service.getTargetprice();
    }

    public void printProductBundleInfo(){
        System.out.println("Bundle ID:" + getBundleID() + "  Name:" + getBundleName());
        System.out.println("Details:" + getProduct().getName() + ", " + getService().getName());
        System.out.println();
    }

    

    
    
}
