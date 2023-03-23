package model.MarketModel;

import java.util.ArrayList;
import java.util.Random;

public class MarketChannelComboCatalog {
    Random randomIndex = new Random();
    ArrayList<MarketChannelAssignment> marketChannelAssignmentList;

    public MarketChannelComboCatalog() {
        marketChannelAssignmentList = new ArrayList<MarketChannelAssignment>();
    }

    public MarketChannelAssignment newMarketChannelAssignment(Market m, Channel c){
        MarketChannelAssignment mca = new MarketChannelAssignment(m, c);
        marketChannelAssignmentList.add(mca);
        return mca;
    }

    public MarketChannelAssignment findMarketChannelAssignment(Market m, Channel c){
        for(MarketChannelAssignment mca: marketChannelAssignmentList){
            if(mca.isMatch(m,c)){
                return mca;
            }
        }
        return null;
    }

    public MarketChannelAssignment randomPickAMarketChannelAssignment(){
        int index = randomIndex.nextInt(marketChannelAssignmentList.size());
        MarketChannelAssignment mca = marketChannelAssignmentList.get(index);
        return mca;
    }

    public void sumAndprintAllBudgetOfMCA(){
        int sum1 = 0;
        int sum2 = 0;
        System.out.println("MARKET - CHANNEL - BUDGET:");
        System.out.println("--------------------------");
        for(MarketChannelAssignment mca: marketChannelAssignmentList){
            System.out.println(mca.getMarket().getName()+" - "+ mca.getChannel().getChannelType()+" - $" +mca.getAdbudget());
            sum1 += mca.getAdbudget();
            sum2 += mca.getTargetrevenue();
        }
        System.out.println();
        System.out.println("***** TOTAL BUDGET: $" + sum1 + "  ***** TOTAL TARGET REVENUE:" + sum2);
        System.out.println();
    }

}
