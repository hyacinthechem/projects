import ecs100.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CombinationsRevised {


    private double bestTotal;
    private List<Packet> BestCombination;



    public void findCombination(double target, List<Packet> packets) {

        UI.println("======= Recursive ======== ");
        UI.println("packets: "+packets);
        BestCombination = new ArrayList<Packet>();
        bestTotal = 0;
        findCombinations(target, packets, 0, new Stack<Packet>(), 0);
        UI.printf("Best: %7.4f(/%5.2f): %s\n", bestTotal, target, BestCombination);


    }

    public void findCombinations(double target, List<Packet> packets, int index, Stack<Packet> combinationSoFar, double totalSoFar){
        if (index >=packets.size()) { return;}
        double newTotal = totalSoFar + packets.get(index).getWeight();
        if (newTotal <= target){
            combinationSoFar.push(packets.get(index));
            if (newTotal > bestTotal){
                bestTotal = newTotal;
                BestCombination = new ArrayList<Packet>(combinationSoFar);
            }
            findCombinations(target, packets, index+1, combinationSoFar, newTotal);
            combinationSoFar.pop();
        }
        findCombinations(target, packets, index+1, combinationSoFar, totalSoFar);

    }



    public List<Packet> makeList(int size){
        List<Packet> ans = new ArrayList<>();
        for(int i = 0; i < size; i++){
            ans.add(new Packet());
        }
        return ans;

    }

    public void setupGUI(){

        UI.addButton("Find Best Combination", () -> { int size = UI.askInt("Size"); double target = size;
        findCombination(target , makeList(size));
        });


    }

    public static void main(String[] args) {

        CombinationsRevised app = new CombinationsRevised();
        app.setupGUI();


    }


}
