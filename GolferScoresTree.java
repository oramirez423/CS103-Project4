/**********************************************************************************************
* Omar Ramirez
* CSC 103 Project 4: Golfer Scores Database using a Binary Search Tree
*
* The class GolferScoresTree is the driver/tester of the Golfer.java and TreeBag.java
* as it assures that the methods and object work correctly.
* 
* This class reads from a file name golferinfo.txt and creates a binary search tree with Golfer 
* objects that contain the data values stored in the infile. The class then promps the user with
* a menu, allowing the user to select from 7 options including:
*    
*    1. displaying the golfers (either in alphabetical order or in tree formatting)
*    2. displaying and updating the stats of an individual golfer
*    3. adding and removing a golfer from the tree
*    4. exiting the application and creating a file containing any new data.
*
***********************************************************************************************/
import java.util.*;
import java.io.*; // for in and out file 

class GolferScoresTree{
   public static void main(String[] args) throws IOException{
      // variables for the main program
      boolean exit = false;
      int option;
      
      TreeBag <Golfer> golfersInTree = new TreeBag<Golfer>();        // creating a tree with Golfer objects and            
      readFile(golfersInTree);                                       // calling the readFile method to read the file and
                                                                     // store the data values in the created tree
      
      while(exit == false){            // while loop that drives the main menu
        option = getMenuOption();
         
        if(option == 7) {  
           updateDatafile(golfersInTree);                               
           exit = true;                    
         }
         
         // switch to run each option in the menu   
         switch(option) { 
         
            case 1:  displayAll(golfersInTree);          // displays all the golfers in alphabetical order
                     break;
                     
            case 2:  displayTreeFormat(golfersInTree);   // use displayAsTree to display all the golfers in current tree format
                     break;
                                                                                   
            case 3:  displayStatsForOne(golfersInTree);  // displays the statistics for one golfer
                     break;
                     
            case 4:  updateStats(golfersInTree);         // allows user to update the statistics of a golfer by adding a new score 
                     break;
            
            case 5:  addNewGolfer(golfersInTree);        // allows user to add a new golfer and insert it to tree
                     break;
            
            case 6:  removeGolfer(golfersInTree);        // allows user to remove a golfer from the tree
                     break; 
                                
            }             
      }// end of while loop
   }// end of main method
   
   
   // To get a menu option from user.
   // getMenuOption() will only allow non-negative integers in the correct range     
   public static int getMenuOption(){
      Scanner menuInput = new Scanner(System.in);        // getMenuOption ensures user will never be able to crash program 
      boolean repeat = true;                             // by only accepting integer numbers in the correct menu range. 
      int option = -1;
      while(repeat == true){                             // otherwise loop will continue prompting user to enter input 
         printMenu();          
         try{                         
            option = menuInput.nextInt();
            if (option < 1 || option > 7) {
               throw new Exception("Not an option. Please enter a menu option.");
            }
            repeat = false;   
         } catch (InputMismatchException e) { 
            System.out.println("Invalid input. Please enter a menu option.");
            menuInput.nextLine();
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
      return option; //returns the menuOption
   } 
   
   // method to get an int value from the uesr to store as a round for a golfer object
   // method will only be used when creating a new golfer object.
   public static int insertRounds(){
      Scanner input = new Scanner(System.in);
      boolean repeat = true;
      int rounds = -1;
      while(repeat == true){                                                     // menu will repeat as long as 
         try{                                                                    // the user does not enter a valid int value within the right range 
            rounds = input.nextInt();
            if(rounds < 0) {
               throw new Exception("Please enter a valid number of rounds.");
            }
            repeat = false;
         } catch (InputMismatchException e){
            System.out.println("Invalid input. Rounds must be an integer.");
            input.nextLine();
         } catch (Exception e) { 
            System.out.println(e.getMessage());
         }
      }
      return rounds;   
   }
   
   // method to get a double value from the user to store as a score for a golfer object 
   // will only be used when user wants to add a new score to the average score of the golfer or when creating a new golfer object 
   public static double insertScore(){
      Scanner input = new Scanner(System.in);
      boolean repeat = true;
      double score = -1;
      while(repeat == true){                                            // menu will repeat as long as 
         try{                                                           // the user does not input a valid double
            score = input.nextDouble();
            if(score < 0) {
               throw new Exception("Please enter a valid score.");
            }           
            repeat = false;
         } catch (InputMismatchException e){
            System.out.println("Invalid input. Rounds must be a double.");
            input.nextLine();
         } catch (Exception e) { 
            System.out.println(e.getMessage());
         }
      }
      return score;   
   }

   // method to read the input file 
   public static void readFile(TreeBag<Golfer> golfers) throws IOException{
      // variables used to store golfers' data
      String name;
      int rounds;
      double score;
      
      File inFile = new File("golferinfo.txt");  // file we are reading the data from 
       
      if(inFile.exists()){                       // if the file exists, read it and 
         Scanner read = new Scanner(inFile);     // store it's information in the variables  
         while(read.hasNext()){
            name = read.next();
            rounds = read.nextInt();
            score = read.nextDouble();
            
            Golfer golf = new Golfer(name, rounds, score);    // use the variables to create a new Golfer object 
            golfers.add(golf);                                // and add the Golfer object to the tree 
         }
         read.close();    // close the inFile
            
      } else {
         System.out.println("File does not exist.");  // just in case the file does not exist
      }      
   }
   
   // Method to update the data file once user exists
   public static void updateDatafile(TreeBag<Golfer> golfers) throws IOException{
      File outFile = new File("golferinfo.txt");      // creating the outFile
      PrintStream output = new PrintStream(outFile);
      PrintStream console = System.out;               
      System.setOut(output);                    // setting the PrintStream as the new standard output stream
      golfers.display();                        // calling display() method in TreeBag to print all golfers in alphabetical order
      System.setOut(console);                   // to catch the output and store it in a new file
   }   
  
   // Method to display all Golfer objects in alphabetical order 
   public static void displayAll(TreeBag<Golfer> golfers){
      golfers.display();      // it will print the golfers in alphabetical order by calling the display method
   }
   
   // Method to display all Golfer objects in tree in tree format 
   public static void displayTreeFormat(TreeBag<Golfer> golfers){
      // calls the displayAsTree method to print golfers in tree format 
      golfers.displayAsTree(); 
   }
   
   // Method to display one existing Golfer object's statistics 
   public static void displayStatsForOne(TreeBag<Golfer> golfers){
      Scanner input = new Scanner(System.in);
      System.out.println("What is the golfer's last name?");
      String name = input.next();
      Golfer golf = golfers.retrieve(new Golfer(name));     // creates a temp golfer object with the name to search and retrieve the object
      
      if(golf != null){
         System.out.println(golf.toString());               // if the golfer is in the tree, it will print their stats by calling the toString method
      } else {
         System.out.println("No golfer was found. Make sure name is capitalized and spelled correctly.");
      }
   }
   
   // Method to update an existing Golfer object's statistics
   public static void updateStats(TreeBag<Golfer> golfers){
      double score;
      Scanner input = new Scanner(System.in);
      System.out.println("What is the golfer's last name?");
      String name = input.next();                              // creates a new temporary golfer object with just the name in 
      Golfer golf = golfers.retrieve(new Golfer(name));        // order to search and retrieve it
      
      if(golf != null){             
         System.out.println("Golfer was found.\nPlease enter this round's score:");    // if the golfer exists
         score = insertScore();                                                        // method will call on the insertScore method to make sure valid score is entered
         golf.addScore(score);                                                         // and will add it to their score to get their new average score
         System.out.println("Golfer successfully updated.");
         
      } else {
         System.out.println("No golfer was found. Make sure name is capitalized and spelled correctly.");
      }
   }
   
   // Method will add a new Golfer object to the tree
   public static void addNewGolfer(TreeBag<Golfer> golfers){
      Scanner input = new Scanner(System.in);
      System.out.print("Please enter the following information for the new golfer.\nLast name: ");
         
      String last = input.next();                           // getting the stats of the new golfer object 
      System.out.print("Number of rounds played:");         // in order to create it 
      int rounds = insertRounds();
      System.out.print("Average score for player:");        // to get rounds and score, it will call on those methods to make sure input 
      double avgScore = insertScore();                         // is logical and will not crash the program
      
      Golfer newGolf = new Golfer(last, rounds, avgScore);                 // creating a new golfer object with provided values
      golfers.add(newGolf);                                                // and adding it to the tree
      System.out.println("Golfer " + last + " was added successfully.");
   }
   
   // Method will retrieve and remove a golfer if the Golfer exists   
   public static void removeGolfer(TreeBag<Golfer> golfers){
      Scanner input = new Scanner(System.in);     
      boolean done = false;
      System.out.println("Please enter the golfer's last name that you wish to remove.");
      String last = input.next();
      
      Golfer golf = golfers.retrieve(new Golfer(last));  // creates a new golfer object with only a last name 
                                                         // in order to search through the tree  
      if(golf != null){                                  // If the Golfer is found,
         done = golfers.remove(new Golfer(last));        // it will call the remove method and remove the actual Golfer in the tree
         
         // if remove returns false, then it did not remove the golfer
         if(done == false){
            System.out.println("There are no golfers with the last name: " + last + " that could be removed.\nPlease try again.");
         // otherwise, it was able to successfully remove the golfer from the tree
         } else {
            System.out.println("Golfer was successfully removed.");
         }    
      } else {
         System.out.println("There are no golfers with that last name. Please try again.");
      }   
   }
      
   // Method prints the main menu for the user 
   public static void printMenu(){
      System.out.println();
      System.out.println("Menu Choices:");
      System.out.println("1. Display listing to screen of all golfers stats (ordered by lastname).");    
      System.out.println("2. Display the golfers in current tree format (use displayAsTree).");     
      System.out.println("3. Find and display one individual golfers stats.");    
      System.out.println("4. Update an individual golfers stats (by adding an additional score).");  
      System.out.println("5. Add a new golfer to the Database.");    
      System.out.println("6. Remove a golfer from the Database.");    
      System.out.println("7. Quit and update datafile.");              
   }
}