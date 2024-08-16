import ecs100.*;
import java.util.*;
/**
 * Write a description of class Password here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Password implements Comparable<Password>{

    public String user;
    public String pass;
    
    public double bankBalance;
    public String accountName;
    
    
    public Password(String user, String password){
    
     this.user = user;
     this.pass = password;
    
    }
    
    
    
    
    public void viewBankBalance(String user, double bankBalance, String accountName){
        this.user = user;
        this.bankBalance = bankBalance;
        this.accountName = accountName;
    
    
    }
    
    public int compareTo(Password other) {
        int sign = 0;
        
        if (this.bankBalance < other.bankBalance) {
            sign = 1;
        } else if (this.bankBalance > other.bankBalance) {
            sign = -1;
        }
        
        return sign;
    }

    
    public String toString(){
    
    return(user + "" + bankBalance + "" + accountName);
    
    }
    
    public String getPassword(){
    
        return pass;
    
    }
    
    public String getAdminPassword(){
    
      return "snoopy40";
    
    }
    
    
    
    
    
}
