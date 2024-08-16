
/**
 * Write a description of class labs here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class labs
{
    
    public void check(){
    
    test(1,2,2);
    test(2,3,4);
    
    test(4,3,1);
    
    
    }
    
     public boolean test(int n1, int n2, int n3){
     
           if(n1<n2){
         
            if(n2<n3){
            
                return true;
        
            }else{
            
               return false;
            }
        
        }

        return true;
    }
    
    public static int abs(int x){
    
        if(x<0){
        
            return -x;
        
        }
        if(x>=0){
        
             return x;
        
        }
        
        assert false;
        
        return 0;
    
    
    }
    
    public static int smallerAbs(int x){
      
        if(x<0 || x>=0){ return x;}
          
        
         
        
    }
}
