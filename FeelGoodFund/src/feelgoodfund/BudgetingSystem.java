/*
FEEL GOOD FUND Budgeting System
Element 011 : 2020 MOD003484 TRI2 - Software Principles

- Lloyd Alex Porter
- StudentID: 2007666
- Team Stratosphere
*/

package feelgoodfund;

import java.io.IOException;
import java.util.Scanner;

public class BudgetingSystem {

    /* Classes  - - - - - - - - - - - - - - - - - - - - - - - - - */
    static Budget budgetClass = new Budget();
    static Menus menuClass = new Menus();
    static DataSource dataClass = new DataSource();

    /* Attributes - - - - - - - - - - - - - - - - - - - - - - - - */
    private static boolean continueProgramLoop = true;
    private static int menuChoice;
    
    private static double budgetValue;
    private static double totalExpenditureValue;
    private static double remainingValue;
    private static int vrbChoice;
    private static String teamName;
    private static double teamExpenditure;
    private static double teamExpenditureSUM;
    
    private static int swtChoice;
    private static int expenditureCount;
    
    private static int index;
    
    private static int teamIndex;
    private static String name;
    private static double value;
    

    // FOR THE REDGATE SECURE SIGN-IN, AS MENTIONED IN ELEMENT 010 BY REDGATE THEMSELVES, THIS
    // BOOLEAN VARIABLE WOULD CHANGE BETWEEN TRUE AND FALSE, DEPENDING ON THE USERS DETAILS.
    private static boolean userIsManager = true;
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



    /*  Methods - - - - - - - - - - - - - - - - - - - - - - - - - */

    // Gets users Menu Choice and goes from there...
    private static void mainMenu() {
        
        // Initialise the Scanner for the menu choice
        Scanner integerInput = new Scanner(System.in);

        // Main WHILE loop for the menu
        while (continueProgramLoop) {

            // print main menu options
            menuClass.printMainMenuOptions(userIsManager);

            // Validation help -> https://stackoverflow.com/a/3059367/11359902
            do {
                System.out.print("#? ");
                while (!integerInput.hasNextInt()) {
                    integerInput.next(); // this is important! 
                    System.out.print("#? ");
                }
                menuChoice = integerInput.nextInt();
            } while (!(menuChoice >= 1 && menuChoice <= 5));

            // IF not a manager, then the choices are reduced, so change
            // what they picked to the right option for the menuChoice.
            if (!userIsManager && menuChoice == 4) {
                menuChoice = 5;
            }


            // SWITCH statement for the Main Menu Choice.
            switch (menuChoice) {

                // Case 1 ------------------------------------------------------------------------1
                case 1: // VIEW REMAINING BUDGET

                    // Set budget value; Get total expenditure; Get remaining budget
                    budgetValue = budgetClass.totalBudget;
                    totalExpenditureValue = budgetClass.teamsClass.calculateAllTeamsTotalExpenditure();
                    remainingValue = budgetClass.remainingBudget();

                    menuClass.printViewRemainingBudget(budgetValue,totalExpenditureValue,remainingValue);
                    vrbChoice = menuClass.viewRemainingBudgetOptions();

                    // IF user wants to see split budget data
                    if (vrbChoice == 1) {

                        teamExpenditureSUM = 0;
                        
                        // print table header and names
                        System.out.print("\n\n/==========[ SPLIT TEAM BUDGET ]==========\\");
                        System.out.print("  \n|                                         |");
                        System.out.print("  \n| TEAM NAME |  EXPENDITURE |      SUM     |");
                        for (int i = 0; i < budgetClass.teamsClass.teamCount; i++) {

                            teamName = budgetClass.teamsClass.teamNames.get(i);
                            teamExpenditure = budgetClass.teamsClass.teamsExpenditure.get(i);
                            teamExpenditureSUM += teamExpenditure;

                            menuClass.printViewSplitRemainingBudget(teamName, teamExpenditure, teamExpenditureSUM);
                        }
                        // print table footer
                        System.out.print("  \n|                                         |");
                        System.out.print("  \n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");

                    } // ELSE skip this and BREAK to main menu

                    break;

                // Case 2 ------------------------------------------------------------------------2
                case 2: // VIEW TEAM EXPENDITURE

                    // Get the choice of team
                    swtChoice = menuClass.viewSelectWhichTeam(budgetClass.teamsClass.teamCount, budgetClass.teamsClass.teamNames);
                    expenditureCount = budgetClass.teamsClass.teamClass[swtChoice-1].teamExpenditureName.size();
                    
                    // IF expenditureCount is ZERO, then there are no expenditures to show
                    if (expenditureCount == 0) {
                        System.out.printf("\n%s has not made any expenditures.", budgetClass.teamsClass.teamNames.get(swtChoice-1));
                        break;
                    }

                    // print the selected teams expenditure
                    menuClass.printTeamExpenditure(swtChoice, expenditureCount, budgetClass.teamsClass.teamClass[swtChoice-1].teamExpenditureName, budgetClass.teamsClass.teamClass[swtChoice-1].teamExpenditureAmount);
                    break;
                
                // Case 3 ------------------------------------------------------------------------3
                case 3: // LOG NEW EXPENDITURE

                    // Get the choice of which team; get the name; get the value
                    swtChoice = menuClass.viewSelectWhichTeam(budgetClass.teamsClass.teamCount, budgetClass.teamsClass.teamNames);
                    menuClass.getExpenditureName();
                    menuClass.getExpenditureAmount();

                    // Add expenditure with the choice, with name and value
                    budgetClass.teamsClass.teamClass[swtChoice-1].addTeamExpenditure(menuClass.expenditureName, menuClass.expenditureAmount);
                    break;

                // Case 4 ------------------------------------------------------------------------4
                case 4: // REMOVE AN EXPENDITURE

                    swtChoice = menuClass.viewSelectWhichTeam(budgetClass.teamsClass.teamCount, budgetClass.teamsClass.teamNames);
                    expenditureCount = budgetClass.teamsClass.teamClass[swtChoice-1].teamExpenditureName.size();
                    
                    // IF expenditureCount is ZERO, then there are no expenditures to remove
                    if (expenditureCount == 0) {
                        System.out.printf("\n%s has no expenditures to remove.", budgetClass.teamsClass.teamNames.get(swtChoice-1));
                        break; // break, no longer does the rest of the program
                    }
                    
                    // Get the index value of the expenditure they want to remove
                    index = menuClass.selectWhichExpenditure(swtChoice, expenditureCount, budgetClass.teamsClass.teamClass[swtChoice-1].teamExpenditureName, budgetClass.teamsClass.teamClass[swtChoice-1].teamExpenditureAmount);
                    budgetClass.teamsClass.teamClass[swtChoice-1].removeTeamExpenditure(index);

                    break;

                // Case 5 ------------------------------------------------------------------------5
                case 5: // EXIT THE SYSTEM
                    continueProgramLoop = false;
            }
            
            // IF Loop is going to continue, then wait for user input to continue...
            if (continueProgramLoop) {
                System.out.print("\n\nPress \"ENTER\" to return to main menu...");
                integerInput.nextLine();
                integerInput.nextLine();
            }
        }

        // Close the Scanner
        integerInput.close();
    }


    // Activate what needs to be activated, on Start Up
    public static void addPreviousData() throws IOException {
        
        do {
            // Gets Teams Index, and sorts the data
            teamIndex = dataClass.sortData(budgetClass.teamsClass.teamNames);

            // IF index is not ZERO
            if (teamIndex != 0) {

                // Get the first values of the datas name and amounbt
                name = dataClass.csvDataName.getFirst();
                value = dataClass.csvDataAmount.getFirst();

                // Add this expenditure to the correct team
                budgetClass.teamsClass.teamClass[teamIndex-1].addTeamExpenditure(name, value);

                // Remove the first value, from all LinkedLists
                dataClass.csvDataTeam.removeFirst();
                dataClass.csvDataName.removeFirst();
                dataClass.csvDataAmount.removeFirst();
            }
        } while (teamIndex != 0);
        // If ZERO then no more data to add
        
        System.out.println("\nAll previous data has been added.");
    }

    public static void setUpTeams() throws IOException {

        // Create the CSV Files
        dataClass.createCSVFiles();

        // IF no teams list file, then ask for teams
        if (dataClass.csvTeamsListNew) {
            dataClass.createTeamsListOnFirstRun();
        } else {
            // Get the Data out of the CSV and into the Lists and LinkedLists
            dataClass.getDataFromCSVs();

            // Get the budget, then delete all existance of it.
            budgetClass.totalBudget = Double.parseDouble(dataClass.csvTeamsList.getFirst());
            dataClass.csvTeamsList.removeFirst();
        }


        // Get Number of Teams, and Get Team Names, and remove the duplicates
        budgetClass.teamsClass.teamCount = dataClass.csvTeamsList.size();

        // Create a Team Class for each Team
        budgetClass.teamsClass.createTeams(dataClass.csvTeamsList);

        // Add Team Names to Team Names ArrayList
        for (int i=0; i < budgetClass.teamsClass.teamCount; i++) {
            budgetClass.teamsClass.teamNames.add(dataClass.csvTeamsList.get(i));
        }
        
    }

    public static void main(String[] args) throws Exception {
        
        // Set Up the Teams... and the Budget too now.
        setUpTeams();

        // IF no teams have been made before, ask for teams, then quit.
        // I had trouble continuing it because of scanners and that,
        // so a quick fix was just to IF/ELSE it.
        // If I had more time, I would fix this, but yanno, time.
        if (dataClass.csvTeamsListNew) {

            System.out.println("\n\nTeams have been created.");
            System.out.println("Restart program to begin Budgeting System.");

        // ELSE do the actual program
        } else {

            // Run 'onStartUp' method to add CSV functionality;
            // Comment out this function to start with blank data sets.
            addPreviousData();
            
            // Run the menu method
            mainMenu();
            
            // Delete the CSVs
            dataClass.deleteCSVs();
            
            // Save Data to the CSVs
            for (int i=0; i < budgetClass.teamsClass.teamCount; i++) {
                dataClass.saveDataToCSVs(budgetClass.teamsClass.teamNames.get(i), budgetClass.teamsClass.teamClass[i].teamExpenditureName, budgetClass.teamsClass.teamClass[i].teamExpenditureAmount);
            }
            
            // Time so say goodbye... :(
                System.out.println("\n\nThank you for using this program,");
                System.out.println("preparing to quit program...");
        }
    }
}



// NOTES:
/*
 * This Budgeting System is very different from what my group created for Element 010.
 * Why? Because we planned a GUI-based system, and one of the first lines I read when
 * looking at this assignment, after handing in the previous one, read:
 * 
 *      "You are not expected to produce a GUI application for this assignment
 *      and all interaction should be accomplished through the console."
 * 
 * And there are a lot of differences between a GUI and a CLI application.
 * 
 * In Element 010, our group never really mentioned the names of any Classes, Methods,
 * nor attributes. Any that were mentioned were mostly part of a Sign-In system, which
 * turns out is also not wanted / needed for this Assignment, so that was not used.
 * However, there is a boolean value at the top of this file that I have commented around
 * which, from Redgates Sign-In system, would change this value depending on the
 * sign-in details.
 * 
 * Also Java's relationshop with CSV files is complicated; if I had more time I would
 * merge all the CSV files into one. If I had even more time, I would possibly of
 * tried to make a database. To do that I would've had to import a third-party SQL
 * Database library, which is a hassle, hence why I haven't and probably will never will.
 * 
 * I've tried to Validate some inputs, but I've found it fairly difficult so that would
 * be something I would try to improve if I had more time.
 * 
 * So in conclusion, I have deviated massively from my group's submission of Element 010.
 * This was necessary as we wrote Element 010 before really knowing Java's capabilities,
 * and therefore made statments that we did not realise would be very difficult.
 * 
 * 
 * Validation help -> https://stackoverflow.com/a/3059367/11359902
 * 
 */


 // CODE DUMP BELOW :
 /*

 
 
 
 */ 