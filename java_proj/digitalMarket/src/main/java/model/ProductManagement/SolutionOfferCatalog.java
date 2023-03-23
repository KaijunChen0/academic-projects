/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */
public class SolutionOfferCatalog {
    ArrayList<SolutionOffer> solutionoffers;
    
    public SolutionOfferCatalog() {
        solutionoffers = new ArrayList<SolutionOffer>();
    }
    
    public SolutionOffer newSolutionOffer(MarketChannelAssignment mca, ProductBundle pb){
        
        SolutionOffer so = new SolutionOffer(mca, pb);
        solutionoffers.add(so);
        return so;
    }

    public void printSolutionOfferList(){
        System.out.println("--------------------------- List of Solution Offers ---------------------------");
        int cnt = 1;
        for(SolutionOffer so: solutionoffers){
            System.out.println("No." + cnt++);
            so.printSolutionOffer();
        }
    }

}
