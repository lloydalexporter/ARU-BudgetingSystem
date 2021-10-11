package feelgoodfund;

import java.util.ArrayList;

public class Team {
    
    /* Attributes - - - - - - - - - - - - - - - - - - - - - - - - */

    public double totalTeamExpenditure;

    public ArrayList<String> teamExpenditureName = new ArrayList<String>();
    public ArrayList<Double> teamExpenditureAmount = new ArrayList<Double>();
    private int teamExpenditureSize;

    public String teamName;

    Team (String teamName) {
        this.teamName = teamName;
    }


    /*  Methods - - - - - - - - - - - - - - - - - - - - - - - - - */

    // Add every value of teamExpenditure ArrayList to the totalTeamExpenditure value.
    public double calculateTotalTeamExpenditure() {

        // Set total to ZERO
        totalTeamExpenditure = 0d;

        // Get size of the ArrayList
        teamExpenditureSize = teamExpenditureAmount.size();

        // FOR every item in 'teamExpenditureAmount'
        for (int i = 0; i < teamExpenditureSize; i++) {

            // Accumulates the sum of each expenditure
            totalTeamExpenditure += teamExpenditureAmount.get(i);
        }

        // System.out.printf("\nTotal Team Expenditure: %d", totalTeamExpenditure);
        return totalTeamExpenditure;
    }

    // Add Expenditure, with the passed Index value.
    public void addTeamExpenditure(String name, double value) {
        teamExpenditureName.add(name);
        teamExpenditureAmount.add(value);
    }

    // Remove Expenditure, with the passed Index value.
    public void removeTeamExpenditure(int index) {
        System.out.printf("\nRemoved %s from the Teams expenditure.", teamExpenditureName.get(index));
        teamExpenditureName.remove(index);
        teamExpenditureAmount.remove(index);
    }

}
