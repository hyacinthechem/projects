import java.awt.*;
import java.util.*;
import java.util.logging.XMLFormatter;

import ecs100.UI;


public class RecursiveDrawer {

    private double x;
    private double y;
    private double width;
    private double diam;


    public void setupGUI(){

        UI.addButton("Stair Pattern" , () -> {
            x = UI.askDouble("x: ");
            y = UI.askDouble("y: ");
            width = UI.askDouble("width: ");
            rectPattern(x,y,width,10);

        });
        UI.addButton("Circle Pattern" , () -> {
                    x = UI.askDouble("x: ");
                    y = UI.askDouble("y: ");
                    diam = UI.askDouble("diam: ");
                    circPattern(x, y, diam,0);
                });

    }

    public void draw(double x, double y, double width, double height){
        UI.setColor(Color.black);
        UI.drawRect(x,y,width,height);
         // UI.setColor(Color.blue);
        //UI.fillRect(x,y,width,height);

    }

    public void rectPattern(double x, double y, double width, double height){

        draw(x,y - height,width,height);

        if(height < 200){   //base case to initialise

            rectPattern(x + width,y,width,height + 75);

        }

    }

    public void drawCircle(double x, double y, double radius, boolean isBlack) {
        UI.setColor(isBlack ? Color.black : Color.white);
        UI.fillOval(x, y, radius, radius);
        UI.setColor(Color.black);
        UI.drawOval(x,y,radius,radius);
    }

    public void circPattern(double x, double y, double diam, int depth) {
        boolean isBlack = depth % 2 == 0;
        drawCircle(x, y, diam, isBlack);

        if (diam < 500) {
            circPattern(x + 15 , y, diam + 30, depth + 1);
        }
    }






    public static void main(String[] args){

        RecursiveDrawer drawer = new RecursiveDrawer();
        drawer.setupGUI();
       // drawer.drawTri();

    }

}
