
/**
 * Write a description of class Stroke here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import ecs100.*;
import java.util.*;
import java.awt.*;
/**
 * Write a description of class Stroke here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stroke {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
   

    public Stroke(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        
    }

    public void draw() {
        UI.setColor(Color.black);
        UI.drawLine(x1, y1, x2, y2);
    }
}



