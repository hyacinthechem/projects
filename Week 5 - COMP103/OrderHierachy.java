import java.util.PriorityQueue;
import ecs100.*;
import java.util.*;
import java.util.Map;
import java.util.TreeMap;
import ecs100.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Write a description of class OrderHierachy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class OrderHierachy
{
    private PriorityQueue<Restaurant> orderQueue;
    
    
    public OrderHierachy(Comparator<Restaurant> comparator){
    
      orderQueue = new PriorityQueue<>(comparator);
    
    }
    
    public void addOrder(Restaurant r){
    
     orderQueue.offer(r);
    
    }
    
    public Restaurant getRestaurant(){
    
    
    return orderQueue.poll();
    
    }
    
    public List<Restaurant> getList(){
    
     return new ArrayList<>(orderQueue);
    }
    
    public void clearQueue(){
    
         orderQueue.clear();
    
    }
    
    public boolean isEmpty(){
    
    return orderQueue.isEmpty();
    
    }
    
    public void addAll(List<Restaurant> r){
    
    orderQueue.addAll(r);
    
    }
    
}
