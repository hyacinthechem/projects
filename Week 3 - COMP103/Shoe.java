import java.awt.Color;
import ecs100.*;
import java.util.*;

/**
 * Write a description of class Shoe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Shoe
{
    private double size;
    private String brand;
    private String model;
    private String stock;
    private Color col;
    
    public Shoe(double size, String brand, String model, String stock, Color Colourway){
    
     this.size = size;
     this.brand = brand;
     this.model = model;
     this.stock = stock;
     this.col = Colourway;
    
    
    }
    
    public double getSize(){
    
        return size; //returns the size of a shoe
    
    
    }
    
    public String getBrand(){
    
        
        return brand; //returns the brand
    }
    
    public String getModel(){
    
        return model; //returns shoe model
    }
    
    public String getStock(){
    
        return stock; //returns if shoe is in stock
    }
    
    public String toString(){
    
    return("Shoe [Size: " + size + ", Brand: " + brand + ", Model: " + model + ", Stock: " + stock);
    }
    
    }
    

