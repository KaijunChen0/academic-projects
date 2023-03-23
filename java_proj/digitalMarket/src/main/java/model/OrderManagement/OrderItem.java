/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

import model.ProductManagement.Product;

/**
 *
 * @author kal bugrara
 */
public class OrderItem {

    Product selectedproduct;
    int actualPrice; // paid price
    int quantity;

    public OrderItem(Product p, int paidprice, int quantity) {
        selectedproduct = p;
        p.addOrderItem(this); // make sure product links back to the item
        this.quantity = quantity;
        this.actualPrice = paidprice;
    }

    public int getOrderItemTotal() {
        return actualPrice * quantity;
    }

    // The following calculates what the price gain would have been if products were
    // sold at target price
    public int getOrderItemTargetTotal() {
        return selectedproduct.getTargetPrice() * quantity;
    }

    // returns positive if seller is making higher margin than target
    // returns negative if seller is making lower margin than target
    // otherwise zero meaning neutral
    public int calculatePricePerformance() {
        return (actualPrice - selectedproduct.getTargetPrice()) * quantity;
    }

    public boolean isActualAboveTarget() {
        if (actualPrice > selectedproduct.getTargetPrice()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isActualBelowTarget() {
        if (actualPrice < selectedproduct.getTargetPrice()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isActualATTarget() {
        if (actualPrice == selectedproduct.getTargetPrice()) {
            return true;
        } else {
            return false;
        }

    }

    public Product getSelectedProduct() {
        return selectedproduct;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void printOrderedItemInfo(){
        System.out.println("*Selected ProductID:" + selectedproduct.getProductID() + "  *Name:" + selectedproduct.getName() + "  *Actual Price:$" + actualPrice + "  *Quantity:" +quantity);
    }

}
