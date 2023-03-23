/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;
import java.util.Collections;

//import com.github.javafaker.Business; //
import model.Business.*;

/**
 *
 * @author kal bugrara
 */
public class CustomersReport {
    Business business;
    ArrayList<CustomerSummary> customersummarylist;
    CustomerComparator comparator; //+

    public CustomersReport(Business business) {
        this.business = business;
        customersummarylist = new ArrayList<CustomerSummary>();
    }

    public void addCustomerSummary(CustomerSummary cs) {
        customersummarylist.add(cs);
    }

    public void printCustomersReport(){
        System.out.println();
        System.out.println("--------------------------- Customers Report ---------------------------");
        System.out.println();
        int cnt = 0;
        for(CustomerSummary cs: customersummarylist){
            System.out.printf("Number:%d  ", ++cnt); //add sequence number
            cs.printCustomerSummary();
        }
        System.out.println("** TOTAL OF SALES:$" + calculateTotalSalesOfAllSummaries());
        System.out.println("** TOTAL OF TARGET SALES:$" + calculateTotalTargetSalesOfAllSummaries());
        System.out.println("** TOTAL OF PRICE PERFORMANCE:$" + calculateTotalPPOfSummaries());
        System.out.println("** TOTAL NUMBER OF ORDERS ABOVE TARGET PRICE:" + calculateTotalNumberOfOrdersAbove());
        System.out.println("** TOTAL NUMBER OF ORDERS BELOW TARGET PRICE:" + calculateTotalNumberOfOrdersBelow());
        System.out.println("** TOTAL NUMBER OF ORDERS:" + calculateTotalOrders());
        System.out.println();
    }

    //sort customer summary by price performance
    public void sortByAndPrint(){
        System.out.println();
        System.out.println("--------------------------- Customers Report --------------------------");
        System.out.println();
        comparator = new CustomerComparator("descending", "Price Performance");
        Collections.sort(customersummarylist, comparator);
        System.out.println("Sort by " + comparator.getSortingField() + "(" + comparator.getSortingOrder() + "):" );
        System.out.println();
        int cnt = 0;
        for(CustomerSummary cs: customersummarylist){
            System.out.printf("Number:%d  ", ++cnt);
            cs.printCustomerSummary();
        }
        System.out.println("** TOTAL OF SALES:$" + calculateTotalSalesOfAllSummaries());
        System.out.println("** TOTAL OF TARGET SALES:$" + calculateTotalTargetSalesOfAllSummaries());
        System.out.println("** TOTAL OF PRICE PERFORMANCE:$" + calculateTotalPPOfSummaries());
        System.out.println("** TOTAL NUMBER OF ORDERS ABOVE TARGET PRICE:" + calculateTotalNumberOfOrdersAbove());
        System.out.println("** TOTAL NUMBER OF ORDERS BELOW TARGET PRICE:" + calculateTotalNumberOfOrdersBelow());
        System.out.println("** TOTAL NUMBER OF ORDERS:" + calculateTotalOrders());
        System.out.println();

    }

    //calculate all the summaries' total sales
    public int calculateTotalSalesOfAllSummaries(){
        int sumOfSales = 0;
        for(CustomerSummary cs:customersummarylist){
            sumOfSales += cs.getTotalSales();
        }
        return sumOfSales;
    }

    //calculate all summaries' total target sales
    public int calculateTotalTargetSalesOfAllSummaries(){
        int sum = 0;
        for(CustomerSummary cs: customersummarylist){
            sum += cs.getTotalTargetVolume();
        }
        return sum;
    }

    //calculate all summaries' total price peformance
    public int calculateTotalPPOfSummaries(){
        int sum = 0;
        for(CustomerSummary cs: customersummarylist){
            sum += cs.getPricePerformanceInCS();
        }
        return sum;
    }

    //calculate all the summaries' total number of orders above target sales
    public int calculateTotalNumberOfOrdersAbove(){
        int cnt = 0;
        for(CustomerSummary cs: customersummarylist){
            cnt += cs.getNumberOfOrdersAboveTotalTarget();
        }
        return cnt;
    }
    
    //calculate all the summaries' total number of orders below target sales
    public int calculateTotalNumberOfOrdersBelow(){
        int cnt = 0;
        for(CustomerSummary cs: customersummarylist){
            cnt += cs.getNumberOfOrdersBelowTotalTarget();
        }
        return cnt;
    }

    public int calculateTotalOrders(){
        int cnt = 0;
        for(CustomerSummary cs: customersummarylist){
            cnt += cs.getTotalOrdersCount();
        }
        return cnt;
    }



}
