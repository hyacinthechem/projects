import java.util.Comparator;

/**
 * Write a description of class ManagerComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ManagerComparator implements Comparator<BankBalance>
{
    public int compare(BankBalance b1, BankBalance b2){
    
        return(b1.toString()).compareTo(b2.toString());
    }
    
}
