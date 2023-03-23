/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import java.util.Random;

import model.Supplier.Supplier;

/**
 *
 * @author kal bugrara
 */
public class ProductCatalog {
    String type;
    ArrayList<Product> products; // list of products initially empty
    Supplier supplier;

    public ProductCatalog(String type, Supplier supplier) {
        this.supplier = supplier;
        this.type = type;
        products = new ArrayList<Product>(); /// create the list of elements otherwise it is null
    }

    // new ProductCatalog(); or new ProductCatalog("Printers");
    public ProductCatalog(Supplier supplier) {
        this.supplier = supplier;
        type = "unknown";
        products = new ArrayList<Product>();
    }

    public Product newProduct(String id, int fp, int cp, int tp) {
        Product p = new Product(id,fp, cp, tp);
        products.add(p);
        return p;
    }

    public Product newProduct(String id,String n, int fp, int cp, int tp) {
        Product p = new Product(id, n, fp, cp, tp);
        products.add(p);
        return p;
    }

    public ProductsReport generatProductPerformanceReport() {
        ProductsReport productsreport = new ProductsReport();

        for (Product p : products) {

            ProductSummary ps = new ProductSummary(p);
            productsreport.addProductSummary(ps);
        }
        return productsreport;
    }

    public ArrayList<Product> getProductList() {
        return products;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public void printOneSupplierAllProducts(){
        for(Product p: products){
            p.printProductInformation();
        }
    }

    public Product pickARandomProduct(){
        Random r = new Random();
        int randomIndex = r.nextInt(products.size());
        return products.get(randomIndex);
    }

    public Supplier getSupplier(){ //
        return supplier;
    }

    


}
