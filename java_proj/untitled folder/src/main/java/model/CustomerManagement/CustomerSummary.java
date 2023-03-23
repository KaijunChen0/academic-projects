/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import model.OrderManagement.Order;

/**
 *
 * @author kal bugrara
 */
public class CustomerSummary {
    CustomerProfile customerprofile;
    int ordertotal;
    int totalTargetSales;
    int pricePerformance;
    int numberOfOrdersAbovetarget;
    int numberOfOrdersBelowtarget;
    int ordersCount; 

    public CustomerSummary(CustomerProfile cp) {
        customerprofile = cp;
        ordertotal = cp.getTotalSales();
        totalTargetSales = getTotalTargetVolume();
        pricePerformance = getPricePerformanceInCS();
        numberOfOrdersAbovetarget = getNumberOfOrdersAboveTotalTarget();
        numberOfOrdersBelowtarget = getNumberOfOrdersBelowTotalTarget();
        ordersCount = getTotalOrdersCount();
    }

    public String getCustomerID(){
        return customerprofile.getCustomerId();
    }

    public String getCustomerName(){
        return customerprofile.getPerson().getName();
    }

    public int getTotalSales(){
        return ordertotal;
    }

    public int getTotalTargetVolume(){
        return customerprofile.getTotalTargetVolumeInProfile();
    }

    public int getPricePerformanceInCS(){
        return customerprofile.getTotalPricePerformance();
    }

    public int getNumberOfOrdersAboveTotalTarget(){
        return customerprofile.getNumberOfOrdersAboveTotalTarget();
    }

    public int getNumberOfOrdersBelowTotalTarget(){
        return customerprofile.getNumberOfOrdersBelowTotalTarget();
    }

    public int getTotalOrdersCount(){
        return customerprofile.getOrdersCount();
    }

    public void printCustomerSummary(){
        System.out.println("Customer ID:" + getCustomerID() + "  Name:" + getCustomerName());
        System.out.println();
        System.out.println("*Total Sales:$" + getTotalSales() + "  *Total Target Sales:$" + getTotalTargetVolume() + "  *Price Performance:$" + getPricePerformanceInCS());
        System.out.println("*Number of Orders above Target Sales:" + getNumberOfOrdersAboveTotalTarget());
        System.out.println("*Number of Orders below Target Sales:" + getNumberOfOrdersBelowTotalTarget());
        System.out.println("*Number of Orders:" + getTotalOrdersCount());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}