/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Supplier;

import java.util.ArrayList;

import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;

/**
 *
 * @author kal bugrara
 */
public class Supplier {
    String supplierID;
    String name;
    ProductCatalog productcatalog;
    ProductsReport productsreport;

    public Supplier(String supplierID, String name) {
        this.supplierID = supplierID;
        this.name = name;
        productcatalog = new ProductCatalog(this);

    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public ProductsReport prepareProductsReport() {

        productsreport = productcatalog.generatProductPerformanceReport();
        return productsreport;
    }

    public ArrayList<ProductSummary> getProductsAlwaysAboveTarget() {

        if (productsreport == null)
            productsreport = prepareProductsReport();
        return productsreport.getProductsAlwaysAboveTarget();

    }

    public String getName() {
        return name;
    }

    public ProductCatalog getProductCatalog() {
        return productcatalog;
    }
    // add supplier product ..

    // update supplier product ...
    @Override
    public String toString() {
        return name;

    }

    public void printOneSupplierAllProducts(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Supplier ID:" + getSupplierID() + "  Name:" + getName());
        System.out.println("---------------------------------------------------------------------");
        productcatalog.printOneSupplierAllProducts();
    }
}
