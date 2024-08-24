// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 - 2024T2, Assignment 4
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;

/** 
 *  Compute all permutations of a list of Strings
 *
 *  You only have to write one method - the extendPermutations(...) method
 *  which does the recursive search.  
 */
public class Permutations {
   
    private List<List<String>> permutations;
    private String s;
    
    
    /**
     * Constructs a list of all permutations of the given items
     * by calling a recursive method, passing in a set of the items to permute
     * and an empty list to build up.
     * Prints the total number of permutations in the message window (with
     *  UI.printMessage(...);
     */
    public List<List<String>> findPermutations(Set<String> items){

        Set<String> copyOfItems = new HashSet<String>(items);   // a copy of the set of items that can be modified
        List<List<String>> ans = new ArrayList<List<String>>(); // where we will collect the answer
        counter=0;
        //suggested approach:
        extendPermutation(copyOfItems, new Stack<String>(), ans);   
        return ans;
    }

    /**
     * Recursive method to build all permutations possible by adding the
     *  remaining items on to the end of the permutation built up so far 
     * If there are no remaining items, then permutationSoFar is complete,
     *   => add a copy of the permutation to allPermutations.
     * Otherwise,
     *  for each of the remaining items,
     *     extend the permutationSoFar with the item, and do a recursive call to extend it more:
     *     - remove the item from remaining items and
     *     - push it onto the permutation so far,
     *     - do the recursive call,
     *     - pop the item from the permutation so far and
     *     - put it back into the remaining items.
     *
     * So that you don't run out of memory, only add the first 10000 permutations to the allPermutations.
     */
    public void extendPermutation(Set<String> remainingItems, Stack<String> permutationSoFar, List<List<String>> allPermutations){
        Set<String> items = new HashSet<>(remainingItems);  //avoid concurrent error, add all of remainingitems to new hashset
        if(!remainingItems.isEmpty()){ //base case
                for(String item : items){   
                remainingItems.remove(item);  //remove the string
                permutationSoFar.push(item);    //push the removed set string to permutationSoFar stack
                extendPermutation(remainingItems,permutationSoFar,allPermutations); //recursive call
                permutationSoFar.pop(); 
                remainingItems.add(item);
                
                if(counter>=10000){
                   return;               // to not run out of memory
                }
            }        
        }else{
           allPermutations.add(new ArrayList<>(permutationSoFar)); //copy of permutationSoFar to allPermutations
           counter++;
           if(counter>=10000){
              return;   // to not run out of memory
            }     
        }
        
    }

    //===================================================
    // User Interface code

    /**
     * Setup GUI
     * Buttons to run permutations on either letters or words
     */
    public void setupGUI(){
        //UI.addButton("A B C D E", ()->{printAll(findPermutations(Set.of("A","B","C","D","E")));});
        UI.addButton("A B C D E", this::useABCDE);
        //UI.addTextField("LettersECS", (String v)->{printAll(findPermutations(makeSetOfLetters(v)));});
        UI.addTextField("Letters", (String s) -> {this.s = s;});
         UI.addButton("Letters", () -> {useLetters(s);} );
        //UI.addButton("Word", this::findPermutations(makeSetOfLetters(v)));
        UI.addTextField("Words", (String s) -> {this.s = s;});  //assign string from textfield to classfield
        UI.addButton("Word", () ->{ useWords(s);});
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0);
    }
    
    public void useABCDE(){
        UI.clearText();
        Set<String> letters = new HashSet<>(Arrays.asList("A","B","C","D","E"));
        permutations = findPermutations(letters);
        printAll(permutations);        
    }
    
    public void useLetters(String s){
        UI.clearText();
        Set<String> letters = makeSetOfLetters(this.s); //use pre written method to return set of letters
        permutations = findPermutations(letters);
        printAll(permutations);   
        
    }
    
    public void useWords(String s){
        UI.clearText();
        Set<String> word = makeSetOfWords(this.s);   //use pre written method to return set of words
        permutations = findPermutations(word);       //initialise permutations list in field and get returned list from method
        printAll(permutations);
    }
    
    public void displayPermutations(){
    
    if(permutations!=null & !permutations.isEmpty()){
    for(List<String> p : permutations){
        UI.println(p);
        }
    }
    
    
    }

    public void printAll(List<List<String>> permutations){
        UI.clearText();
        for (int i=0; i<permutations.size(); i++){
            for (String str : permutations.get(i)){UI.print(str+" ");}
            UI.println();
        }
        UI.println("----------------------");
        UI.printf("%d items:\n", permutations.get(0).size());
        UI.printf("%,d permutations:\n", counter);
        UI.println("----------------------");
    }

    /**
     * Makes a set of strings, one string for each character in the argument
     */
    public Set<String> makeSetOfLetters(String str){
        Set<String> ans = new HashSet<String>();
        for (int i=0; i<str.length(); i++){
            if (str.charAt(i)!=' '){
                ans.add(""+str.charAt(i));
            }
        }
        return Collections.unmodifiableSet(ans);
    }

    /**
     * Makes a set of strings, one string for each word in the argument
     */
    public Set<String> makeSetOfWords(String str){
        Set<String> ans = new HashSet<String>();
        for (String v : str.split(" ")){ans.add(v);}
        return Collections.unmodifiableSet(ans);
    }

    // Counter for the number of complete permutations found
    private long counter = 0;  

    /** Report the value of counter in the message area */
    public void reportCounter(){
        if ((counter<<54)==0) {UI.printMessage((counter>10000000)?((counter>>>20)+"M"):((counter>>>10)+"K"));}
    }

    
    // Main
    public static void main(String[] arguments) {
        Permutations p = new Permutations();
        p.setupGUI();
    }
}
