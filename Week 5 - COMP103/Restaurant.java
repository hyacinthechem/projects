
/**
 * Write a description of class Restaurant here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Restaurant
{
    private double orderNumber;
    private String order;
    private int numberOfClients;
    private int tableNumber;
    
    private String menuType;
    
    
    
    public Restaurant(String order, double orderNumber, int numberOfClients){
    
    this.order = order;
    this.orderNumber = orderNumber;
    this.numberOfClients = numberOfClients;
    
    
    }
    
    
    public String orderInformation(){
    
       String ordered = this.menuType;
       
       int tableNumber = this.tableNumber;
       
       return ("Menu type: " +  ordered + "Clients at table:   "  + "Table Number: " + tableNumber );
    
    }
    
    public double get0rderNumber(){
    
         return this.orderNumber;
    
    
    }
    
    public int getNumberOfClients(){
    
         return numberOfClients;
    
    }
    
    public String getOrder(){
    
          return order;
    }
    
    public int getTableNumber(){
    
    
    return tableNumber;
    
    }
    
    public double getOrderNumber(){
    
    
    return orderNumber;
    }
    
    //@Override
    
    public boolean equals(Object other){
    
       if(other instanceof Restaurant){
        
          return this.order.equals(((Restaurant) other).getOrder());
        
        }else{
        
            return false;
        
        }
       
        
        
    }
    
    
    
    @Override
    
    public String toString(){
    
      return ("Order:  " + "   " + order + "   " + "Number #:  " + " " + orderNumber + " " + "Number at Table " + " " + numberOfClients );
    
    }
    
}

