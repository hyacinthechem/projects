import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import ecs100.*;


public class Squad implements Iterable<Squad>{

    private Set<Squad> squads;
    private String name;
    public boolean visited;
    public Squad general;

    public Squad(String name){
        this.squads = new HashSet<Squad>();
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addSquad(Squad s){
        this.squads.add(s);
    }

    public boolean removeSquad(Squad s){
        return this.squads.remove(s);
    }

    public Squad getSquadMember(){
        return general;
    }

    public boolean hasMember(Squad s){
        return squads.contains(s);

    }

    public void visit(){
         this.visited = true;
    }

    public void unVisit(){
        this.visited = false;
    }

    public boolean hasVisited(){
        return this.visited;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public Iterator<Squad> iterator() {
        return squads.iterator();
    }
}
