package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Market;
import model.ProductManagement.SolutionOffer;

public class SolutionOrderList {
    ArrayList<SolutionOrder> solutionOrders;

    public SolutionOrderList() {
        solutionOrders = new ArrayList<SolutionOrder>();
    }

    public SolutionOrder newSolutionOrder(CustomerProfile cp,SolutionOffer offer){
        SolutionOrder sorder = new SolutionOrder(cp, offer);
        solutionOrders.add(sorder);
        return sorder;
    }

    public int getTotalRevenue(){
        int sum = 0;
        for(SolutionOrder order: solutionOrders){
            sum += order.getSolutionRevenue();
        }
        return sum;
    }

    //getRevenueByMarket()
    //getRevenueByAllComb()
    //

    public void showSolutionOrdersAndReprot(){
        System.out.println("--------------------------- List of Solution Order ---------------------------");
        System.out.println();
        int cnt = 1;
        for(SolutionOrder so: solutionOrders){
            System.out.println("No." + cnt++);
            so.printSolutionOrderInfo();
        }
        System.out.println("--------------------------- Solution Sales Report ---------------------------");
        System.out.println();
        System.out.println("MARKET - TOTAL REVENUE:");
        System.out.println("-----------------------");
        System.out.println("EAST - $" + getOrderSalesFromEastMarket() );
        System.out.println("WEST - $" + getOrderSalesFromWesttMarket());
        System.out.println("SOUTH - $" + getOrderSalesFromSouthMarket());
        System.out.println("NORTH - $" + getOrderSalesFromNorthtMarket());
        System.out.println();
        System.out.println("CHANNEL - TOTAL REVENUE:");
        System.out.println("----------------------");
        System.out.println("INTERNET - $" + calculateTotalOfInternet());
        System.out.println("TV - $" + calculateTotalOfTV());
        System.out.println("NEWSPAPER - $" + calculateTotalOfNewspaper());
        System.out.println("MAGAZINE - $" + calculateTotalOfMagazine());
        System.out.println("RADIO - $" + calculateTotalOfRadio());
        System.out.println();
        System.out.println("BUNDLE ID - NAME - TOTAL ORDERS - TOTAL SALES:");
        System.out.println("-------------------------------");
        System.out.println("NO1  S01P01SC00 - stud earrings holiday set - " + sumBundle1TotalOrders() + " - " + sumBundle1TotalSales());
        System.out.println("NO2  S01P02SC00 - hoop earrings holiday set - " + sumBundle2TotalOrders() + " - " + sumBundle2TotalSales());
        System.out.println("NO3  S01P03SC00 - drop earrings holiday set - " + sumBundle3TotalOrders() + " - " + sumBundle3TotalSales());
        System.out.println("NO4  S01P04SC00 - round ring holiday set - " + sumBundle4TotalOrders() + " - " + sumBundle4TotalSales());
        System.out.println("NO5  S01P05SC00 - oval ring holiday set - " + sumBundle5TotalOrders() + " - " + sumBundle5TotalSales());
        System.out.println("NO6  S01P06SC00 - princess ring holiday set - " + sumBundle6TotalOrders() + " - " + sumBundle6TotalSales());
        System.out.println("NO7  S01P07SC00 - gold bracelet holiday set - " + sumBundle7TotalOrders() + " - " + sumBundle7TotalSales());
        System.out.println("NO8  S01P08SC00 - silver bracelet holiday set - " + sumBundle8TotalOrders() + " - " + sumBundle8TotalSales());
        System.out.println("NO9  S01P09SC00 - pearl bracelet holiday set - " + sumBundle9TotalOrders() + " - " + sumBundle9TotalSales());
        System.out.println();
        System.out.println("******TOTAL ORDERS:" + (cnt-1) + "  ******TOTAL SALES:$" + getTotalRevenue());
        System.out.println();
    }

    public int calculateTotalSales(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            sum += so.getSolutionRevenue();
        }
        return sum;
    }

    //calculate total sales of markets
    public int getOrderSalesFromEastMarket(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.getMca().getMarket().getName().equalsIgnoreCase("east")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int getOrderSalesFromWesttMarket(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.getMca().getMarket().getName().equalsIgnoreCase("west")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int getOrderSalesFromNorthtMarket(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.getMca().getMarket().getName().equalsIgnoreCase("north")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int getOrderSalesFromSouthMarket(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.getMca().getMarket().getName().equalsIgnoreCase("south")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    //calculate total revenue from channels
    public int calculateTotalOfInternet(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.getMca().getChannel().isMatch("internet")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int calculateTotalOfTV(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.getMca().getChannel().isMatch("tv")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }
    
    public int calculateTotalOfMagazine(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.getMca().getChannel().isMatch("magazine")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int calculateTotalOfNewspaper(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.getMca().getChannel().isMatch("newspaper")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }
    
    public int calculateTotalOfRadio(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.getMca().getChannel().isMatch("radio")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    //get each bundle's total sale
    public int sumBundle1TotalSales(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P01SC00")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int sumBundle1TotalOrders(){
        int cnt = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P01SC00")){
                cnt++;
            }
        }
        return cnt;
    }

    public int sumBundle2TotalSales(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P02SC00")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int sumBundle2TotalOrders(){
        int cnt = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P02SC00")){
                cnt++;
            }
        }
        return cnt;
    }

    public int sumBundle3TotalSales(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P03SC00")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int sumBundle3TotalOrders(){
        int cnt = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P03SC00")){
                cnt++;
            }
        }
        return cnt;
    }

    public int sumBundle4TotalSales(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P04SC00")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int sumBundle4TotalOrders(){
        int cnt = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P04SC00")){
                cnt++;
            }
        }
        return cnt;
    }

    public int sumBundle5TotalSales(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P05SC00")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int sumBundle5TotalOrders(){
        int cnt = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P05SC00")){
                cnt++;
            }
        }
        return cnt;
    }

    public int sumBundle6TotalSales(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P06SC00")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int sumBundle6TotalOrders(){
        int cnt = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P06SC00")){
                cnt++;
            }
        }
        return cnt;
    }

    public int sumBundle7TotalSales(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P07SC00")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int sumBundle7TotalOrders(){
        int cnt = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P07SC00")){
                cnt++;
            }
        }
        return cnt;
    }

    public int sumBundle8TotalSales(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P08SC00")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int sumBundle8TotalOrders(){
        int cnt = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P08SC00")){
                cnt++;
            }
        }
        return cnt;
    }

    public int sumBundle9TotalSales(){
        int sum = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P09SC00")){
                sum += so.getSolutionRevenue();
            }
        }
        return sum;
    }

    public int sumBundle9TotalOrders(){
        int cnt = 0;
        for(SolutionOrder so:solutionOrders){
            if(so.isMatchProductBundle("S01P09SC00")){
                cnt++;
            }
        }
        return cnt;
    }
}
