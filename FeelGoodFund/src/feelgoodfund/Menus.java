package feelgoodfund;

import java.util.ArrayList;
import java.util.Scanner;

public class Menus {
    

    /* Attributes - - - - - - - - - - - - - - - - - - - - - - - - */
    private String mainMenuOptions[] = {"View Remaining Budget", "View Team Expenditure", "Log New Expenditure", "Remove an Expenditure", "Exit the System"};

    private int caseOneChoice;

    private int caseThreeChoice;
    public String expenditureName;
    private int expenditureNameLength;
    public double expenditureAmount;
    private boolean genCaseThreeLoop;
    private boolean geaCaseThreeLoop;
    private int expenditureAmountLength;

    private int caseFourChoice;
    


    /*  Methods - - - - - - - - - - - - - - - - - - - - - - - - - */

    // Print out the Main Menu Options
    public void printMainMenuOptions(boolean manager) {
        System.out.print(" \n\n/============[ MENU OPTIONS ]=============\\");
        System.out.print("   \n|                                         |");

        System.out.printf("\n| %d) %-21s                |", 1, mainMenuOptions[0]);
        System.out.printf("\n| %d) %-21s                |", 2, mainMenuOptions[1]);
        System.out.printf("\n| %d) %-21s                |", 3, mainMenuOptions[2]);

        // IF a manager, then print the managerial options; ELSE don't do that.
        if (manager) {
            System.out.printf("\n| %d) %-21s                |", 4, mainMenuOptions[3]);
            System.out.printf("\n| %d) %-21s                |", 5, mainMenuOptions[4]);
        } else {
            System.out.printf("\n| %d) %-21s                |", 4, mainMenuOptions[4]);
        }

        System.out.print("   \n|                                         |");
        System.out.print("   \n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n");
    }



    // CASE 01 : VIEW REMAINING BUDGET
    public void printViewRemainingBudget(double a, double b, double c) {
        System.out.print("\n\n/========[ VIEW REMAINING BUDGET ]========\\");
        System.out.print("  \n|                                         |");
        System.out.printf(" \n| %26s %12.2f |", "FeelGoodFund Budget:", a);
        System.out.printf(" \n| %26s %12.2f |", "Combined Team Expenditure:", b);
        System.out.print("  \n+—————————————————————————————————————————+");
        System.out.printf(" \n| %26s %12.2f |", "Remaining Expenditure:", c);
        System.out.print("  \n|                                         |");
        System.out.print("  \n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
    }
    
    private void printViewRemainingBudgetOptions() {
        System.out.print("\n\n/===============[ OPTIONS ]===============\\");
        System.out.print("  \n|                                         |");
        System.out.print("  \n| 1) View Teams Seperately                |");
        System.out.print("  \n| 2) Return to Main Menu                  |");
        System.out.print("  \n|                                         |");
        System.out.print("  \n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
    }
    public int viewRemainingBudgetOptions() {

        printViewRemainingBudgetOptions();
        
        Scanner vrbScanner = new Scanner(System.in);
        
        // Validation help -> https://stackoverflow.com/a/3059367/11359902
        do {
            System.out.print("\n#? ");
            while (!vrbScanner.hasNextInt()) {
                vrbScanner.next(); // this is important!
                System.out.print("#? ");
            }
            caseOneChoice = vrbScanner.nextInt();
        } while (!(caseOneChoice == 1 || caseOneChoice == 2));
        // Input must be either a '1' or a '2'
        
        return caseOneChoice;
    }
    
    public void printViewSplitRemainingBudget(String teamName, double teamExpenditure, double sum) {
        // prints out the table spacing and format for each row
        System.out.printf(" \n| %9s | %12.2f | %+12.2f |", teamName, teamExpenditure, sum);
    }



    // CASE 02 : VIEW TEAM EXPENDITURE
     
    private void printSelectWhichTeamOptions(int teamCount, ArrayList<String> teamNames) {
         
        // print the header
        System.out.print("\n\n/=============[ SELECT TEAM ]=============\\");
        System.out.print("  \n|                                         |");
         
        // FOR every team, print the team name, formatted nicely
        for (int i = 0; i < teamCount; i++) {
            System.out.printf("\n| %2d) %-20s                |", i+1, teamNames.get(i));
        }
            
        // print the footer
        System.out.print("  \n|                                         |");
        System.out.print("  \n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n");
    }
    public int viewSelectWhichTeam(int teamCount, ArrayList<String> teamNames) {
            
        printSelectWhichTeamOptions(teamCount, teamNames);
        
        Scanner swtScanner = new Scanner(System.in);

        // Validation help -> https://stackoverflow.com/a/3059367/11359902
        do {
            System.out.print("#? ");
            while (!swtScanner.hasNextInt()) {
                swtScanner.next(); // this is important!
                System.out.print("#? ");
            }
            caseThreeChoice = swtScanner.nextInt();
        } while (!(caseThreeChoice >= 1 && caseThreeChoice <= teamCount));
        // Input must be between 1 and the total amount of teams

        return caseThreeChoice;
    }
    
    public void printTeamExpenditure(int teamID, int expenditureCount, ArrayList<String> teamExpenditureName, ArrayList<Double> teamExpenditureAmount) {
            
        // print the header
        System.out.print("\n\n/===========[ TEAM EXPENDITURE ]==========\\");
        System.out.print("  \n|                                         |");
        System.out.print("  \n| ID  NAME                        AMOUNT  |");
        
        // FOR every team, print the team name, formatted nicely
        for (int i = 0; i < expenditureCount; i++) {
            System.out.printf("\n| %2d  %-20s   %12.2f |", i+1, teamExpenditureName.get(i), teamExpenditureAmount.get(i));
        }
        
        // print the footer
        System.out.print("  \n|                                         |");
        System.out.print("  \n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
    }
    
    // CASE 03 : LOG NEW EXPENDITURE & CASE 04 : REMOVE AN EXPENDITURE

    public void getExpenditureName() {

        Scanner genScanner = new Scanner(System.in);
        
        genCaseThreeLoop = true;

        while (genCaseThreeLoop) {
            
            try {
                System.out.print("\n\nExpenditure name must be 20 characters or less.");
                System.out.print("\nEnter Expenditure Name: ");

                // get next integer value user enters
                expenditureName = genScanner.nextLine();
                // System.out.println(expenditureName);
                expenditureNameLength = expenditureName.length();
                // System.out.println(expenditureNameLength);
            } catch (Exception e) {
                expenditureName = null;
            }

            // IF length of expenditure name is more than 20c, then it's too long
            if (expenditureNameLength > 20) {
                
                System.err.println("Expenditure name is too long,");
                System.err.println("try again, with less that 20 characters.");
                
                expenditureName = null;
            }

            if (expenditureName != null) {
                genCaseThreeLoop = false;
            }
        }
    }
    
    public void getExpenditureAmount() {
        Scanner geaScanner = new Scanner(System.in);
        
        geaCaseThreeLoop = true;

        System.out.println("\nExpenditure amount must be 12 characters or less.");
        System.out.println("Value will be automatically rounded.");

        // Validation help -> https://stackoverflow.com/a/3059367/11359902
        do {
            System.out.print("Enter Expenditure Amount: ");
            while (!geaScanner.hasNextDouble()) {
                geaScanner.next(); // this is important!
                System.out.print("Enter Expenditure Amount: ");
            }
            expenditureAmount = geaScanner.nextDouble();
            // get the length of the value
            expenditureAmountLength = String.valueOf(expenditureAmount).length();

            // IF length of expenditure amount is more than 12c, then it's too long
            if (expenditureAmountLength > 12) {
                
                System.err.println("Expenditure name is too long,");
                System.err.println("try again, with less that 12 characters.");
                expenditureAmount = 0;
            
            } else if (expenditureAmount <= 0) {

                System.err.println("Expenditure cannot be negative nor '0',");
                System.err.println("try again, with a positive number.");
                
                expenditureAmount = 0;
            }

            // IF number is negative, don't allow it
            if (expenditureAmount != 0) {
                geaCaseThreeLoop = false;
            } else {
                geaCaseThreeLoop = true;
            }

        } while (geaCaseThreeLoop);
    }

    public int selectWhichExpenditure(int teamID, int expenditureCount, ArrayList<String> teamExpenditureName, ArrayList<Double> teamExpenditureAmount) {
        
        printTeamExpenditure(teamID, expenditureCount, teamExpenditureName, teamExpenditureAmount);
        
        Scanner sweScanner = new Scanner(System.in);


        // Validation help -> https://stackoverflow.com/a/3059367/11359902
        do {
            System.out.print("\nEnter Expenditure ID: ");
            while (!sweScanner.hasNextInt()) {
                sweScanner.next(); // this is important!
                System.out.print("\nEnter Expenditure ID: ");
            }
            caseFourChoice = sweScanner.nextInt();
        } while (!(caseFourChoice >= 1 && caseFourChoice <= expenditureCount));

        return caseFourChoice-1;
    }
}
