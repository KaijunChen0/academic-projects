/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import model.MarketModel.Market;
import model.OrderManagement.Order;
import model.OrderManagement.SolutionOrder;
import model.Personnel.Person;

/**
 *
 * @author kal bugrara
 */
public class CustomerProfile {
    ArrayList<Order> orders;
    //ArrayList<Market> markets; //no needed
    Person person;
    ArrayList<SolutionOrder> solutionorders;

    public CustomerProfile(Person p) {
        person = p;
        orders = new ArrayList<Order>();
    }

    public int getTotalSales() {
        int sum = 0;
        for (Order o : orders) {
            sum += o.getOrderTotal();
        }
        return sum;
    }

    //
    public int getTotalBundleSales() {
        int sum = 0;
        for (SolutionOrder so : solutionorders) {
            sum += so.getSolutionRevenue();
        }
        return sum;
    }

    public int getTotalPricePerformance() {
        // for each order in the customer orderlist
        // calculate order price performance and add it to the sum
        int margin = 0;
        for (Order o : orders) {
            margin += o.getOrderPricePerformance();
        }
        return margin;
    }

    public int getNumberOfOrdersAboveTotalTarget() {
        // for each order in the customer order list
        // calculate if order is positive (actual order total is greater than sum of
        // item targets
        // if yes then add 1 to total
        int sum = 0;
        for (Order o : orders) {
            if (o.isOrderAboveTotalTarget() == true)
                sum = sum + 1;
        }

        return sum;
    }

    public int getNumberOfOrdersBelowTotalTarget() {
        int sum = 0;
        for (Order o : orders) {
            if (o.isOrderBelowTotalTarget() == true)
                sum = sum + 1;
        }

        return sum;
    }
    // for each order in the customer order list
    // calculate if order is negative
    // if yes then add 1 to total

    public boolean isMatch(String id) {
        if (person.getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    public void addCustomerOrder(Order o) {
        orders.add(o);
    }

    @Override
    public String toString() {
        return person.getPersonId();
    }

    public String getCustomerId() {
        return person.getPersonId();
    }

    public Person getPerson() {
        return person;
    }

    public void printCustomerProfile(){
        System.out.println("------------------------------------------------------------");
        System.out.println("Customer ID:" + getCustomerId() + "  Name:" + getPerson().getName() + "  Address:" + getPerson().getAddress());
        System.out.println();
        int ordercount = 0;
        for(Order o: orders){
            ordercount++;
            System.out.println("Order " + ordercount + ":");
            System.out.println("Order Number " + o.getOrderNumber());
            o.printOrderInfo();
            System.out.println("Total:$" + o.getOrderItemsTotalSales());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    public int getTotalTargetVolumeInProfile(){
        int sum = 0;
        for(Order o: orders){
            sum += o.getTargetSales();
        }
        return sum;
    }

    public int getOrdersCount(){
        int cnt = 0;
        for(Order o: orders){
            cnt++;
        }
        return cnt;
    }

    //if customer is belong to a targeting market
    public boolean isMatchToAMarket(Market market){
        if(person.getAddress().equalsIgnoreCase(market.getName())){
            return true;
        }
        return false;
    }



}
