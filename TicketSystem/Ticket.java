
public class Ticket implements Comparable<Ticket> {

    private int priority;
    private int seat;
    private int row;
    private String stand;
    private String name;
    private double price;

    public Ticket(String name, double price, int seat, int row, int priority){

        this.name = name;
        this.price = price;
        this.seat = seat;
        this.row = row;
        this.priority = priority;
        //this.stand = stand;

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

    @Override
    public String toString(){

        return(name + " " + "$" + price + " Seat: " + seat + " Row: " +  row + " Priority: " + priority);

    }

    @Override

    public int compareTo(Ticket other) {
        return Integer.compare(this.priority, other.priority);
    }
    public String fileString(){

        return(name + " " + price + " " + row + " " + seat + " " + priority);

    }

}
