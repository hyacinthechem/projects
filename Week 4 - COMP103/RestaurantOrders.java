

import java.util.Map;
import java.util.TreeMap;
import ecs100.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Write a description of class RestaurantOrders here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RestaurantOrders
{
    // use a set to store orders so that you don't have any duplicates
    
    Set<Restaurant> orders = new HashSet<>();
    
public void setupGUI(){
    
      UI.addButton("View all orders", this:: viewOrders );
      UI.addButton("Finished" , this:: finishedOrders);
      
    
    }
    
    

    public void loadOrderData(){
    
    try{
        
        String filename = "data/restaurant_clients-3.txt";
        
        Scanner sc = new Scanner(Path.of(filename));
        
        while(sc.hasNext()){
        
          String order = sc.next();
          double orderNumber = sc.nextDouble();
          int numberOfClients = sc.nextInt();
           
          Restaurant r = new Restaurant(order,orderNumber,numberOfClients);
          
          orders.add(r);
          
        
        }
        
        
    
    
    }catch(IOException e){UI.println("File Failure" + e);}
}

public void viewOrders(){
    loadOrderData();
    for(Restaurant r : orders){
    
       UI.println(r.toString());
    
    }


}

public void finishedOrders(){



}


public static void main(String [] args){
    
       RestaurantOrders r1 = new RestaurantOrders();
       
       r1.setupGUI();
    
    }
}

