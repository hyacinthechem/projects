import java.util.Comparator;

/**
 * Write a description of class BankBalance here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BankBalance implements Comparable<BankBalance>
{
    
    public String user;
    public String pass;
    
    public double bankBalance;
    public String accountName;
    
public BankBalance(String user, double bankBalance, String accountName){
        this.user = user;
        this.bankBalance = bankBalance;
        this.accountName = accountName;
    
    }
    
    public int compareTo(BankBalance other){
       
    int sign = 0;
    
    if(this.bankBalance>other.bankBalance){
    
        sign = 1;
    
    }else if(this.bankBalance<other.bankBalance){
    
      sign = -1;
    
    }
    
    return sign;
}

public String toString(){
    
    return(user + " " + bankBalance + " " + accountName);
    
    }
    
public double getBankBalance(){
    
    return this.bankBalance;
    
    }
    
    public String getPassword(){
    
        return pass;
    
    }
    
    public String getAdminPassword(){
    
      return "snoopy40";
    
    }
    
}

