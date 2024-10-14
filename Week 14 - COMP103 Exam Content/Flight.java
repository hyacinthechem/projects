import java.awt.*;
import java.lang.annotation.ElementType;
import java.util.*;
import java.util.List;

import ecs100.*;
public class Flight<E> implements Iterable<E>, Comparable<Flight<E>> {

    public String aircraft;
    public int departureTime;
    public String airline;
    public int arrivalTime;
   // public Flight<Passenger> flight;
    public E passenger;
    public String flightNumber;
    public List<E> passengers;




    public Flight(String airline, String flightNumber, String aircraftType, int depTime, int ArrivalTime) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.aircraft = aircraftType;
        //this.passenger = p;
        this.departureTime = depTime;
        this.arrivalTime = ArrivalTime;
       // this.numberOfPassengers = numOfPassengers;
        this.passengers = new ArrayList<>();

    }



    public Flight(E p){
        this.passenger = p;
        this.passengers = new ArrayList<>();
    }

    //getters and setters

    public int numberOfPassengers() {
        return passengers.size();
    }

    public void addPassenger(E p){
        passengers.add(p);
    }

    public void removePassenger(E p){
        passengers.remove(p);
    }

    public E getPassenger() {
        return passenger;
    }

    public void setPassenger(E passenger) {
        this.passenger = passenger;
    }

    public String getFlightNumber() {
        return flightNumber;
    }


    public List<E> getFlightPassengers(){ return Collections.unmodifiableList(passengers); }

    public boolean dayTimeFlight(){
        if(this.departureTime<1700 && this.departureTime>=600){
            return true;
        }else{
            return false;
        }
    }



    @Override

    public String toString(){
        return "Airline: " + airline +
                ", Flight Number: " + flightNumber +
                ", Aircraft: " + aircraft +
                ", Number of Passengers: " + passengers.size() +
                ", Departure Time: " + departureTime +
                ", Arrival Time: " + arrivalTime;
    }

    public void drawFlight(double x, double y) {
        // Draw the flight box
        UI.setColor(Color.lightGray);
        UI.fillRect(x, y, 200, 100);  // Main flight box (airline, flight number, etc.)
        UI.setColor(Color.black);
        UI.drawRect(x, y, 200, 100);  // Border for the flight box

        // Display flight details inside the box
        UI.drawString("Airline: " + airline, x + 10, y + 20);
        UI.drawString("Flight #: " + flightNumber, x + 10, y + 35);
        UI.drawString("Aircraft: " + aircraft, x + 10, y + 50);
        UI.drawString("Departure: " + departureTime, x + 10, y + 65);
        UI.drawString("Arrival: " + arrivalTime, x + 10, y + 80);

        // Draw a sub-box for each passenger under the flight
        double passengerX = x;
        double passengerY = y + 110;
        for (E passenger : passengers) {
            // Draw each passenger box
            UI.setColor(Color.white);
            UI.fillRect(passengerX, passengerY, 150, 50);  // Passenger box
            UI.setColor(Color.black);
            UI.drawRect(passengerX, passengerY, 150, 50);  // Border for the passenger box

            // Display passenger details
            UI.drawString("Passenger: " + passenger.toString(), passengerX + 10, passengerY + 20);
            passengerY += 60;  // Move down for the next passenger
        }
    }




    @Override

    public int compareTo(Flight<E> o){

        if(departureTime<o.departureTime){
            return 1;
        }

        else if(departureTime>o.departureTime){
            return -1;
        }else {
            return 0;
        }
    }

    @Override

    public Iterator<E> iterator(){
        return this.passengers.iterator();
    }

}
