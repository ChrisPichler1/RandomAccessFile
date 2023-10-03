/* This is our Driver main method to actually run the methods provided in the Methods class. It contains the main Switch statement that powers the entire program. Because the
 * methods are defined in a separate class, it is very small and only really contains static calls to methods in the Methods class.
 * Programmer: Chris Pichler
 * Course:     COSC 311, F'23
 * Project:    1
 * Due date:   9-19-23
 */


import java.io.*;
import java.util.*;

public class Driver {
	//The main method throws a FileNotFoundException and an IOException to avoid errors in the program.
	public static void main(String[] args) throws FileNotFoundException, IOException{
		//Print the programmer name, course name, project number, and due date in accordance to the rubric.
		Methods.intro();
		Scanner scnr; //This is our Scanner object used take input from the user's keyboard to decide which method numbers to run.
		int input; //This integer variable holds the integer input from the user's keyboard to decide which method numbers to run.
		
		//Keep asking the user for a number until they input "7".
		do {
			//Create our Scanner object to read input from the user's keyboard.
			scnr = new Scanner(System.in);
			//Print the Switch menu for the user, this is called every time the do-while loop goes through an iteration.
			Methods.menu();
			//Confirm the user input an integer, if not, the user is told they input an incorrect input and the menu prints again for the next iteration.
			try {
				input = scnr.nextInt();
			}catch(InputMismatchException ex) {
				System.out.println("Non-numeric input");
				continue;
			}
			//This is our Switch statement that is called each time a user enters an appropriate integer to do something to our Random Access File.
			switch(input) {
				case 1:
					//The user entered "1", call the optionOne() static method from the Method's class to make a Random Access File.
					Methods.optionOne();
					break;
				case 2:
					//The user entered "2", call the optionTwo() static method from the Method's class to display a Random Access File.
					Methods.optionTwo();
					break;
				case 3:
					//The user entered "3", call the optionThree() static method from the Method's class to retrieve a method from a Random Access File.
					Methods.optionThree();
					break;
				case 4:
					//The user entered "4", call the optionFour() static method from the Method's class to modify a record in a Random Access File.
					Methods.optionFour();
					break;
				case 5:
					//The user entered "5", call the optionFive() static method from the Method's class to add a record from a Random Access File.
					Methods.optionFive();
					break;
				case 6:
					//The user entered "6", call the optionSix() static method from the Method's class to delete a record from a Random Access File.
					Methods.optionSix();
					break;
				case 7:
					//The user entered "7", a String "Bye!" is printed to the console in accordance to the rubric and the program ends.
					System.out.println("Bye!");
					break;
				default:
					//The user entered an invalid integer. Let them know that number is an invalid number and start the do-while loop over.
					System.out.println(input + " is an invalid number");
					break;
			}
			//End the program if the user types "7".
			if(input == 7) {
				break;
			}
		//Only end the do-while loop when the user enters "7".
		}while(true);
	}	
}
