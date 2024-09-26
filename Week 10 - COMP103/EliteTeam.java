import java.util.*;
import ecs100.*;

public class EliteTeam {

    private String name; // For user input in the GUI
    private Squad rootSquad; // Starting point of the squad graph
    private Set<Squad> processed = new HashSet<>();

    private boolean set;
    private boolean visited;

    public void setupGUI() {
        UI.addButton("Print Graph", this::printGraph);
       // UI.addButton("Check Connected", this::checkConnected);
        UI.addButton("Count Connection", this::connection);
        UI.addTextField("Connection", (String name) -> { this.name = name; });
    }

    public void printGraph() {
        set = UI.askBoolean("Do you want a set or use of boolean?");
        if(set){
            processSquad(rootSquad);
        }else{
            processSquadBoolean(rootSquad);
        }
    }

    public void connection(){

        UI.println(connectionCount(rootSquad));

    }

    public int connectionCount(Squad squad){
        if(squad == null){
            return 0;
        }

        squad.visit();
        int count = 1;
        for(Squad s : squad){
            if(!s.hasVisited()) {
                count += connectionCount(s);
            }
        }
        return count;
    }


    public void processSquad(Squad squad) {

        if (squad == null) {
            return;
        }
        processed.add(squad);
        UI.println(squad.getName());
        for (Squad s : squad) {
            if(!processed.contains(s)) {
                processSquad(s);
            }
        }
    }

    public void processSquadBoolean(Squad squad){
        if (squad == null) {
            return;
        }
        squad.visit();
        UI.println(squad.toString());


        for(Squad s : squad){
           if(!s.hasVisited()) {
               processSquadBoolean(s);
           }
        }

    }



    public void createSquad() {
        List<String> names = Arrays.asList(
                "Emily Miller", "James Garcia", "Luca Garcia", "Antoine Proust", "Evan Smith"
        );

        // Create a cycle by making squads reference each other
        Squad firstSquad = null;
        Squad prevSquad = null;

        for (String name : names) {
            Squad squad = new Squad(name);

            if (firstSquad == null) {
                firstSquad = squad; // Keep track of the first squad
            }

            if (prevSquad != null) {
                prevSquad.addSquad(squad); // Add the current squad to the previous one
            }

            prevSquad = squad; // Move to the next squad
        }

        // Close the cycle by making the last squad point to the first one
        if (prevSquad != null && firstSquad != null) {
            prevSquad.addSquad(firstSquad); // This creates a cycle
        }

        // Set the root squad to the first squad created
        rootSquad = firstSquad;
    }

    public static void main(String[] args) {
        EliteTeam team = new EliteTeam();
        team.createSquad(); // Create the squads and structure
        team.setupGUI(); // Setup the GUI
    }
}
