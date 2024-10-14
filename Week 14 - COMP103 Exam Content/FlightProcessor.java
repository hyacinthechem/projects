import java.awt.*;
import java.util.*;
import java.util.List;

import ecs100.*;
public class FlightProcessor {

    private String flightName;
    private int departureTime;
    private int arrivalTime;
    private Passenger passenger;
    private List<Flight<Passenger>> allFlights = new ArrayList<>();


    private String[] airlines = { "Emirates", "Air New Zealand", "Qantas", "Cathay Pacific", "Singapore Airlines","Jetstar"};
    private int[] departureTimes = {100,200,300,400,500,600,700,800,900,1000,1100,1200,1300,1400,1500,1600,1700,1800,1900,2000,2100,2200,2300};
    private int[] arrivalTimes = { 100,200,300,400,500,600,700,800,900,1000,1100,1200,
            1300,1400,1500,1600,1700,1800,1900,2000,2100,2200,2300 };
    private String[] aircraftTypes = {"Airbus A320Ceo", "Airbus A350-1000", "Boeing 777-300ER","Airbus A321Neo","Boeing 737-300", "Boeing 787-9", "Airbus A380"};
    private String[] flightNumbers = {"SQA286","ANZ12","QFA103","ANZ1", "ANZ2","QFA132","CPA188","EK449","JST204"};


    public void setupGUI(){
        UI.addButton("Run Departure Process", this::departureProcess);
        UI.addButton("Display All Flights", this::allFlights);
        UI.addButton("Draw All Flights", this::drawAllFlights);
    }



    public void departureProcess(){


    }

    public void allFlights(){

        for(Flight<Passenger> flight : allFlights){
            UI.println(flight.toString());
        }

    }

    public void drawAllFlights(){
        double x = 50;  // Starting x position
        double y = 50;  // Starting y position
        for (Flight<Passenger> flight : allFlights) {
            flight.drawFlight(x, y);  // Draw the current flight at (x, y)
            y += 200;  // Move down for the next flight
        }
    }


    String[] names = {"Logan Clark",
            "Daniel Baker", "Zoe Johnson",
            "Sophie Taylor", "Sarah Campbell",
            "Joseph Thompson", "John Taylor", "Isabella Allen",
            "Nathan Robinson", "Ella Thompson", "Jane Turner",
            "Hannah Allen", "Chloe Hall", "Isabella King",
            "Ella Allen", "Jane Scott", "Andrew Rodriguez",
            "Jacob Adams", "David Miller", "Logan Anderson", "Logan Miller",
            "Ella Jackson", "Grace Anderson", "Logan Lewis", "Mia White",
            "Sophia Clark", "Nathan Lee", "Joseph Rodriguez", "Daniel Adams",
            "Emily Lee", "Zoe Roberts", "Isabella Perez", "Lily Johnson", "Matthew Nelson", "Michael Brown",
            "William Garcia", "William Clark", "Anthony Phillips", "Ryan Garcia", "Mia Baker",
            "Joseph Mitchell", "Amelia Mitchell", "Isabella Baker", "Andrew Martinez", "Sarah Campbell",
            "Chloe Phillips", "Lily Martin", "Isabella Baker", "Madison Davis", "Nathan Mitchell",
            "Zoe Harris", "Nathan Green", "Benjamin Thomas", "Grace Lee", "Daniel White", "Joseph Taylor",
            "Samuel Robinson", "James Robinson", "Nathan Lewis", "Ryan Hall", "Zoe Lee", "Ava Roberts", "Hannah Thompson",
            "David Martinez", "Matthew Williams", "Daniel Miller", "Ella Martinez",
            "Zoe Baker", "Michael Adams", "Samuel Robinson", "Jacob Thomas", "William King",
            "Emma Lewis", "Logan Robinson", "William Garcia", "Anthony Wilson", "Madison Jackson",
            "Madison Mitchell", "Charlotte Jones", "Jane Roberts",
            "Logan Roberts", "Amelia Perez", "Ella Baker", "James Taylor", "Ethan Baker", "James Baker", "Andrew Carter", "Ethan Lee", "Charlotte Jackson",
            "Michael Young", "Ava Carter", "Olivia Young", "Jacob Green", "Andrew Jones", "Daniel Lewis", "Zoe Johnson", "Lily White", "Olivia Robinson",
            "Madison Scott", "Ryan Clark", "Ava Taylor", "Ella Miller", "Ava Allen", "Laura Perez", "Ella Adams", "Sarah Jackson",
            "Sophie Lewis", "Lily Williams", "Charlotte White", "Zoe Robinson", "Logan Scott", "Ava Williams", "Logan King", "Sophia Nelson", "Ella Wilson", "Amelia Martinez", "Mia Wilson", "Matthew Martin", "Jane Green", "Laura Campbell", "Sophie Rodriguez", "Grace Adams", "Sophia Taylor", "Andrew Clark", "Sophia Thompson", "Hannah King", "Anthony Rodriguez", "Daniel Baker", "Michael Anderson", "Andrew Martinez", "Matthew Scott", "Chloe Green",
            "Michael Brown", "Madison Johnson", "Charlotte Thomas", "Alex Davis", "Hannah Jones", "Anthony Garcia", "Isabella Lee", "Ella Baker", "Emma Baker", "Ava Jackson", "Benjamin Nelson", "Sophia Lewis", "Jacob Scott", "Daniel Rodriguez", "Chloe Brown", "Laura Allen",
            "Ella Jones", "Sophie King", "Sophie Carter", "Sarah Adams", "Emily Walker", "Hannah Williams", "John Jackson",
            "Mia Taylor", "William Thompson", "Sophia Lewis", "Jane Perez", "Daniel Green", "James Thomas", "Nathan Wilson",
            "Chris Miller", "Chris Anderson", "David Phillips", "Lily Martin", "Daniel Perez", "Andrew Turner", "Joshua Rodriguez", "Sarah Garcia", "Grace Adams", "Joseph White", "William Jackson", "Jane Clark", "Madison Mitchell", "Emily Walker", "Emily Nelson", "Nathan Nelson", "Benjamin Johnson", "Grace Thompson", "Mia Rodriguez", "Hannah Young", "Chloe Garcia", "Joseph Jones", "Amelia Williams", "Isabella Jackson", "Hannah Clark", "Alex Lewis", "Hannah Johnson", "Samuel Allen", "John Martin",
            "Hannah White", "Ryan Lewis", "Ava Thomas", "Emma Jones", "Emily Jones",
            "Hannah Davis", "Andrew Phillips", "Emma Robinson", "Chris Wilson"};

    String[] memberStatus = {"Airpoints Silver","Airpoints Gold", "Airpoints Elite", "Airpoints Platinum" };




    public void loadFlights(){
          Random rand = new Random();
        for(int i=0; i<flightNumbers.length * 100; i++){
            String airline = airlines[rand.nextInt(airlines.length)];
            int departureTime = departureTimes[rand.nextInt(departureTimes.length)];
            int arrivalTime = arrivalTimes[rand.nextInt(arrivalTimes.length)];
            String aircraftType = aircraftTypes[rand.nextInt(aircraftTypes.length)];
            String flightNumber = flightNumbers[rand.nextInt(flightNumbers.length)];
            Flight<Passenger> flight = new Flight<Passenger>(airline,flightNumber,aircraftType,departureTime,arrivalTime);
            int numOfPassengers = rand.nextInt(300) + 1;
            for(int j=0; j<numOfPassengers; j++){
                Passenger passenger = new Passenger(names[rand.nextInt(names.length)],flightNumber,memberStatus[rand.nextInt(memberStatus.length)]);
                flight.addPassenger(passenger);
            }
            allFlights.add(flight);
        }


    }



    public static void main(String[] args){
        FlightProcessor fp = new FlightProcessor();
        BaggageDrop dp = new BaggageDrop();
        dp.setupGUI();
        dp.resetQueues();
        dp.loadPassengers();
        fp.loadFlights();
        fp.setupGUI();
    }




}
