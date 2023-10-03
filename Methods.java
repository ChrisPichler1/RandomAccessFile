/* In this class, the methods are defined for our main method Driver program to call in order to complete the Switch statement in accordance to the rubric. It contains an intro
 * method that starts out the program that states the programmer's name, the course title, the project number, and the due date. It also has the menu for the Switch statement
 * that tells the user what each input does to the random access file, and will show up after each method is completed. I will summarize each method below in more detail.
 * Programmer: Chris Pichler
 * Course:     COSC 311, F'23
 * Project:    1
 * Due date:   9-19-23
 */

import java.util.*;
import java.io.*;
public class Methods{
	//These are the variables used in the various methods in this class. It is declared globally in order for them to be used in a local setting in multiple methods without having
	//to re-initialize them each time.
	public static Scanner scnr = new Scanner(System.in); //This is a Scanner object that is used to read input from the user's keyboard.
	public static Scanner scanr; //This is a Scanner object used to read the text file when first creating the random access file.
	public static File file; //This is File object that will hold the text file used in option 1.
	public static File rafFile; //This is a File object used when the Random Access File is created to check if the File already exists. If so, it will delete that file.
	public static int input; //This integer variable is used all over the class to hold integers, mostly input by the user and used by each method.
	public static String fileName; //This String variable holds the filename initialized in option 1 to ask what text file the user wants to access.
	public static String name; //This String variable holds the filename initialized in option 1 to confirm that the user wants to access the correct Random Access file.
	public static RandomAccessFile raf; //This is our RandomAccessFile variable used all over the program, mainly as parameters to methods defined in the Student class.
	public static String response; //This is a String variable used to read what the user inputs and what they want to do inside the Switch statement.
	public static String rafName; //This is a String variable used to confirm that the user wants to access the correct Random Access file (if it equals String name).
	public static Student student; //This is our Student object used all over the methods to use the instance methods in the Student class.
	public static long length; //This variable holds the length of the Random Access file.
	public static long i; //This variable is used in various loops in the program. It could theoretically be declared in each method separately. 
	public static double gpa; //This double variable holds the GPA if the user wants to manipulate the GPA of a student.
	public static int id; //This integer variable holds the ID if the user wants to manipulate the ID of a student.
	
	//This is the intro method in accordance to the rubric. It is only used once at the start of the program.
	public static void intro() {
		System.out.println("Programmer:    Chris Pichler");
		System.out.println("Course:        COSC 311, F'23");
		System.out.println("Project:       1");
		System.out.println("Due date:      9-19-23\n");
	}
	
	//This is the menu used each time a user is asked what they would like to do to the Random Access File. It is used the most of any method in this class.
	public static void menu() {
		System.out.println("\n   Menu");
		System.out.println("   ====");
		System.out.println("1: Make a random-access file");
		System.out.println("2: Display a random-access file");
		System.out.println("3: Retrieve a record");
		System.out.println("4: Modify a record");
		System.out.println("5: Add a record");
		System.out.println("6: Delete a record");
		System.out.println("7: Exit\n");
		System.out.print("Enter your choice: ");
	}
	
	//This holds the menu in option 4 if the user wants to manipulate a variable inside a Student object in the Random Access file.
	public static void fourMenu() {
		System.out.print("\n1- Change the first name: ");
		System.out.print("\n2- Change the last name: ");
		System.out.print("\n3- Change ID: ");
		System.out.print("\n4- Change GPA: ");
		System.out.print("\n5- Done: ");
		System.out.print("\nEnter your choice: ");
	}
	
	//This is option one. Create a Random Access File and confirm to the user that it worked. It throws a FileNotFoundException and an IOException to avoid runtime errors.
	public static void optionOne() throws FileNotFoundException, IOException{
		//Asking the user what text file they want to use and saving it to fileName.
		System.out.print("Enter an input file name: ");
		fileName = scnr.next();
		//Asking the user what file name they want to use for the Random Access file and saving it to name.
		System.out.print("Enter an output file name: ");
		name = scnr.next();
		//This is used to create a File name and confirm whether or not the file name already exists.
		rafFile = new File(name);
		//Delete the file if it exists
		if(rafFile.exists()) {
			rafFile.delete();
		}
		//Create our Random Access File used throughout this program.
		raf = new RandomAccessFile(name, "rw");
		file = new File(fileName);
		//Use a Scanner to read an input from the text file
		scanr = new Scanner(file);
		student = new Student();
		//As long as the file isn't empty, read the text file as input and output a Random Access file in binary
		if(file.length() > 0){
			while(scanr.hasNext()) {
				student.readFromTextFile(scanr);
				student.writeToFile(raf);
			}
		}
		System.out.println("Random access file is built successfully!");
	}
	
	//This is option 2 that will print out the Random Access file neatly for the user either 5 names at at time, or all at once. It throws an IOException to avoid errors.
	public static void optionTwo() throws IOException {
		System.out.print("Enter the random access file name: ");
		rafName = scnr.next();
		//Confirming the user wants to access the correct Random Access File.
		if(rafName.equals(name)) {
			student = new Student();
			//Put our pointer back to the start of the Random Access File.
			raf.seek(0);
			do {
				i = 0;
				//Start out by printing the first 5 non-deleted names in the Random Access File (referred to as RAF for the rest of these comments)
				while(i < 5) {
					try {
						student.readFromFile(raf);
							if(!student.getFirst().startsWith("DELETED")) {
								System.out.println(student);
							}
							else {
								i--;
							}
							i++;
					}
					//When we reach the end of the RAF, break the loop with no errors.
					catch(EOFException ex) {
						break;
					}
				}
				System.out.print("\nEnter N (for next 5 records), A (for all remaining records), M(for main menu):");
				//Asking the user if they want to print the next 5 students, all the remaining students, or go back to the main menu.
				response = scnr.next();
			}while(response.equalsIgnoreCase("n"));
			//Print all the remaining students in the console.
			if(response.equalsIgnoreCase("a")) {
				while(true) {
					try {
						student.readFromFile(raf);
						if(!student.getFirst().startsWith("DELETED")) {
							System.out.println(student);
						}
					}
					//Stop when the RAF ends and stop any runtime errors from appearing.
					catch(EOFException ex) {
						break;
					}
				}
			}
		}
		//The RAF name input by the user doesn't exist.
		else {
			System.out.println(rafName + " is not found");
		}
	}
	
	//This is the third option, printing one student that the user wants to view. It throws an IOException to avoid errors.
	public static void optionThree() throws IOException {
		length = raf.length();
		System.out.print("Enter a record number: ");
		input = scnr.nextInt();
		//The number input by the user is too high, that record number doesn't exist.
		if((input * 92) > length) {
			System.out.println(input + " is an invalid record number");
		}
		else {
			//Move the file pointer to the start of whatever students the user wants to view.
			raf.seek(92 * (input - 1));
			student = new Student();
			student.readFromFile(raf);
			//Use the toString() method in the Student class to print the students as long as they're not deleted.
			if(!student.getFirst().startsWith("DELETED")) {
				System.out.println(student);
			}
			else {
				//The user was deleted, let the user know this.
				System.out.println("This record has been deleted");
			}
		}
	}
	
	//This is option 4, manipulating a user's information supplied by the user's input. It throws an IOException to avoid errors.
	public static void optionFour() throws IOException {
		length = raf.length();
		System.out.print("Enter a record number: ");
		input = scnr.nextInt();
		if(input * 92 <= length) {
			raf.seek(92 * (input - 1));
			student = new Student();
			student.readFromFile(raf);
			//The user was deleted, let the user know they can't modify a deleted record.
			if(student.getFirst().startsWith("DELETED")) {
				System.out.println("Can't modify a deleted record");
			}
		else {
			System.out.println(student);
			//Move the file pointer to the start of the student the user wants to manipulate.
			raf.seek(92 * (input - 1));
			do {
				fourMenu();
				input = scnr.nextInt();
				switch(input) {
					//Change the student's first name and save it to the RAF.
					case 1:
						System.out.print("Enter first name: ");
						response = scnr.next();
						student.setFirst(response);
						student.writeToFile(raf);
						break;
					//Change the student's last name and save it to the RAF.
					case 2:
						System.out.print("Enter last name: ");
						response = scnr.next();
						student.setLast(response);
						student.writeToFile(raf);
						break;
					//Change the student's ID and save it to the RAF.
					case 3: 
						System.out.print("Enter ID: ");
						id = scnr.nextInt();
						student.setID(input);
						student.writeToFile(raf);
						break;
					//Change the student's GPA and save it to the RAF.
					case 4:
						System.out.print("Enter GPA: ");
						gpa = scnr.nextDouble();
						student.setGPA(gpa);
						student.writeToFile(raf);
						break;
					//Exit the menu.
					case 5:
						break;
					default:
						System.out.println("Invalid response");
						break;
				}
			}while(input != 5);
		}
		}
		else {
			//The user entered an invalid record number.
			System.out.println(input + "is an invalid record number");
		}
	}
	
	//This is option 5, adding a student to the end of the RAF. It throws an IOException to avoid errors.
	public static void optionFive() throws IOException {
		Student addedStudent = new Student();
		//The user will supply the first name, last name, ID, and GPA, and the program will write the user into the end of the RAF.
		System.out.print("Enter first name: ");
		String firstName = scnr.next();
		System.out.print("Enter last name: ");
		String lastName = scnr.next();
		System.out.print("Enter ID: ");
		id = scnr.nextInt();
		System.out.print("Enter GPA: ");
		gpa = scnr.nextDouble();
		addedStudent.setData(firstName, lastName, id, gpa);
		//Move the file pointer to the end of the RAF.
		raf.seek(raf.length());
		addedStudent.writeToFile(raf);		
	}
	
	//This is option 6, lazy deletion. The user chooses a student to delete, and that student's first name is changed to "DELETED". If so, that user is not reachable in any other method.
	//It throws an IOException to avoid errors.
	public static void optionSix() throws IOException {
		System.out.print("Enter a record number: ");
		int input = scnr.nextInt();
		if((92 * input) > raf.length()) {
			//The user supplied an invalid record number.
			System.out.println(input + " is an invalid record number");
		}
		else {
			raf.seek(92 * (input - 1));
			//Change the student's first name to "DELETED" and overwrite it in the RAF.
			student.setFirst("DELETED");
			student.writeToFile(raf);
		}
	}
}

//Output provided in a separate text file.
