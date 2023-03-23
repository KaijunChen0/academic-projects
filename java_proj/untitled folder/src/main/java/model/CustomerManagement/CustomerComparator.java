package model.CustomerManagement;

import java.util.Comparator;

public class CustomerComparator implements Comparator<CustomerSummary>{
    String sortingOrder;
    String sortingField;

    public CustomerComparator(String sortingOrder, String sortingField) {
        if(sortingOrder == "descending"){
            this.sortingOrder = sortingOrder;
        }
        else{
            this.sortingOrder = "ascending";
        }
        this.sortingField = "Price Performance";  //default sorting field
        if(sortingField != ""){
            this.sortingField = sortingField;
        }
    }

    public String getSortingOrder() {
        return sortingOrder;
    }

    public String getSortingField() {
        return sortingField;
    }

    @Override
    public int compare(CustomerSummary cs1, CustomerSummary cs2) {
        // if cs1.getPricePerformance() > cs2.getPricePerformance() return -1;
        //change -1 to 1 to reverse the ordering
        // if cs1.getPricePerformance() = cs2.getPricePerformance() return 0;
        // if cs1.getPricePerformance() < cs2.getPricePerformance() return 1;
        //return 1 
        //this method can be replaced by the following method
        int direction = 1;
        if(sortingOrder == "descending"){
            direction = -1;  //multiply (-1) to reverse the ordering
        }
        if(sortingField == "Total Sales"){
            return direction * Integer.compare(cs1.getTotalSales(), cs2.getTotalSales());
        }
        if(sortingField == "Total Target Sales"){
            return direction * Integer.compare(cs1.getTotalTargetVolume(), cs2.getTotalTargetVolume());
        }
        if(sortingField == "Number of Orders Above Target"){
            return direction * Integer.compare(cs1.getNumberOfOrdersAboveTotalTarget(), cs2.getNumberOfOrdersAboveTotalTarget());
        }
        if(sortingField == "Number of Orders Below Target"){
            return direction * Integer.compare(cs1.getNumberOfOrdersBelowTotalTarget(), cs2.getNumberOfOrdersBelowTotalTarget());
        }
        if(sortingField == "Number of Orders"){
            return direction * Integer.compare(cs1.getTotalOrdersCount(), cs2.getTotalOrdersCount());
        }
        return direction * Integer.compare(cs1.getPricePerformanceInCS(), cs2.getPricePerformanceInCS()); //default sorting field
    }

}
