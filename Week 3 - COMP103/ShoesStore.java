import ecs100.*;
import java.util.Set;
import java.util.*;
import java.nio.file.*;
import java.io.IOException;
import java.awt.Color;

/**
 * Write a description of class Boots here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ShoesStore
{
    private Set<Shoe> shoes;
    
    private Set<String> shoeword = new HashSet<>();
    
    
    public void setupGUI(){
    
        UI.addButton("Load Shoe Data", this::loadData);
        UI.addButton("Shoes in Stock", this::listShoesInStock);
        UI.addButton("Sorted Shoes", this::sortedShoes);
        
    
    }
    
    public void loadData(){
      
        try{
        List<String> shoelist = Files.readAllLines(Path.of("shoe_list.txt"));
        
        shoes = new HashSet<>();
        
        for(String line : shoelist){
          Scanner sc = new Scanner(line);
          String brand = sc.next();
          String fName = sc.next();
          String sName = sc.next();
          double size = sc.nextDouble();
          String stock = sc.next();
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          Color col = new Color(r,g,b);
          String model = fName + " " + sName;

          
          shoes.add(new Shoe(size,brand,model,stock,col));
          
          shoeword.add(brand + model + stock);
          
          
          
        
        
        }
        
        UI.println("Data Loaded Succesfully");
        
        
        
        }catch(IOException e){UI.println("File Failure" + e);}
        
    
    }
    
    public void listShoesInStock(){
        UI.clearText();
        
        /*
        for(Shoe s : shoes){
            UI.println();
            UI.println(s.toString());
        
        }
        
        */
       
        for(int i = 0; i<shoeword.size(); i++){
        
            UI.println(shoeword);
        
        }
    
    
    }
    
 
    
    public void sortedShoes(){
        UI.clearText();
        Set<String> sortedShoes = new TreeSet<>(shoeword);
        sortedShoes.addAll(shoeword);
        UI.println("Set (Ordered): " + sortedShoes);

     
    }
    
    public static void main(String[] arguments){
       
        ShoesStore s1 = new ShoesStore();
        s1.setupGUI();
    
    }
    
    
}
