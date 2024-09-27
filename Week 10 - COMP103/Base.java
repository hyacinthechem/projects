import java.util.*;

public class Base<E> implements Iterable<Base<E>> {

private E type;
private int numberOfTroops;
private Base<E> base;
private Map<Base<E>, Integer> neighbours;
private boolean visited;

public Base(E type, int numberOfTroops) {
    this.type = type;
    this.numberOfTroops = numberOfTroops;
    neighbours = new HashMap<>();

}

public E getType(){
    return type;
}

public int getNumberOfTroops(){
    return numberOfTroops;
}

public Base<E> getStation(){
    return base;
}


public Map<Base<E>, Integer> getNeighbours(){
    return Collections.unmodifiableMap(neighbours);
}

public void addNeighbours(Base<E> child, int distance){
     neighbours.put(child, distance);
}

public void removeNeighbours(Base<E> child, int distance){
     neighbours.remove(child);
}

public void visit(){
    this.visited = true;
}

public void unVisit(){
    this.visited = false;
}

public boolean isVisited(){
    return visited;
}

@Override
public String toString(){
    return "Type: " + type + " NumberOfTroops: " + numberOfTroops;
}

public Iterator<Base<E>> iterator() {
     return neighbours.keySet().iterator();
}

}
