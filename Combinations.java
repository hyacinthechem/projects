import ecs100.*;
import java.util.*;
import java.io.*;

/** Combinations:
 */

public class Combinations{

    private List<Packet> bestCombination;  // fields used during recursive calls
    private double bestTotal;              // to hold the best so far

    /**
     * Given
     *  a set of packets of different weights, and
     *  a pallet with a target weight
     * Find the selection of packets that has the maximum total
     * weight that is not over the target
     */
    public void findCombinations(double target, List<Packet> packets){
        UI.println("======= Recursive ======== ");
        UI.println("packets: "+packets);
        bestCombination = new ArrayList<Packet>();
        bestTotal = 0;
        findCombinations(target, packets, 0, new Stack<Packet>(), 0);
        UI.printf("Best: %7.4f(/%5.2f): %s\n", bestTotal, target, bestCombination);
    }

    /**
     * Try all ways of adding packets from index onwards to combinationSoFar
     * by trying the two options of adding the packet at index or not,
     * and then recursing from index+1.
     * Stops when no more packets to add
     */
    public void findCombinations(double target, List<Packet> packets, int index, Stack<Packet> combinationSoFar, double totalSoFar){
       if (index >=packets.size()) { return;}
        double newTotal = totalSoFar + packets.get(index).getWeight();
        if (newTotal <= target){
            combinationSoFar.push(packets.get(index));
            if (newTotal > bestTotal){
                bestTotal = newTotal;
                bestCombination = new ArrayList<Packet>(combinationSoFar);
            }
            findCombinations(target, packets, index+1, combinationSoFar, newTotal);
            combinationSoFar.pop();
        }
        findCombinations(target, packets, index+1, combinationSoFar, totalSoFar);
    
    }

    /**
     * Use a binary number to represent the combination and
     * step through numbers to step through all combinations
     */
    public void findCombinations2(double target, List<Packet> packets){
        UI.println("======= Coded in bits ======== ");
        UI.println("packets: "+packets);
        long bestCombination = 0;
        double bestTotal = 0;
        long max = 1;
        for (int i=0; i<packets.size(); i++){
            
            max*=2;
        } 
        max--;
        for (long combination=1; combination<max; combination++){
            // add up weight
            double total = 0;
            long x=combination;
            for (int i=0; i<packets.size(); i++){
                if (x%2==1){total += packets.get(i).getWeight();}
                x = x/2;
            }
            if (total <= target && total > bestTotal){
                bestTotal = total;
                bestCombination = combination;
            }
        }
        long x = bestCombination;
        List<Packet> bestPackets = new ArrayList<Packet>(); 
        for (int i=0; i<packets.size(); i++){
            if (x%2==1){bestPackets.add(packets.get(i));}
            x = x/2;
        }
        printBitCombination(bestCombination, bestTotal, target, packets);
    }

    
    public void printBitCombination(long bits, double total, double target, List<Packet> packets){
        List<Packet> combination = new ArrayList<Packet>(); 
        for (int i=0; i<packets.size(); i++){
            if (bits%2==1){combination.add(packets.get(i));}
            bits = bits/2;
        }
        UI.printf("Best: %7.4f(/%5.2f): %s\n", total, target, combination);
    }

    /**
     * Initialise the interface
     */
    public void setupGUI(){
        UI.addButton("Combinations(Rec)", ()->{
                int size = UI.askInt("size");
                double target = size*(1.8+Math.random()*.4);
                findCombinations(target, makeList(size));
                UI.println("========");
            });
        UI.addButton("Combinations(Bit))", ()->{
                int size = UI.askInt("size");
                double target = size*(1.8+Math.random()*.4);
                findCombinations2(target, makeList(size));
                UI.println("========");
            });
        UI.addButton("Clear", UI::clearText);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0);
    }

    public List<Packet> makeList(int size){
        List<Packet> ans = new ArrayList<Packet>();
        for (int i=0; i<size; i++){
            ans.add(new Packet());
        }
        return ans;
    }

    public static void main(String[] arguments){
        Combinations c = new Combinations();
        c.setupGUI();
    }   

}
