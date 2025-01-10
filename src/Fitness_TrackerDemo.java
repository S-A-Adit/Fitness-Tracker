package Deliverable_3;
import java.util.*;
public class Fitness_TrackerDemo {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Welcome to the Fitness Tracker! Running the code will create the table and add rows to it.");
		CreateTable createTable = new CreateTable();
		createTable.createTable();
		InsertInto insertInto = new InsertInto();
		insertInto.insertInto();
		RetrieveTable retrieveTable = new RetrieveTable();
	    retrieveTable.printTable();
		System.out.println("\nSSelect what to do with Table!");
		System.out.println("Choose from 1 to 4.\n1.Query \n2.Update Row \n3.Delete Row \n4.Drop Table\nDonot repeat any of the processes moree than once!");
		
	
		String repeat = "y";
		
		while (repeat.equalsIgnoreCase("y")) {
			
			System.out.println("Enter your choice: ");
            int usermenu = keyboard.nextInt();
            keyboard.nextLine();
			
            switch (usermenu) {
            case 1:
                GymMemberQuery query = new GymMemberQuery();
                query.ageRange();
                AverageCount query1 = new AverageCount();
                query1.averageCount();
                break;

            case 2:
                UpdateRow updateRow = new UpdateRow();
                updateRow.updateRow();
        	    retrieveTable.printTable();
                break;

            case 3:
                DeleteRow deleteRow = new DeleteRow();
                deleteRow.deleteRow();
                retrieveTable.printTable();
                break;

            case 4:
                DropTable dropTable = new DropTable();
                dropTable.dropTable();
                System.out.println("\nTable dropped. Exiting the program.");
                repeat = "n"; // Stop repeating after dropping the table
                continue;

            default:
                System.out.println("\nInvalid choice. Please select a number between 1 and 4.");
                
                break;
        
		}
            if (repeat.equalsIgnoreCase("y")) {
                System.out.println("\nEnter 'y' to repeat the menu. Any other input will exit: ");
                repeat = keyboard.nextLine();

                if (!repeat.equalsIgnoreCase("y")) {
                    System.out.println("\nExiting the program. Thank you for using the Fitness Tracker!");
                    break;
                }
			
	 }
		
	}	
}
}


