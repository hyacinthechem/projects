

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
    
   private List<Restaurant> orders = new ArrayList<>();
   private Queue<Restaurant> orderLine = new PriorityQueue<>();
   private String ordername;
   private Queue<Restaurant> orderLine2 = new ArrayDeque<>();
    
public void setupGUI(){
    
      UI.addButton("View all orders", this:: viewOrders );
      UI.addButton("Clear Orders" , this::clearOrders);
      UI.addButton("Order Queue" , this:: orderRank);
      UI.addTextField("Order ", (String ordername) -> { this.ordername = ordername;});
      UI.addButton("View Specific Order", () -> {specificOrder(this.ordername);});
      
    
    }
    
public void clearOrders(){
    
    this.orders.clear();
    UI.clearText();
    
    
    }
    
    

    public void loadOrderData(){
    
    try{
        
        String filename = "data/restaurant_clients-3 copy.txt";
        
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

public void specificOrder(String orderType){
 
 loadOrderData();
 Restaurant find = new Restaurant(orderType, 0, 0);
    
  //loop through restaurant objects and find each given order and see if they equal 
 // to each other by using the .equals method
 
 
for(Restaurant r : orders){
      if(r.equals(find)){
     UI.println(r.toString());
    }
    
    }


}

public void finishedOrders(){

    loadOrderData();
   
   for(int i = 0; i<10; i++){
    
       UI.println( "        " );
    
    }

       for(Restaurant r : orders){
           orderLine2.offer(r);
           UI.println(orderLine2);
        
    }
    

}

public void orderRank(){
   loadOrderData();
   
   Comparator<Restaurant> rankComparator = new Comparator<Restaurant>(){
      @Override
      public int compare(Restaurant r1, Restaurant r2){
        
        return Integer.compare(r2.getNumberOfClients(), r1.getNumberOfClients());
        
        }
        
    
    
    };
    
    OrderHierachy orderHierachy = new OrderHierachy(rankComparator);
    
    for(Restaurant r : orders){
    
         orderHierachy.addOrder(r);
    
    }
    
    

    //Collections.sort(orderHierachy.getQueue(), Collections.reverseOrder());
    
    while(!orderHierachy.isEmpty()){
    
        Restaurant r = orderHierachy.getRestaurant();
        UI.println(r.toString());
    
    
    }
    

}


public static void main(String [] args){
    
       RestaurantOrders r1 = new RestaurantOrders();
       
       r1.setupGUI();
    
    }
}

