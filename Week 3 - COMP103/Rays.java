import ecs100.*;
import java.util.*;
/**
 * Write a description of class Rays here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rays
{
    private int[] ages = { 35,26,43,15,19,5 };
    
    private List<Integer> ageGroup = Arrays.asList(35,26,43,15,19,5);

    public void setupGUI(){
    
    UI.addButton("Display Ages", this::values);
    UI.addButton("Sorted Ages", this::sortedAge);
    UI.addButton("Sorted Age Groups", this::sortedAgeGroup);
    
    }
    
    public void values(){
    
    
    
     UI.println(ages);
     
     
    
    }
    
    public void sortedAge(){
      
      Arrays.sort(ages);

    
      System.out.println(Arrays.toString(ages));
    }
    
    public void sortedAgeGroup(){
    
      Collections.sort(ageGroup);
      
      System.out.println(ageGroup);
    
    
    }
    
      public static void main(String[] args) {
        Rays r1 = new Rays();
        r1.setupGUI();
        
        }
}
