import ecs100.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.Map;
import java.nio.file.Path;
import java.util.*;


public class TicketSystem {

   // private Map<String, Ticket> tickets = new HashMap<>();
    private Queue<Ticket> customers;

    private List<Ticket> ticketData;
    private List<Password> users = new ArrayList<>();
    private List<BankBalance> bankBalances = new ArrayList<>();
    private String password;
    private String adminPassword = "pass";
    private String ticketName;

    private boolean customerPriority = false;

    private int seatNumber;
    private double amount; //checks if user has enough to pay for ticket
    public Password username;

    // Arrays that hold Ticket Information

    private String [] type = { "Regular", "Premium", "Platinum" };
    private double[] ticketPrice = {75, 180, 300};
    private int[] priority = { 3, 2, 1 };


public void setupGUI(){
    UI.addButton("Purchase Ticket", () -> { buyTicket(username); });
    UI.addButton("View Ticket", () -> { viewTicket(this.ticketName, this.seatNumber);});
    UI.addButton("Pre-Sale Priority", () -> {preSale(this.customerPriority = true);});
    UI.addButton("Pre-Sale" , () -> {preSale(this.customerPriority);});
    UI.addTextField("Name" , (String name) -> { ticketName = name;});
    UI.addTextField("Seat Number" , (String seat) -> seatNumber = Integer.parseInt(seat));
    UI.addTextField("Password" , (String password) -> { this.password = password;});
    UI.addButton("All Tickets ( Admin )", () -> {viewTicketData(password);});
    UI.addButton("Admin Save" , this::saveTicketInformation);
    UI.addButton("Bank Save" ,  this::saveBankInformation);
    UI.addButton("Clear", UI::clearText);
    //UI.addButton("Test Queue," , this::testQueue);
}

public void preSale(boolean priority){

    UI.drawString("All Blacks V France - PreSale" , 100 , 120);

    if(priority){
        customers = new PriorityQueue<>();
    }else{
        customers = new ArrayDeque<>();
    }

    for(int i = 0; i< ticketData.size(); i++){
        customers.offer(ticketData.get(i));
    }

   while(!customers.isEmpty()){
       Ticket ticket = customers.poll();
       UI.println(ticket);
   }

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
           ticketData.add(new Ticket(name, ticketPrice, seatNumber, row, priority));

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


public void loadBankBalanceData() {

    String filePath = "data/account_list.txt";

    try {
        Scanner sc = new Scanner(Path.of(filePath));

        while(sc.hasNext()){
            String person = sc.next();
            String accountName = sc.next();
            double balance = sc.nextDouble();
            bankBalances.add(new BankBalance(person,balance,accountName));
        }


    } catch (IOException e) {
        UI.println("File Failure" + e);
    }
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
           //String ticketName = UI.askString("What type of Ticket do you want to buy?");
           ticketType(ticketName, username);
       }else{
           UI.println("Incorrect Password");
       }

   }else{
       UI.println("Username does not exist");
   }



}

public void ticketType(String ticketType, String person) {
        Iterator<BankBalance> it = bankBalances.iterator();
        while (it.hasNext()) {
            BankBalance b = it.next();
            if (b.getUser().equals(person) && b.getAccountName().equals("Checking")) {
                amount = b.getBankBalance();

                for (int i = 0; i < type.length; i++) {
                    if (ticketType.equals(type[i])) {
                        if (amount >= ticketPrice[i]) {
                            Random rand = new Random();
                            // Remove the current BankBalance entry
                            it.remove();
                            // Add the updated BankBalance entry
                            bankBalances.add(new BankBalance(person, amount - ticketPrice[i], b.getAccountName()));
                            // Add the new ticket
                            ticketData.add(new Ticket(person, ticketPrice[i], rand.nextInt(10), rand.nextInt(90), priority[i]));
                            UI.println("Ticket purchased successfully!");
                            return;  // Exit the method after a successful purchase
                        } else {
                            UI.println("Insufficient funds to purchase the " + ticketType + " ticket.");
                            return;
                        }
                    }
                }
                UI.println("Invalid ticket type selected.");
                return;
            }
        }

        UI.println("No matching bank account found for user " + person + ".");
    }

    public void saveBankInformation() {
        String filePath = UIFileChooser.open("data/account_list.txt");
        try {
            PrintStream outfile = new PrintStream(filePath);
            for(BankBalance b : bankBalances){
                outfile.println(b.toString());
            }
        } catch (IOException e) {
            UI.println("File Failure" + e);
        }
    }
    public  void saveTicketInformation() {

        String filePath = UIFileChooser.open("data/ticketData.txt");

        try {
            PrintStream outfile = new PrintStream(filePath);

            for(int i = 0; i < ticketData.size(); i++){

                outfile.println(ticketData.get(i).fileString());
            }


        } catch (IOException e) {
            UI.println("File Failure" + e);
        }
    }

    public void view(){
        for(BankBalance b : bankBalances){
            UI.println(b.toString());
        }
}


    public static void main(String[] args) {
   // PasswordManager p1 = new PasswordManager();
   // p1.setupGUI();
    TicketSystem ts = new TicketSystem();
    ts.loadData();
    ts.image();
    ts.loadUserData();
    ts.loadBankBalanceData();
    //ts.view();
    ts.setupGUI();

}



}
