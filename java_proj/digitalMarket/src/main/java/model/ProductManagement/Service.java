package model.ProductManagement;

import model.Business.*;

public class Service {
    Business business;
    String serviceCode;
    String name;
    int ceilingprice;
    int floorprice;
    int targetprice;

    public Service(Business business) {
        this.business = business;
        serviceCode = "SC00";
        name = "Jewelry Insurance";
        ceilingprice = 109;
        floorprice = 59;
        targetprice = 89;   
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public String getName() {
        return name;
    }

    public int getCeilingprice() {
        return ceilingprice;
    }

    public int getFloorprice() {
        return floorprice;
    }

    public int getTargetprice() {
        return targetprice;
    }

    public Business getBusiness() {
        return business;
    }


    

    
}
