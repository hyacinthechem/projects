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
    
    private String username;
    
    private String password;
    
    private String pass;
    
    public void loadData(){
       loadUserData();
    
    
    }
    
    public void setupGUI(){
    
        UI.addButton("Login", this:: doLogin);
  
        UI.addButton("Compare", this::compareTimes);
        UI.addButton("Clear" , UI::clearText);
        UI.addTextField("Admin Password", (String password) -> {this.password = password;});
       UI.addButton("Admin Login",() -> {printLargerBankBalance(this.password);});
    }
    
    
    public void doLogin(){
         
        // loadUserData(); //load the data textfile of users with bank
        //loadAccountData(); //load the data of the balances of each user
        loadUserData();
         username = UI.askString("What is your username");
        
        boolean inSystem = loadUserData(username);
        
        if(inSystem){
        
        //loadUserData2(username);
         pass = UI.askString("Hello: " + username + " what is your password");
        loadAccountData(username, pass);
        
       }else{
        
        UI.println("Sorry, this user is not in the banks records");
        
        }
        
        
        
    
    
    }
    
    public void loadUserData(){
    
        String fileN = "data/user_list.txt";
    try{
       Scanner sc = new Scanner(Path.of(fileN));
       
       while(sc.hasNext()){
        
           String person = sc.next();
           String pass = sc.next();
           Password p = new Password(person,pass);
          
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



public String loadAccountData(String username, String password){
    long startTime = System.currentTimeMillis();
    try{
    String file = "data/account_list.txt";
    boolean correctPass = false;
    for(Map.Entry<String, Password> user : passwords.entrySet()){
    
         if(user.getValue().getPassword().equals(password)){
             
             Scanner sc = new Scanner(Path.of(file));
             
             
             while(sc.hasNext()){
                
              String person = sc.next(); //username
              
              if(person.equals(username)){
                correctPass = true;
                String accountType = sc.next();  //checkings or savings
                double amount = sc.nextDouble(); //amount in accountType
                
                ///BankBalance.add(new Password(person,amount,accountType));
            
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

    long endTime = System.currentTimeMillis();
    
    


}catch(IOException e){UI.println("File Failure" + e);}
long endTime = System.currentTimeMillis();
return("Hashamp took: " + (endTime - startTime) + " of processing");
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
    
    String hashmap = loadAccountData(username,pass);
    String TreeMap = loadAccountDataTree(username,pass);
    
    
    UI.println(hashmap);
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
    
    public static void main(String[] args){
    
        PasswordManager p1 = new PasswordManager();
        p1.setupGUI();
    
    
    }
    
    
}
