public class Passenger implements Comparable<Passenger> {

    public String name;
    public String flight;
    public Passenger passenger;
    public boolean bagDropped = false;


    public Passenger(String name, String flight) {
        this.name = name;
        this.flight = flight;

    }

    public String baggageDrop() {
        bagDropped = true;
        return ("Passenger: " + name + " Succesfully dropped bag");
    }

    public boolean allBagsDropped() {
        return bagDropped;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    @Override
    public String toString() {
        return ("name: " + name + " Flight: " + flight);
    }

    @Override

    public int compareTo(Passenger other) {
        return this.flight.compareTo(other.flight);
    }

}
