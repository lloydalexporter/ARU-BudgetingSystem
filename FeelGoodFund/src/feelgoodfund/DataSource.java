package feelgoodfund;

import java.util.*;
import java.io.*;

public class DataSource {
    
    /* Attributes - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    public LinkedList<String> csvTeamsList = new LinkedList<String>();
    public LinkedList<String> csvDataTeam = new LinkedList<String>();
	public LinkedList<String> csvDataName = new LinkedList<String>();
	public LinkedList<Double> csvDataAmount = new LinkedList<Double>();

    public String csvTitleTeamsList = "teamsList.csv";
    public String csvTitleTeam = "dataTeam.csv";
    public String csvTitleName = "dataName.csv";
    public String csvTitleAmount = "dataAmount.csv";

    public double budget;
    public String budgetString;

    public boolean csvTeamsListNew;
    public boolean csvTeamNew;
    public boolean csvNameNew;
    public boolean csvAmountNew;

    public int teamsToCreate;
    public int count = 0;
    private String teamName;

	public String lineTeamsList;
	public String lineTeam;
	public String lineName;
	public String lineAmount;
	public double lineAmountConverted;

    public boolean repeat = true;

    
    
    /*  Methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    // Connect to CSV files
    public void createCSVFiles() {

        File fileTeamsList = new File(csvTitleTeamsList);
        File fileTeam = new File(csvTitleTeam);
        File fileName = new File(csvTitleName);
        File fileAmount = new File(csvTitleAmount);

        try {
            
            // IF TRUE, File has been created.
            if (fileTeamsList.createNewFile()) {
                csvTeamsListNew = true;
                System.out.println("File created: " + fileTeamsList.getName());
            }
            // IF TRUE, File has been created.
            if (fileTeam.createNewFile()) {
                csvTeamNew = true;
                System.out.println("File created: " + fileTeam.getName());
            }
            // IF TRUE, File has been created.
            if (fileName.createNewFile()) {
                csvNameNew = true;
                System.out.println("File created: " + fileName.getName());
            }
            // IF TRUE, File has been created.
            if (fileAmount.createNewFile()) {
                csvAmountNew = true;
                System.out.println("File created: " + fileAmount.getName());
            }

        } catch (IOException e) {

            System.err.println("An error occurred.");
            e.getMessage();
        }
    }

    public void createTeamsListOnFirstRun() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("  \nThis is the first time running this appilcation.");
        
        // Validation help -> https://stackoverflow.com/a/3059367/11359902
        do {
            System.out.print("\n\nEnter the FeelGoodFund Budget: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid Input, try again.\n");
                scanner.next(); // this is important!
                System.out.print("Enter the FeelGoodFund Budget: ");
            }
            budget = scanner.nextDouble();
        } while (!(budget >= 0));
        budgetString = Double.toString(budget) + "\n";
        
        // Validation help -> https://stackoverflow.com/a/3059367/11359902
        do {
            System.out.print("\n\nEnter the Amount of Teams you would like: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid Input, try again.\n");
                scanner.next(); // this is important!
                System.out.print("Enter the Amount of Teams you would like: ");
            }
            teamsToCreate = scanner.nextInt();
        } while (!(teamsToCreate > 0));
        
        // Clear the Scanner, ready for String input.
        scanner.nextLine();

        // FOR every amount of teams the user wants to make
        for (int i=0; i < teamsToCreate; i++) {
            System.out.printf("\nEnter the name of Team %d: ", i+1);
            teamName = scanner.nextLine();
            // Add teamName to csvTeamsList
            csvTeamsList.add(teamName);
        }

        // Close the scanner
        scanner.close();

        // Write all the team names to the file
        try (FileWriter teamsList = new FileWriter(csvTitleTeamsList, true)) {
            teamsList.append(budgetString); // first line is the budget.
			for (int i=0; i < csvTeamsList.size(); i++) {
				lineTeamsList = csvTeamsList.get(i) + "\n";
				teamsList.append(lineTeamsList);
			}
			teamsList.close();
		}
    }

    // Get Data from the CSVs into the necessary arraylists
    // https://www.baeldung.com/java-csv-file-array
	public void getDataFromCSVs() throws IOException { 

		// Put data into LinkedList
		try (BufferedReader readingFile = new BufferedReader(new FileReader(csvTitleTeamsList))) {
			while ((lineTeamsList = readingFile.readLine()) != null) {
				csvTeamsList.add(lineTeamsList);
			}
            readingFile.close();
		}
		try (BufferedReader readingFile = new BufferedReader(new FileReader(csvTitleTeam))) {
			while ((lineTeam = readingFile.readLine()) != null) {
				csvDataTeam.add(lineTeam);
			}
            readingFile.close();
		}
		try (BufferedReader readingFile = new BufferedReader(new FileReader(csvTitleName))) {
			while ((lineName = readingFile.readLine()) != null) {
				csvDataName.add(lineName);
			}
            readingFile.close();
		}
		try (BufferedReader readingFile = new BufferedReader(new FileReader(csvTitleAmount))) {
			while ((lineAmount = readingFile.readLine()) != null) {
				lineAmountConverted = Double.parseDouble(lineAmount);
				csvDataAmount.add(lineAmountConverted);
			}
            readingFile.close();
		}
	}

    // Sort Data into correct Teams and that
    public int sortData(ArrayList<String> teamNames) {

        // FOR every item in the CSVs
        for (int x = 0; x < csvDataTeam.size(); x++) {
            // FOR every team
            for (int y = 0; y < teamNames.size(); y++) {
                // IF the Team in the CSV matches a Team Name, return which team to do it
                if (csvDataTeam.getFirst().equals(teamNames.get(y))) {
                    return y+1; // add one, we will minus it later
                }
            }
        }
        return 0; // else return 0, then we know it's the end
    }
    
    // Delete the CSVs before adding the new values
    public void deleteCSVs() {

        // initiate the files
        File fileTeam = new File(csvTitleTeam);
        File fileName = new File(csvTitleName);
        File fileAmount = new File(csvTitleAmount);
        
        // delete the previous files, ready to save the new data in the next method calling
        fileTeam.delete();
        fileName.delete();
        fileAmount.delete();
    }
    
    // Save Data from the arraylists into the CSVs
    public void saveDataToCSVs(String thisTeamName, ArrayList<String> teamExpenditureName, ArrayList<Double> teamExpenditureAmount) throws IOException{
        
        // FOR every element in teamExpenditureName, append the expenditure team to the file.
        try (FileWriter csvTeam = new FileWriter(csvTitleTeam, true)) {
			for (int i=0; i < teamExpenditureName.size(); i++) {
				lineTeam = thisTeamName + "\n";
				csvTeam.append(lineTeam);
			}
			csvTeam.close();
		}

        // FOR every element in teamExpenditureName, append the expenditure name to the file.
        try (FileWriter csvName = new FileWriter(csvTitleName, true)) {
			for (int i=0; i < teamExpenditureName.size(); i++) {
				lineName = teamExpenditureName.get(i) + "\n";
				csvName.append(lineName);
			}
			csvName.close();
		}

        // FOR every element in teamExpenditureName, append the expenditure amount to the file.
        try (FileWriter csvAmount = new FileWriter(csvTitleAmount, true)) {
			for (int i=0; i < teamExpenditureName.size(); i++) {
				lineAmount = Double.toString(teamExpenditureAmount.get(i)) + "\n";
				csvAmount.append(lineAmount);
			}
			csvAmount.close();
		}
    }
}