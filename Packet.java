import ecs100.*;
import java.util.*;
import java.io.*;


/** A Packet to be put in a pallet.
 *  Contains an ID and a weight (between 100gm and 5kg)
 */

public class Packet{
    private static long nextID = 1;

    private long ID;
    private double weight;

    /** Construct a new Packet object with random weight up to 5kg */
    public Packet(){
        ID = nextID++;
        weight = 2 + Math.random()*5;
    }

    /** Construct a new Packet object with specified weight */
    public Packet(double wt){
        ID = nextID++;
        weight = wt;
    }


    public double getWeight(){ return weight; }

    /**
     * Natural sorting order is by weight 
     */
    public int compareTo(Packet other){
        if (this.weight<other.weight) return -1;
        if (this.weight>other.weight) return 1;
        return 0;
    }

    public String toString(){
        return String.format("%.2f", weight);
    }
    
    public static void main(String[] arguments){
       Packet obj = new Packet();
    }	


}
