package model.ProductManagement;

import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import model.Supplier.Supplier;

public class ProductBundleCatalog {
    Random random = new Random();
    ArrayList<ProductBundle> bundles;

    public ProductBundleCatalog(){
        bundles = new ArrayList<ProductBundle>();
    }

    public ArrayList<ProductBundle> getProductBundles(){
        return bundles;
    }

    public ProductBundle newProductBundle(Product p, Service s){
        ProductBundle bundle = new ProductBundle(p, s);
        bundles.add(bundle);
        return bundle; 
    }

    public void printBundleListInfo(){
        System.out.println("--------------------------- List of Product Bundles ---------------------------");
        int cnt = 0;
        for(ProductBundle pb: bundles){
            System.out.println("No." + ++cnt);
            pb.printProductBundleInfo();
        }
    }

    public void showBundlePriceOfList(){
        System.out.println("--------------------------- List of Bundle's Price ---------------------------");
        int cnt = 0;
        System.out.println("P1 targeting areas of east and west via Internet and TV");
        System.out.println("P2 targeting areas of east and west via magazine, newspaper and radio");
        System.out.println("P3 targeting areas of north and south via Internet and TV");
        System.out.println("P4 targeting areas of north and south via magazine, newspaper and radio");
        System.out.println();
        for(ProductBundle pb: bundles){
            System.out.println("No." + ++cnt);
            System.out.println("Bundle ID:" + pb.getBundleID());
            System.out.println("Name:" + pb.getBundleName());
            System.out.println("*P1:$" + pb.getPriceOfEWIT()+" *P2:$"+ pb.getPriceOfEWMNR()+ " *P3:$" + pb.getPriceOfNSIT()+" *P4:$" +pb.getPriceOfNSMNR());
            System.out.println("");
        }

    }

    public ProductBundle pickArandomBundle(){
        int randomIndex = random.nextInt(bundles.size());
        ProductBundle pb = bundles.get(randomIndex);
        return pb;

    }
    
}
