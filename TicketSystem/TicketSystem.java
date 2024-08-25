import ecs100.*;
import java.util.*;


public class TicketSystem {

public void setupGUI(){
    UI.addButton("Purchase Ticket", this::buyTicket);
    UI.addButton("View Ticket", this:: viewTicket);



}

public void loadData(){



}

public void viewTicket(){


}

public void buyTicket(){


}

    public static void main(String[] args) {

    TicketSystem ts = new TicketSystem();
    ts.loadData();
    ts.setupGUI();

}



}
