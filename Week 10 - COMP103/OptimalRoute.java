import java.io.File;
import java.util.*;
import ecs100.*;

public class OptimalRoute {

     private String baseName;
     private Base<String> headQuarters;
     private Set<Base<String>> allBases = new HashSet<>();
     private Set<Base<String>> visitedSet = new HashSet<>();
     private boolean set;

public void setupGUI(){

    UI.addTextField("Military Base Name", (String s )-> {this.baseName = s;});
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
        if(b.getType().contains(baseName)){
            UI.println(b.toString());
        }
    }
}

public void optimalRoute(){

    findOptimalRoute(headQuarters);

}

public void findOptimalRoute(Base<String> base){


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
    headQuarters.addNeighbours(goldBase , 30);
    headQuarters.addNeighbours(silverBase , 20);
    headQuarters.addNeighbours(bronzeBase , 50);
    headQuarters.addNeighbours(reserveBase , 5);

    eliteBase.addNeighbours(goldBase , 10);

    goldBase.addNeighbours(silverBase, 4);
    goldBase.addNeighbours(eliteBase, 10);
    goldBase.addNeighbours(bronzeBase, 9);
    goldBase.addNeighbours(reserveBase, 25);


    silverBase.addNeighbours(bronzeBase, 10);
    silverBase.addNeighbours(reserveBase, 11);

    bronzeBase.addNeighbours(reserveBase, 23);

    reserveBase.addNeighbours(headQuarters, 15);
    reserveBase.addNeighbours(bronzeBase, 25);
    reserveBase.addNeighbours(goldBase, 30);


    eliteBase.addNeighbours(headQuarters, 25);
    goldBase.addNeighbours(headQuarters, 30);  // Reverse connection
    silverBase.addNeighbours(headQuarters, 20);  // Reverse connection
    bronzeBase.addNeighbours(headQuarters, 50);  // Reverse connection

}

public static void main(String[] args){
    OptimalRoute optimalRoute = new OptimalRoute();
    optimalRoute.loadImage();
    optimalRoute.loadBases();
    optimalRoute.setupGUI();
}

}
