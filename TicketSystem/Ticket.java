
public class Ticket {

    private int priority;
    private int seat;
    private String stand;
    private String name;
    private double price;

    public Ticket(String name, double price, String stand, int seat){

        this.name = name;
        this.price = price;
        this.seat = seat;
        this.stand = stand;

    }

    public int getPriority(){
        return priority;
    }

    public int getSeat(){
        return seat;

    }

    public String getStand(){
        return stand;
    }

    public String getName(){

        return name;
    }
    public double getPrice(){
        return price;
    }

    public void setPriority(int priority){

        this.priority = priority;
    }

}
