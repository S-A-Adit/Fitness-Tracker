package console_version;
import java.util.*;
import java.util.Scanner;

public class Fitness_TrackerDemo {
    public static void main(String[] args) {
        try (Scanner keyboard = new Scanner(System.in)) {
            System.out.println("Welcome to the Fitness Tracker!");

            CreateTable createTable = new CreateTable();
            if (createTable.tableExists("FITNESSTRACKERDATASET")) {
                System.out.println("Table already exists.");
            } else {
                System.out.println("Table not found. Creating now...");
                createTable.createTable();
            }

            RetrieveTable retrieveTable = new RetrieveTable();
            

            System.out.println("\nWhat would you like to do with the table?");
            System.out.println("Choose from the following options:");
            System.out.println("1. Add Rows");
            System.out.println("2. Run Query");
            System.out.println("3. Update Row");
            System.out.println("4. Delete Row");
            System.out.println("5. Drop Table");
            System.out.println("Note: Please do not repeat any action more than once!");

            String repeat = "y";

            while (repeat.equalsIgnoreCase("y")) {
                System.out.print("\nEnter your choice (1-5): ");
                int userChoice = -1;

                try {
                    userChoice = Integer.parseInt(keyboard.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    continue;
                }

                switch (userChoice) {
                    case 1 -> {
                        InsertInto insertInto = new InsertInto();
                        insertInto.insertInto();
                        retrieveTable.printTable();
                    }
                    case 2 -> {
                        GymMemberQuery query = new GymMemberQuery();
                        query.ageRange();

                        AverageCount averageCount = new AverageCount();
                        averageCount.averageCount();
                    }
                    case 3 -> {
                        UpdateRow updateRow = new UpdateRow();
                        updateRow.updateRow();
                        retrieveTable.printTable();
                    }
                    case 4 -> {
                        DeleteRow deleteRow = new DeleteRow();
                        deleteRow.deleteRow();
                        retrieveTable.printTable();
                    }
                    case 5 -> {
                        DropTable dropTable = new DropTable();
                        dropTable.dropTable();
                        System.out.println("\nTable dropped. Exiting the program.");
                        return;
                    }
                    default -> {
                        System.out.println("\nInvalid choice. Please select a number between 1 and 5.");
                    }
                }

                System.out.print("\nDo you want to perform another action? (y/n): ");
                repeat = keyboard.nextLine();
            }

            System.out.println("\nExiting the program. Thank you for using the Fitness Tracker!");
        }
    }
}



