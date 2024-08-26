import java.util.Map;
import java.util.TreeMap;
import ecs100.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;


/**
 * Write a description of class PasswordManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PasswordManager
{
    Map<String, Password> passwords = new HashMap<>();
    Map<String, Password> passwords1 = new TreeMap<>();
    
    private List<BankBalance> BankBalances = new ArrayList<>();
    private List<Password> users = new ArrayList<>();
    boolean access;
    boolean viewBalance = false;
    
    private String username;
    
    private String password;
    
    private String pass;
    
    public void loadData(){
       loadUserData();
    
    
    }
    
    public void setupGUI(){

        UI.addButton("Login", this:: doLogin);
        UI.addButton("Logout", this::doLogout);
        UI.addButton("Display Users", this::displayUsers);
        UI.addButton("Compare", this::compareTimes);
        UI.addButton("View Deposit", () -> {viewDeposit(this.username,this.access);});
        UI.addButton("View Balance", () ->{loadAccountData(this.username, this.pass,this.viewBalance = true);});
        UI.addButton("Clear" , UI::clearText);
        UI.addTextField("Admin Password", (String password) -> {this.password = password;});
        //UI.addButton("Admin Login",() -> {printLargerBankBalance(this.password);});
        UI.addButton("Admin Login",() -> {sortingBalances(this.password);});
    }

    public void setupGUITicket(){
        UI.addButton("Bank Login", this:: doLoginTicket);

    }

    public void doLoginTicket(){
        username = UI.askString("What is your username");


    }
    
public void doLogin(){
         UI.clearText();
        // loadUserData(); //load the data textfile of users with bank
        //loadAccountData(); //load the data of the balances of each user
         loadUserData();
         username = UI.askString("What is your username");
        
        boolean inSystem = loadUserData(username);
        
        access = false;
        
        if(inSystem){
        
        //loadUserData2(username);
        pass = UI.askString("Hello: " + username + " what is your password");
        access = true;
        
        loadAccountData(username, pass, viewBalance);
        }else{
        
        UI.println("Sorry, this user is not in the banks records");
        
        }
        
        
        
    
    
    }
    
public void doLogout(){
UI.clearText();
access = false;

}
    
    public void loadUserData(){
    
        String fileN = "data/user_list.txt";
    try{
       Scanner sc = new Scanner(Path.of(fileN));
       
       while(sc.hasNext()){
        
           String person = sc.next();
           String pass = sc.next();
           Password p = new Password(person,pass);

           users.add(p);
           passwords.put(person, p);
           passwords1.put(person,p);
           //BankBalance.add(new Password(person,pass));
        
        }
    
        
        
        String fileName = "data/account_list.txt";
        
        Scanner scan = new Scanner(Path.of(fileName));
        
        while(scan.hasNext()){
         String person = scan.next(); //username
         String accountType = scan.next();  //checkings or savings
         double amount = scan.nextDouble(); //amount in accountType
         BankBalances.add(new BankBalance(person,amount,accountType));
            
               // UI.println("Account Name: " + accountType + " Available: " + amount);
            
        }
    
    
    }catch(IOException e){UI.println("File Failure" + e);}
}

    public void displayUsers(){
        for(Password p : users){
            UI.println(p.toString());

        }
    }
    
    public boolean loadUserData(String user){
    
    try{
        List<String> lines = Files.readAllLines(Path.of("data/user_list.txt"));
        boolean found = false;
        for(String line : lines){
         Scanner sc = new Scanner(line);
            
         if(line.contains(user)){
           found = true;
           String person = sc.next();
           String pass = sc.next();
           
           
           //Password p = new Password(person,pass);
          
           //passwords.put(person, p);
           //passwords1.put(person,p);
         
            
            }else{
            
            sc.nextLine();
            
            }
         
            
        
         
         
        
        
        
        }
        
        if(!found){
        
        return false;
        
        }
        
    
    
    
    
    }catch(IOException e){UI.println("File Failure" + e);}
    
    return true;
}



public boolean loadAccountData(String username, String password, boolean view){
 UI.clearText();
if(access){   
    
    long startTime = System.currentTimeMillis();
    try{
    String file = "data/account_list.txt";
    boolean correctPass = false;
    for(Map.Entry<String, Password> user : passwords.entrySet()){
    
         if(user.getValue().getPassword().equals(password)){
             
             if(!viewBalance){
                 UI.println("Succesfully logged in");
                }
             
             Scanner sc = new Scanner(Path.of(file));
             
             
             while(sc.hasNext()){
                
              String person = sc.next(); //username
              
              if(person.equals(username)){
                correctPass = true;
                String accountType = sc.next();  //checkings or savings
                double amount = sc.nextDouble(); //amount in accountType
                
                ///BankBalance.add(new Password(person,amount,accountType));
                
                if(viewBalance){
                  UI.println("Account Name: " + accountType + " Available: " + amount);
                }
                }
              //String accountType = sc.next();  //checkings or savings
              //double amount = sc.nextDouble(); //amount in accountType
            
              //UI.println("Account Name: " + accountType + " Available: " + amount);
           
                
                
                }
            
            
            
            }        
        
        
    
    
    
    }
    
    
    
    
    if(!correctPass){
    
      UI.println("Incorrect password, please try again");
      
      return false;
    }

    long endTime = System.currentTimeMillis();
    
    

}catch(IOException e){UI.println("File Failure" + e);}
long endTime = System.currentTimeMillis();
//return("Hashamp took: " + (endTime - startTime) + " of processing");
}else{
    UI.println("Please Login First");
}

return true;
}


public String loadAccountDataTree(String username,String password){
     long startTime = System.currentTimeMillis();
    try{
    //long startTime = System.currentTimeMillis();
          
    String file = "data/account_list.txt";
    boolean correctPass = false;
    for(Map.Entry<String, Password> user : passwords1.entrySet()){
    
         if(user.getValue().getPassword().equals(password)){
             
             Scanner sc = new Scanner(Path.of(file));
             
             
             while(sc.hasNext()){
                
              String person = sc.next(); //username
              
              if(person.equals(username)){
                correctPass = true;
                String accountType = sc.next();  //checkings or savings
                double amount = sc.nextDouble(); //amount in accountType
            
                UI.println("Account Name: " + accountType + " Available: " + amount);
                
                }
              //String accountType = sc.next();  //checkings or savings
              //double amount = sc.nextDouble(); //amount in accountType
            
              //UI.println("Account Name: " + accountType + " Available: " + amount);
           
                
                
                }
            
            
            
            }        
        
        
    
    
    
    }
    
    
    
    
    if(!correctPass){
    
      UI.println("Incorrect password, please try again");
    
    }

    //long endTime = System.currentTimeMillis();
    
    //UI.println("Treemap took: " + (endTime - startTime) + " of processing");

}catch(IOException e){UI.println("File Failure" + e);}
long endTime = System.currentTimeMillis();
return("Treemap took: " + (endTime - startTime) + " of processing");
}
    
    
public void compareTimes(){
    
    //String hashmap = loadAccountData(username,pass);
    String TreeMap = loadAccountDataTree(username,pass);
    
    
    //UI.println(hashmap);
    UI.println(TreeMap);
    
    
    
    
    }
    
public void printLargerBankBalance(String password){
      UI.clearText();
      loadUserData();
      Password pAdmin = new Password("root", "snoopy40");
       
      String fileName = "data/account_list.txt";
        
        //Scanner scan = new Scanner(Path.of(fileName));
        
        /*
        while(sc.hasNext()){
         String person = sc.next(); //username
         String accountType = sc.next();  //checkings or savings
         double amount = sc.nextDouble(); //amount in accountType
         BankBalance.add(new Password(person,amount,accountType));
            
               // UI.println("Account Name: " + accountType + " Available: " + amount);
            
        }
      */
      
      
      
      String correct = pAdmin.getAdminPassword();
    
      if(correct.equals(password)){
        
        Collections.sort(BankBalances, Collections.reverseOrder()); 
        
        UI.println("Ascending order of people with the highest balance in accounts whether it be checkings or savings: ");
        
        for(BankBalance balance : BankBalances){
        
         UI.println(balance.toString());
        
        
        }
        
                
        
        }else{
        
            UI.println("Password is incorrect");
        
        
        }
    
    }
    
public void viewDeposit(String username, boolean access){
UI.clearText();
try{
    //boolean found = false;
    if(access){
    String filename = "data/deposit.txt";
    Scanner sc = new Scanner(Path.of(filename));
    while(sc.hasNext()){
     String name = sc.next();
    if(name.equalsIgnoreCase(username)){
      double deposit = sc.nextDouble();
      UI.println("Name:  " + name + "$" + deposit);
      
      boolean year = UI.askBoolean("Would you like to see timeline of deposit");
      
      if(year){
      int timeline = UI.askInt("Choose timeline of deposit between 1 and 4 years");
      if(timeline>4||timeline<1){
        UI.println("Not Part of Timeliene");
        }
        
      investment(deposit,0.04,timeline);
      
      UI.println(investment(deposit,0.04,timeline));

    }
}
        
    }
    
    
    }else{
    
    UI.println("Please Login first");
    
    }

}catch(IOException e){UI.println("File Failure" + e);}
}
    
    
public double investment(double deposit, double rate, int year){

    if(year == 0){
    
     return deposit;
    }else{
    
        double prev = investment(deposit,rate,year-1);
        
        return prev * ( 1 + rate);
    
        
    }
    
    


}
    
public void sortingBalances(String password){
    Password pAdmin = new Password("root", "snoopy40");
    loadUserData();
    String correct = pAdmin.getPassword();
    
    /*
    if(correct.equals(password)){
    
        Collections.sort(BankBalances, new ManagerComparator());
        
        for(BankBalance balance : BankBalances){
        
         UI.println(balance.toString());
        
        
        }
    
    }   
    
    */
   
   
   
    Comparator<BankBalance> balanceSize = (b1, b2) -> {
    
        int sign = 0;
    
    if(b1.getBankBalance()<b2.getBankBalance()){
    
        sign = 1;
    
    }else if(b1.getBankBalance()>b2.getBankBalance()){
    
      sign = -1;
    
    }
    
    return sign;
    
    
    };
   
     
    TreeMap<BankBalance, String> updatedBalances = new TreeMap<>(balanceSize);
    
    
    for(BankBalance b : BankBalances){
     
        updatedBalances.put(b, "reference");
        UI.println(updatedBalances);
        
    
    }
    
    UI.println(updatedBalances);
    }
    
    public static void main(String[] args){
    
        PasswordManager p1 = new PasswordManager();
        p1.loadUserData();
        p1.setupGUI();
    
    
    }
    
    
}

