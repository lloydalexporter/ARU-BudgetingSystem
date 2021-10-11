package feelgoodfund;

import java.util.ArrayList;
import java.util.LinkedList;

public class Teams {
    
    /* Classes  - - - - - - - - - - - - - - - - - - - - - - - - - */
    Team[] teamClass;

    /* Attributes - - - - - - - - - - - - - - - - - - - - - - - - */
    public ArrayList<String> teamNames = new ArrayList<String>();
    public int teamCount;
    public double totalTeamsExpenditure;
    
    public ArrayList<Double> teamsExpenditure = new ArrayList<Double>();


    /*  Methods - - - - - - - - - - - - - - - - - - - - - - - - - */

    public void createTeams(LinkedList<String> teamNamesList) {

        // Initialise Team Count with the Amount of Teams
        teamClass = new Team[teamCount];

        // FOR every team amount, create a Team
        for (int i=0; i < teamCount; i++) {
            teamClass[i] = new Team(teamNamesList.get(i));
        }
    }


    public double calculateAllTeamsTotalExpenditure() {

        // Clear the ArrayList before adding the new values
        teamsExpenditure.clear();

        // FOR every team
        for (int i=0; i < teamCount; i++) {
            // set team expenditure index element to the team's total expenditure

            // TRY to set the index, EXCEPT if index doesn't exist, then add it.
            try {
                teamsExpenditure.set(i,teamClass[i].calculateTotalTeamExpenditure());
            } catch (Exception e) {
                teamsExpenditure.add(teamClass[i].calculateTotalTeamExpenditure());
            }
        }
        
        // Set total to ZERO
        totalTeamsExpenditure = 0;

        // FOR every team, accumulate the total expenditure for all teams
        for (int i = 0; i < teamCount; i++) {
            totalTeamsExpenditure += teamsExpenditure.get(i);
        }
        
        return totalTeamsExpenditure;
    }
}
