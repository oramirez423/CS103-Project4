/**********************************************************************************************
* Omar Ramirez
* CSC 103 Project 4: Golfer Scores Database using a Binary Search Tree
*
* The class Golfer allows for the creation of a Golfer object. It can:
*     1. Set and get the values of the golfer's lastName, numberOfRounds, and averageScore. 
*     2. Allowing the addition of a new score to get averaged into their current score.
*     3. Comparing the current Golfer object with another Golfer object by their last name.
*     4. Printing out a Golfer object's instance variables.
*
***********************************************************************************************/
import java.util.*;

public class Golfer implements Comparable<Golfer>{

   //Invariant of the Golfer class:
   //   1. The last name of the golfer will be stored in the instance variable lastName.
   //   2. The total rounds a golfer has played will be stored in the instance variable numberOfRounds.
   //   3. The golfer's average score will be stored in the instance variable averageScore.
   private String lastName;
   private int numberOfRounds;
   private double averageScore;
   
   /**
   * Initialize a Golfer object with given inputted value for
   * the last name in order to use the retrieve and remove method 
   * in the TreeBag class. 
   * <dt><b>Param-name: </b><dd>
   *   The last name of the golfer.
   * <dt><b>Postcondition:</b><dd>
   *   New Golfer object has been created with the last name
   *   variable set to the passed in value. Other private instance 
   *   variables will be set to 0. 
   **/  
   public Golfer(String name){      // constructor used for displaying and removing a value
      this(name, 0, 0); 
   }// end of Golfer constructor
   
   /**
   * Initialize a Golfer object with given inputted value for
   * the last name, number of rounds, and score of Golfer object.
   * <dt><b>Param-name, rounds, score: </b><dd>
   *   The last name of the golfer, the total rounds the golfer has played
   *   and the average score of the golfer.
   * <dt><b>Postcondition:</b><dd>
   *   New Golfer object has been created with the last name, total rounds, 
   *   and average score variables set to the passed in values. 
   **/  
   public Golfer(String name, int rounds, double score){
      lastName = name;
      numberOfRounds = rounds;      
      averageScore = score;  
   }// end of Golfer constructor
   
   /**
   * Gets the value of the last name 
   * for the Golfer object
   * <dt><b>Param- none:</b><dd>
   * <dt><b>Postcondition:</b><dd>
   *   The last name for the Golfer object has been
   *   returned. 
   **/  
   public String getName(){
      // returning the last name of Golfer object
      return lastName;
   }
   
   /**
   * Gets the value of the rounds 
   * played for the Golfer object
   * <dt><b>Param- none:</b><dd>
   * <dt><b>Postcondition:</b><dd>
   *   The rounds played for the Golfer object has been
   *   returned. 
   **/  
   public int getRounds(){
      // returning the numberOfRounds of the Golfer object
      return numberOfRounds;
   }
   
   /**
   * Gets the value of the average scofre 
   * for the Golfer object
   * <dt><b>Param- none:</b><dd>
   * <dt><b>Postcondition:</b><dd>
   *   The average score for the Golfer object has been
   *   returned. 
   **/  
   public double getScore(){
      // returning the averageScore of the Golfer object
      return averageScore;
   }
   
   /**
   * Sets the last name of the Golfer object
   * <dt><b>Param-name:</b><dd>
   *  The last name that will be set for the Golfer object.
   * <dt><b>Postcondition:</b><dd>
   *   The last name for the Golfer object has been
   *   set to the given value. 
   **/    
   public void setName(String name){
      // setting/updating the last name of the Golfer object 
      lastName = name;
   }
   
   /**
   * Sets the total rounds of the Golfer object
   * <dt><b>Param-rounds:</b><dd>
   *  The total rounds that will be set for the Golfer object.
   * <dt><b>Postcondition:</b><dd>
   *   The total rounds for the Golfer object has been
   *   set to the given value. 
   **/  
   public void setRounds(int rounds){
      // setting/updating the number of rounds of the Golfer object
      numberOfRounds = rounds;
   }
   
   /**
   * Sets the average score of the Golfer object
   * <dt><b>Param-score:</b><dd>
   *  The average score that will be set for the Golfer object.
   * <dt><b>Postcondition:</b><dd>
   *   The average score for the Golfer object has been
   *   set to the given value. 
   **/  
   public void setScore(double score){
      // setting/updating the averageScore of the Golfer object.
      averageScore = score;
   }
   
   /**
   * Updates the numberOfRounds and averageScore values of the Golfer after
   * receiving a passed in score which will be the new score after a round
   * has been played.  
   * <dt><b>Param-score:</b><dd>
   *  The Golfer's latest score after playing a round.
   * <dt><b>Postcondition:</b><dd>
   *  The variables numberOfRounds and averageScore have been updated correctly.
   *  The passed in score will be added to the average of the golfer and the 
   *  number of rounds has increased by one.
   **/  
   public void addScore(double score){
      // assigning the passed in value a variable
      double newScore = score;
      
      // getting the number of shots to be averaged out
      double shots = numberOfRounds * averageScore;                             
      // updating the golfer's rounds
      numberOfRounds++;   
      // getting the new average score       
      double newAverage = (newScore + shots)/numberOfRounds;
      newAverage = newAverage*100;              // easy way to round the answer to 2 decimal places 
      newAverage = Math.round(newAverage);
      newAverage = newAverage/100;      
      // updating the golfer's average score
      averageScore = newAverage;
   }
    
   /**
   * Compares two Golfer objects by calling the compareTo method 
   * of Strings and receiving the result of an int which will determine
   * whether the passed in object is larger or smaller (based on their 
   * last name) than the current object.   
   * <dt><b>Param-obj:</b><dd>
   *    The Golfer object being passed in.
   * <dt><b>Returns:</b><dd>
   *    Returns an integer with its value determining whether the passsed in Golfer's
   *    last name is before or after the current Golfer's last name.
   * <dt><b>Postcondition:</b><dd>
   *  Returned an int that will determine whether the passed in value 
   *  is larger or smaller based on the value of the int.
   **/  
   public int compareTo(Golfer obj){
      int value = lastName.compareTo(obj.getName());  // calling the compareTo method on the Strings of the objects
      return value; 
   }
   
   /**
   * Creates a String giving the private instance values  
   * of the Golfer object, which include last name, number of rounds
   * played, and their average score. 
   * <dt><b>Param - none</b><dd>
   * <dt><b>Postcondition:</b><dd>
   *    The values of the Golfer object have been printed.
   **/ 
   public String toString(){
      // returning the Golfer's variables          
      return lastName + " " + numberOfRounds + " " + averageScore;   
   }
   
} // end of Golfer class