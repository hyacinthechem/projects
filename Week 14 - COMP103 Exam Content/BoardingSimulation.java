import java.util.*;
import ecs100.*;

/* Hold Queue for passengers travelling on a flight object and have the string that maps the flight number to the flight */

public class BoardingSimulation {

    public boolean priority;
    private Map<String, Queue<Passenger>> allFlights;

    public void setupGUI(){
        UI.addButton("Run Boarding Process", this::run);
        UI.addButton("Run Priority Boarding Process", () -> { resetQueues(priority=true); run();});

    }

    public void resetQueues(boolean priority){
         allFlights = new HashMap<String, Queue<Passenger>>();
         if(priority){
             FlightProcessor fd = new FlightProcessor();
             List<Flight<Passenger>> allFlightsList = fd.loadFlights();

             for(Flight<Passenger> flight : allFlightsList){
                 Queue<Passenger> prioQueue = new PriorityQueue<>();
                 prioQueue.addAll(flight.getFlightPassengers());
                 String flightNumber = flight.getFlightNumber();
                 allFlights.put(flightNumber, prioQueue);
             }

         }else{
              FlightProcessor fd = new FlightProcessor();
              List<Flight<Passenger>> allFlightsList = fd.loadFlights();
              for(Flight<Passenger> flight : allFlightsList){
                  Queue<Passenger> queue = new ArrayDeque<>();
                  queue.addAll(flight.getFlightPassengers());
                  String flightNumber = flight.getFlightNumber();
                  allFlights.put(flightNumber, queue);
              }

         }

    }

    public void run(){
        while(true) {
            if (allFlights.isEmpty()) {
                UI.println("All Flights have been Boarded");
                break;
            }

            runSecurityCheck(); //security check screening
            //resetQueues(priority);
            boardAllPassengers();
        }


    }


    public void runSecurityCheck(){

        // use map methods to get the key and return a value. therefore get flight number and return the queue for that flight
        // and security check all the passengers in that queue

        for(Queue<Passenger> queue : allFlights.values()){
            //Queue<Passenger> queue = allFlights.get(flightNumber);
            for (Passenger passenger : queue) {
                if (!passenger.securityChecked()) {  // Check if the passenger hasn't passed security
                    String check = passenger.securityCheck();  // Perform security check
                    UI.println("Security check result: " + check);
                }
            }

        }

    }

    public void boardAllPassengers(){
        for(String flightNumber : allFlights.keySet()){
            Queue<Passenger> queue = allFlights.get(flightNumber);
            while(!queue.isEmpty()){
                Passenger p = queue.peek();
                if(p.securityChecked()){
                    queue.poll();

                    UI.println("Passenger has Succesfully Boarded on Flight: " + flightNumber );
                    UI.println("Passenger Status: " + p.getStatus());
                }
            }


        }

    }

    public static void main(String[] args) {
        BoardingSimulation bs = new BoardingSimulation();
        bs.resetQueues(false);
        bs.setupGUI();
    }
}
