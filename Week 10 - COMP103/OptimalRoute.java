import java.io.File;
import java.util.*;
import ecs100.*;

public class OptimalRoute {

     private String baseName;
     private String startingBaseName;
     private String endingBaseName;



     private Base<String> headQuarters;
     private Set<Base<String>> allBases = new HashSet<>();
     private Set<Base<String>> visitedSet = new HashSet<>();
    Set<Base<String>> visitedSetRoute = new HashSet<>();

    private boolean set;

public void setupGUI(){

    UI.addTextField("Military Base Name", (String i )-> {this.baseName = i;});
    UI.addTextField("Starting Base Name", (String j )-> {this.startingBaseName = j;});
    UI.addTextField("Ending Base Name", (String k )-> {this.endingBaseName = k;});
    UI.addButton("Print all Base Stations", this::bases);
    UI.addButton("Find Base", () ->{findBase(baseName);});
    UI.addButton("Find Most Optimal Route" , this::optimalRoute);
}

public void bases(){

     set = UI.askBoolean("Would you like to use a set or boolean");

     if(set){
         processBases(headQuarters);
     }else{
         processBasesBoolean(headQuarters);
     }
}

public void processBases(Base<String> base){

    UI.println(base.toString());
    visitedSet.add(base);
    for(Base<String> b : base){
        if(!visitedSet.contains(b)){
            processBases(b);
        }
    }


}

public void processBasesBoolean(Base<String> base){
    base.visit();
    UI.println(base.toString());
    for(Base<String> b : base){
        if(!b.isVisited()){
            processBasesBoolean(b);
        }
    }

}

public void findBase(String baseName){
    for(Base<String> b : allBases){
        if(b.getType().equals(baseName)){
            UI.println(b.toString());
        }
    }
}

public void optimalRoute(){

    Base<String> startingBase = findBaseRoute(startingBaseName);
    Base<String> endingBase = findBaseRoute(endingBaseName);
    if(startingBase!=null && endingBase!=null) {
        findOptimalRoute(startingBase, endingBase);
    }else{
        UI.println("Base does not exist");
    }


}



public Base<String> findBaseRoute(String base){

    for(Base<String> b : allBases){
        if(b.getType().equals(base)){
            return b;
        }
    }

    return null;
}

public void findFinalOptimalRoute(Base<String> start, Base<String> destination){

    Map<Base<String>, Integer > distances = new HashMap<>();
    PriorityQueue<Base<String>> baseQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

    Map<Base<String>, Base<String>> previousBases = new HashMap<>();

    for(Base<String> b : allBases){
        distances.put(b, Integer.MAX_VALUE);
    }
    distances.put(start, 0);
    baseQueue.offer(start);



    while(!baseQueue.isEmpty()){
        Base<String> current = baseQueue.poll();

        if(current.equals(destination)){
            return;
        }

        for(Map.Entry<Base<String>, Integer> dist : current.getNeighbours().entrySet()){
              Base<String> b = dist.getKey();
              int distanceToNeighbour = dist.getValue();

              int newDistanceToNeighbour = distances.get(current) + distanceToNeighbour;

              if(newDistanceToNeighbour < distances.get(b)){
                  distances.put(b, newDistanceToNeighbour);
                  previousBases.put(b, current);
                  baseQueue.offer(b);
              }
        }
    }
    if (!previousBases.containsKey(destination)) {
        UI.println("No route found to destination.");
        return;
    }


}

public void findOptimalRoute1(Base<String> base1, Base<String> base2){
    int count = 0;
    double maxDistance = Double.MIN_VALUE;
    double minDistance = Double.MAX_VALUE;
    Base<String> minDistanceBase = null;


    Map<Base<String>, Integer> base1Map = new HashMap<>(base1.getNeighbours());
    Map<Base<String>, Integer> base2Map = new HashMap<>(base2.getNeighbours());

   for(Map.Entry<Base<String>, Integer> e : base1Map.entrySet()){
       Base<String> base = e.getKey();
       visitedSetRoute.add(base);
       int distance = e.getValue();
       //UI.println(base.getType() + " Distance: " + distance);

       if(distance < minDistance){
           minDistance = distance;
           visitedSetRoute.remove(base);
           minDistanceBase = base;
           visitedSetRoute.add(minDistanceBase);
       }

       count = count + (int)minDistance;
       UI.println("Current base: " + base.getType() + ", Next Base: " + minDistanceBase.getType() + ", Total Distance: " + count);

       visitedSetRoute.remove(minDistanceBase);
   }

      visitedSet.add(base1);
    if(!visitedSet.contains(minDistanceBase)) {
        if(minDistanceBase != null) {
            findOptimalRoute1(minDistanceBase, base2);
        }
    }




}

    public void findOptimalRoute(Base<String> start, Base<String> destination) {
        // Priority Queue to select the base with the shortest known distance

        // Map to store the shortest known distance to each base from the start base
        Map<Base<String>, Integer> distances = new HashMap<>();
        PriorityQueue<Base<String>> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Map to track the previous base for the shortest path to each base
        Map<Base<String>, Base<String>> previousBases = new HashMap<>();

        // Initialize all distances to infinity, except for the start base
        for (Base<String> base : allBases) {
            distances.put(base, Integer.MAX_VALUE);
        }
        distances.put(start, 0);  // Distance to start base is 0
        queue.offer(start);

        while (!queue.isEmpty()) {
            // Get the base with the shortest known distance
            Base<String> current = queue.poll();

            // Stop if we have reached the destination
            if (current.equals(destination)) {
                break;
            }

            // Process each neighbor of the current base
            for (Map.Entry<Base<String>, Integer> entry : current.getNeighbours().entrySet()) {
                Base<String> neighbor = entry.getKey();
                int distanceToNeighbor = entry.getValue();

                // Calculate the new distance to this neighbor
                int newDistance = distances.get(current) + distanceToNeighbor;

                // If a shorter path to this neighbor is found, update its distance and add it to the queue
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previousBases.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        // If the destination base is unreachable, print an error
        if (!previousBases.containsKey(destination)) {
            UI.println("No route found to destination.");
            return;
        }

        // Reconstruct the path from the destination back to the start
        List<Base<String>> path = new ArrayList<>();
        for (Base<String> at = destination; at != null; at = previousBases.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);  // Reverse the path to get it from start to destination

        // Print out the path and the total distance
        UI.println("Optimal Route: ");
        for (Base<String> base : path) {
            UI.println(base.getType() + " -> ");
        }
        UI.println("Total Distance: " + distances.get(destination));
    }


public static void loadImage(){
    String filePath = "data/bases.png";
    UI.drawImage(filePath, 50,50);
}

public void loadBases(){

    headQuarters = new Base<String>("HeadQuarters" , 200);
    Base<String> eliteBase = new Base<>("Elite Base" , 100);
    Base<String> goldBase = new Base<>("Gold Base" , 225);
    Base<String> silverBase = new Base<>("Silver Base" , 500);
    Base<String> bronzeBase = new Base<>("Bronze Base" , 2500);
    Base<String> reserveBase = new Base<>("Reserve Base" , 5000);

    allBases.add(headQuarters);
    allBases.add(eliteBase);
    allBases.add(goldBase);
    allBases.add(silverBase);
    allBases.add(bronzeBase);
    allBases.add(reserveBase);



    headQuarters.addNeighbours(eliteBase , 25);
    headQuarters.addNeighbours(goldBase , 9);
    headQuarters.addNeighbours(silverBase , 20);
    headQuarters.addNeighbours(bronzeBase , 50);
    headQuarters.addNeighbours(reserveBase , 5);

    eliteBase.addNeighbours(headQuarters, 25);
    eliteBase.addNeighbours(goldBase , 10);

    goldBase.addNeighbours(silverBase, 4);
    goldBase.addNeighbours(eliteBase, 10);
    goldBase.addNeighbours(headQuarters, 9);


    silverBase.addNeighbours(goldBase, 4);
    silverBase.addNeighbours(bronzeBase, 10);
    silverBase.addNeighbours(headQuarters, 20);

    bronzeBase.addNeighbours(reserveBase, 25);
    bronzeBase.addNeighbours(silverBase, 10);
    bronzeBase.addNeighbours(headQuarters, 50);


    reserveBase.addNeighbours(headQuarters, 15);
    reserveBase.addNeighbours(bronzeBase, 25);



    //eliteBase.addNeighbours(headQuarters, 25);
   // goldBase.addNeighbours(headQuarters, 30);  // Reverse connection
  //  silverBase.addNeighbours(headQuarters, 20);  // Reverse connection
  //  bronzeBase.addNeighbours(headQuarters, 50);  // Reverse connection

}

public static void main(String[] args){
    OptimalRoute optimalRoute = new OptimalRoute();
    optimalRoute.loadImage();
    optimalRoute.loadBases();
    optimalRoute.setupGUI();
}

}
