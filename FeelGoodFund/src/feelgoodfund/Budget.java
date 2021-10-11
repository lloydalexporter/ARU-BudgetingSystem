package feelgoodfund;

public class Budget {
    
    /* Classes  - - - - - - - - - - - - - - - - - - - - - - - - - */
    Teams teamsClass = new Teams();

    /* Attributes - - - - - - - - - - - - - - - - - - - - - - - - */
    public double totalBudget;
    public double totalExpenditure;
    public double totalRemaining;

    /*  Methods - - - - - - - - - - - - - - - - - - - - - - - - - */
    public double remainingBudget() {

        // calculation of the remaining budget
        totalRemaining = totalBudget - teamsClass.calculateAllTeamsTotalExpenditure();

        return totalRemaining;
    }
}


// CODE DUMP:
/*
 *
 * public void calculateBudget() {

        // FOR every team, print
        for (int i = 0; i < teamsClass.teamCount; i++) {
            System.out.println(i);
        }
    }
 *  
 * 
 */