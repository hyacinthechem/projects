import ecs100.*;
import java.awt.*;
import java.util.*;
/**
 * Write a description of class Connect here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Grids
{
    
   private long finalTime;
   private double x;
   
   public void setupGUI(){
       UI.addButton("Draw Grids", this::enter);
    }
    
   public void enter(){
    
    double x = UI.askDouble("X: ");
    double y = UI.askDouble("Y: ");
    int n = UI.askInt("Grid Size: ");
    
    drawGridsLoop(x,y,n);
    
    UI.println("Time it took:  " + finalTime);
    
    for(int i = 0; i<10; i++){
    
     UI.println("            ");
     
    
    }
    
    UI.clearGraphics();
    
    UI.askString("Do you want to continue: ");
    
    drawGridsRecurse(x,y,n);
    
    
    UI.println("Time it took:  " + finalTime);

    
    
    
    
    }

    
    public void drawRect(double x, double y, int width){
    
    UI.setColor(Color.blue);
    UI.fillRect(x,y,width,width);
    
    
   }
    
   
   public void drawGridsLoop(double x, double y, int n){
    long startTime = System.currentTimeMillis();
    for(int i=0; i<n; i++){
    
        this.drawRect(x,y,15);
        y = y - 20;
        
    
    }
    
    long endTime = System.currentTimeMillis();
    
    finalTime = (endTime - startTime);

    }
    
   public void drawGridsRecurse(double x, double y, int n){
       long startTime = System.currentTimeMillis();

       this.drawRect(x,y,15);
       
       if(n>1){
        
        this.drawGridsRecurse(x,y-20,n-1);
        
        
        }
    
       long endTime = System.currentTimeMillis();
       
       finalTime = (endTime - startTime);
    
   }
    
   public static void main(String[] args){
    
        Grids g1 = new Grids();
        g1.setupGUI();
    
    }
}
