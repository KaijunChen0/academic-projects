/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import model.OrderManagement.OrderItem;

/**
 *
 * @author kal bugrara
 */
public class Product {
    private String productID;
    private String name;
    private int floorPrice;
    private int ceilingPrice;
    private int targetPrice;
    ArrayList<OrderItem> orderitems;
    

    public Product(String id, int fp, int cp, int tp) {
        productID = id;
        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        orderitems = new ArrayList<OrderItem>();
    }

    public Product(String id,String name, int fp, int cp, int tp) {
        productID = id;
        this.name = name;
        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        orderitems = new ArrayList<OrderItem>();
    }

    public Product updateProduct(int fp, int cp, int tp) {
        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        return this; // returns itself
    }

    public String getProductID(){
        return productID;
    }

    public void setPorductID(String id){
        productID = id;
    }

    public int getTargetPrice() {
        return targetPrice;
    }

    public void addOrderItem(OrderItem oi) {
        orderitems.add(oi);
    }

    // Number of item sales above target
    public int getNumberOfProductSalesAboveTarget() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            if (oi.isActualAboveTarget() == true)
                sum = sum + 1;
        }
        return sum;
    }

    public int getNumberOfProductSalesBelowTarget() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            if (oi.isActualBelowTarget() == true)
                sum = sum + 1;
        }
        return sum;
    }

    public boolean isProductAlwaysAboveTarget() {
        for (OrderItem oi : orderitems) {
            if (oi.isActualAboveTarget() == false)
                return false; //
        }
        return true;
    }

    // calculates the revenues gained or lost (in relation to the target)
    // For example, if target is at $2000 and actual is $2500 then revenue gained
    // is $500 above the expected target. If the actual is $1800 then the lose will
    // be $200
    // Add all these difference to get the total including wins and loses

    public boolean isProductAlwaysBelowTarget() {
        for (OrderItem oi : orderitems) {
            if (oi.isActualBelowTarget() == false)
                return false; //
        }
        return true;
    }

    public int getOrderPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.calculatePricePerformance(); // positive and negative values
        }
        return sum;
    }

    public int getSalesVolume() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.getOrderItemTotal(); // positive and negative values
        }
        return sum;
    }

    public void setName(String n) {
        name = n;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getFloorPrice() {
        return floorPrice;
    }

    public int getCeilingPrice() {
        return ceilingPrice;
    }

    public int getNumberOfSoldProducts(){
        int cnt = 0;
        for(OrderItem oi:orderitems){
            cnt += oi.getQuantity();
        }
        return cnt;
    }

    public void printProductInformation(){
        System.out.println("Product ID: " + getProductID() + "  Name:" + getName() + "  *Floor Price:$" + getFloorPrice() + "  *Ceiling Price:$" + getCeilingPrice() + "  *Target Price:$" + getTargetPrice() + "  *Quantity:" + getNumberOfSoldProducts() + "  *Total Sales:$" + getSalesVolume() + "  *Price Performance:$" + getOrderPricePerformance());
    }

    
}
