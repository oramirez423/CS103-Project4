/**************************************************************************************
* Omar Ramirez
* CSC 103 Project 4: Golfer Scores Database using a Binary Search Tree
* TreeBag allows us to create a Binary Search Tree that will store the Golfer objects.
***************************************************************************************/
// File: TreeBag.java 

// The implementation of most methods in this file is left as a student
// exercise from Section 9.5 of "Data Structures and Other Objects Using Java"


/******************************************************************************
* This class is a homework assignment;
* An <CODE>TreeBag</CODE> is a collection of int numbers.
*
* <dl><dt><b>Limitations:</b> <dd>
*   Beyond <CODE>Integer.MAX_VALUE</CODE> elements, <CODE>countOccurrences</CODE>,
*   and <CODE>size</CODE> are wrong. 
*
* <dt><b>Note:</b><dd>
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @version
*   Jan 24, 2016
******************************************************************************/
public class TreeBag<E extends Comparable> implements Cloneable
{
   // The Term E extends Comparable is letting the compiler know that any type
   // used to instantiate E must implement Comparable. i. e. that means that whatever
   // type E is must have a compareTo method so that elements can be compared against one another
   // This is required becuase we are doing comparisons in our methods


   // Invariant of the TreeBag class:
   //   1. The elements in the bag are stored in a binary search tree.
   //   2. The instance variable root is a reference to the root of the
   //      binary search tree (or null for an empty tree).
   private BTNode<E> root;  
   
   /**
   * Insert a new element into this bag.
   * @param <CODE>element</CODE>
   *   the new element that is being inserted
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to this bag.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory a new BTNode.
   **/
   public void add(E element) {      
      // when the first element is being added, it will be the root value
      if(root == null){                               
         root = new BTNode<E>(element, null, null);
      // if this is not the first element
      // there will be a cursor set to the root to begin and will traverse the tree
      // based on the value of the element 
      } else {
         boolean done = false;   // boolean value to stop searching when the new element is added to the tree
         BTNode<E> cursor = root;
         BTNode<E> value = new BTNode<E>(element, null, null); // creating a new BTNode with the element's value
         
         while(done == false){
            if(element.compareTo(cursor.getData()) <= 0){   // compareTo method from Golfer class to determine  
               if(cursor.getLeft() == null){                // whether the element is larger or smaller than the element in the tree.
                  cursor.setLeft(value);        // once the link to an element is null
                  done = true;                  // it will add the element to the tree
               } else {
                  cursor = cursor.getLeft();    // if the link is not null, it will continue searching down the tree
               }                                // based on it's values
         
            } else {
               if(cursor.getRight() == null){   //doing the same thing as on top but this is in the case where the element 
                  cursor.setRight(value);       //is larger than the current element in the tree
                  done = true;
               } else {
                  cursor = cursor.getRight();
               }
            }
         }// end of while loop
      }
   }// end of add method

   /**
   * Retrieve location of a specified element from this bag.
   * @param <CODE>target</CODE>
   *   the element to locate in the bag
   * @return 
   *  the return value is a reference to the found element in the tree
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then method returns
   *   a reference to a comparable element. If the target was not found then
   *   the method returns null.
   *   The bag remains unchanged.
   **/
   public E retrieve(E target) {
      // only try to find the target if root is not null
      if(root != null){
         boolean done = false;
         // create a cursor node that will allow us to traverse the tree
         BTNode<E> cursor = root;
         
         // while loop to traverse the tree
         while(done == false && cursor != null){
            if(target.compareTo(cursor.getData()) < 0){           // determines which direction we have to go based  
               cursor = cursor.getLeft();                         // on the values of the target
            } else if(target.compareTo(cursor.getData()) > 0){
               cursor = cursor.getRight();
            } else if(target.compareTo(cursor.getData()) == 0){   // if compareTo returns 0, then the node is found
               done = true;                                       // and method will return a refernce to the element 
               return cursor.getData();
            }
         }// end of while loop
         
         // will return if cursor is null
         return null;
      
      // if root is null, then return null
      } else {
         return null;
      }            
   }
   
   /**
   * Remove one copy of a specified element from this bag.
   * @param <CODE>target</CODE>
   *   the element to remove from the bag
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then one copy of
   *   <CODE>target</CODE> has been removed and the method returns true. 
   *   Otherwise the bag remains unchanged and the method returns false. 
   **/
   public boolean remove(E target) {
      // local BTNode variables
      // initializing the cursor to root and parentOfCursor to null
      BTNode<E> cursor = root;
      BTNode<E> parentOfCursor = null;
      boolean done = false;
      
      // while loop that allows us to move the cursor down the tree
      // until the cursor either reaches the target or becomes null
      while(done == false && cursor != null){
         if(target.compareTo(cursor.getData()) < 0){
            parentOfCursor = cursor;                                       // if statements help move the cursor down appropriately 
            cursor = cursor.getLeft();                                     // based on whether the target is larger or smaller than 
                                                                           // the cursor
         } else if (target.compareTo(cursor.getData()) > 0){
            parentOfCursor = cursor;                                       
            cursor = cursor.getRight();
         
         } else if (target.compareTo(cursor.getData()) == 0){        // indicates that the target has been found so the loop can stop  
            done = true;
         }
      }// end of while loop 
      
      // indicates the target is not in the tree, so we return false
      if(cursor == null){
         return false;
      }
      
      // in the case where the cursor is at the root of the tree
      // but it has no left child
      if(cursor == root && cursor.getLeft() == null){
         root = root.getRight();                         // get rid of the root by making the right child the new root
         return true;
      }
      
      // in the case where the cursor is farther down the tree
      // but it still has no left child 
      if(cursor.getLeft() == null){
         if(cursor == parentOfCursor.getLeft()){
            // the cursor is on the left side of the parent
            // so we then change parent's left link
            parentOfCursor.setLeft(cursor.getRight());
         } else {
            // the cursor is on the right side of the parent
            // so we change parent's right link
            parentOfCursor.setRight(cursor.getRight());
         }
         return true;
      }
      
      // the cursor is non-null and we have a left child
      if(cursor.getLeft() != null){
         // set the cursor to the right data point because it is the largest element in the cursor's left subtree
         // and this way we get rid of the data point
         cursor.setData(cursor.getLeft().getRightmostData());
         // to get rid of the duplicate node since we have the largest element in the left subtree now as the parent 
         cursor.setLeft(cursor.getLeft().removeRightmost());
         return true;
      }
      
      return false; 
   }// end of remove method
   
   /**
   * Displays the entire tree of Node elements in a order specified
   * by the elements compareTo method
   * 
   * @param 
   *   none
   * <dt><b>Postcondition:</b><dd>
   *   Outputs all elements in the tree to Screen.
   *   Does not change the structure 
   **/
   public void display() {
      // will tell user there are no elements in tree if the root is null
      if(root == null){       
         System.out.println("There are no elements in this tree.");
      
      // using inorderPrint since we're trying to print the golfer's in alphabetical order
      } else {
         root.inorderPrint();
      }     
   } 
     
   /**
   * Displays the entire tree of Node elements using the
   * built in print method of BTNode
   * which displays the entire tree in tree format
   * 
   * @param 
   *   none
   * <dt><b>Postcondition:</b><dd>
   *   Outputs all elements in the tree to Screen.
   *   Does not change the structure 
   **/   
   public void displayAsTree() {
      if (root == null)
         throw new IllegalArgumentException("The tree is empty");
      root.print(0);
   }
      
   
   
   /**
   * Generate a copy of this bag.
   * @param - none
   * @return
   *   The return value is a copy of this bag. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an <CODE>TreeBag</CODE> before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public TreeBag<E> clone( )
   {  // Clone an IntTreeBag object.
      // Student will replace this return statement with their own code:
      return null; 
   } 

   /**
   * Accessor method to count the number of occurrences of a particular element
   * in this bag.
   * @param <CODE>target</CODE>
   *   the element that needs to be counted
   * @return
   *   the number of times that <CODE>target</CODE> occurs in this bag
   **/
   public int countOccurrences(E target)
   {
      // Student will replace this return statement with their own code:
      return 0;
   }
   
       
   /**
   * Determine the number of elements in this bag.
   * @param - none
   * @return
   *   the number of elements in this bag
   **/                           
   public int size( )
   {
      return BTNode.treeSize(root);
   }

   /**
   * Add the contents of another bag to this bag.
   * @param <CODE>addend</CODE>
   *   a bag whose contents will be added to this bag
   * <dt><b>Precondition:</b><dd>
   *   The parameter, <CODE>addend</CODE>, is not null.
   * <dt><b>Postcondition:</b><dd>
   *   The elements from <CODE>addend</CODE> have been added to this bag.
   * @exception IllegalArgumentException
   *   Indicates that <CODE>addend</CODE> is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of the bag.
   **/
   public void addAll(TreeBag<E> addend)
   {
      // Implemented by student.
   }
   
   /**
   * Create a new bag that contains all the elements from two other bags.
   * @param <CODE>b1</CODE>
   *   the first of two bags
   * @param <CODE>b2</CODE>
   *   the second of two bags
   * <dt><b>Precondition:</b><dd>
   *   Neither b1 nor b2 is null.
   * @return
   *   the union of b1 and b2
   * @exception IllegalArgumentException
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new bag.
   **/   
   public static TreeBag union(TreeBag b1, TreeBag b2)
   {
      // Student will replace this return statement with their own code:
      return null;
   }
      
}
           