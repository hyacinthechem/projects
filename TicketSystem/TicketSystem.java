import ecs100.*;

import java.io.IOException;
import java.util.*;
import java.nio.file.Path;
import java.util.*;


public class TicketSystem {

    private List<Ticket> ticketData;
    private List<Password> users = new ArrayList<>();
    private String password;
    private String adminPassword = "pass";
    private String ticketName;
    private int seatNumber;
    public Password username;


public void setupGUI(){
    UI.addButton("Purchase Ticket", () -> { buyTicket(username); });
    UI.addButton("View Ticket", () -> { viewTicket(this.ticketName, this.seatNumber);});
    UI.addTextField("Name" , (String name) -> { ticketName = name;});
    UI.addTextField("Seat Number" , (String seat) -> seatNumber = Integer.parseInt(seat));
    UI.addTextField("Password" , (String password) -> { this.password = password;});
    UI.addButton("All Tickets ( Admin )", () -> {viewTicketData(password);});
    UI.addButton("Clear", UI::clearText);
}

public void loadData() {
    try {
        String fileName = "data/ticketData.txt";
        Scanner sc = new Scanner(Path.of(fileName));
        ticketData = new ArrayList<>();

        while(sc.hasNext()){
           String name = sc.next();
           double ticketPrice = sc.nextDouble();
           int row = sc.nextInt();
           int seatNumber = sc.nextInt();
           int priority = sc.nextInt();
           ticketData.add(new Ticket(name, ticketPrice, seatNumber, row));

        }

    } catch (IOException e) {
        UI.println("File Failure" + e);
    }
}

public static void image(){
    UI.drawImage("data/Stade_de_France_Rugby_1.jpg" , 100 , 200);
}
public void loadUserData() {
    String fileN = "data/user_list.txt";
    try {
        Scanner sc = new Scanner(Path.of(fileN));

        while (sc.hasNext()) {

            String person = sc.next();
            String pass = sc.next();
            Password p = new Password(person, pass);

            users.add(p);

        }

    }catch(IOException e ){UI.println("File Failure" + e);}
}

public void viewTicketData(String pass) {
    UI.clearText();
    if(pass.equals(adminPassword)){
        for(int i = 0; i < ticketData.size(); i++){
            UI.println(ticketData.get(i).toString());
        }

    }else{
        UI.println("Permission Denied");
    }


}

public void viewTicket(String ticketName, int seatNumber){
    UI.clearText();
    boolean found = false;
for(int i = 0; i < ticketData.size(); i++){
    if(ticketData.get(i).getName().equals(ticketName) && ticketData.get(i).getSeat() == seatNumber){
        found = true;
        UI.println(ticketData.get(i).toString());
    }
}
if(!found){
    UI.println("Ticket not found in System");
}

}

public Password findUsername(String u) {
    for(int i = 0; i < users.size(); i++){
        if(users.get(i).getUser().equals(u)){
            Password p1  = users.get(i);

            return p1; }
    }
    return null;
}

public void buyTicket(Password user){
    UI.clearText();
    String username = UI.askString("Enter Username");
    user = findUsername(username);
   if(user.getUser().equals(username)){
       String password = UI.askString("Enter Password");
       if(user.getPassword().equals(password)){
           String ticketName = UI.askString("What type of Ticket do you want to buy?");
           UI.drawString("Regular", 100, 200);
           UI.drawString("Premium", 100, 225);
           UI.drawString("Platinum", 100, 250);
           ticketType(ticketName, username);
       }else{
           UI.println("Incorrect Password");
       }

   }else{
       UI.println("Username does not exist");
   }



}

    public void ticketType(String ticketType,String person){

        if(ticketType.equals("Regular")){
            Random rand = new Random();
            Ticket t1 = new Ticket(person,75,rand.nextInt(90),rand.nextInt(10));
            ticketData.add(t1);

        }
        if(ticketType.equals("Premium")){
           Random rand = new Random();
           Ticket t2 = new Ticket(person,180,rand.nextInt(90),rand.nextInt(10));
           ticketData.add(t2);
        }

       if(ticketType.equals("Platinum")){
        Random rand = new Random();
        Ticket t3 = new Ticket(person,300,rand.nextInt(90),rand.nextInt(10));
        ticketData.add(t3);
        }

}



    public static void main(String[] args) {
    PasswordManager p1 = new PasswordManager();
    p1.setupGUITicket();
    TicketSystem ts = new TicketSystem();
    ts.loadData();
    ts.image();
    ts.loadUserData();
    ts.setupGUI();

}



}
