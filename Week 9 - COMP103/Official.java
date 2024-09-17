import ecs100.UI;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Official {

private String name;
private String title;
private Set<Official> teamOfficials = new HashSet<Official>();

//Important fields
    private String role;
    private Official manager;
    private double offset;  // horizontal offset relative to the manager
    // negative value to the left of the manager,
    // positive value to the right

    // Constants for size of box representing the position


    public static final double WIDTH = 125;  // Increased from 55 to 80
    public static final double HEIGHT = 40;

    public static final double V_SEP = 30;   // vertical separation between layers (increased slightly)
    public static final double ROOT_TOP = 50;
    public static final double ROOT_X = 550;
    public static final Color BACKGROUND_COL = new Color(255, 255, 180);
    public static final Color HIGHLIGHT_COL = new Color(97, 255, 89);
    public static final Color TEMP_HIGHLIGHT_COL = new Color(222, 255, 220);
    public static final Color EMPL_HIGHLIGHT_COL = new Color(9, 255, 255);

//



    //official constructor with only the title role
public Official(String title){
    this.title = title;
}


public Official(String name, String title) {
    this.name = name;
    this.title = title;
    this.teamOfficials = new HashSet<Official>();
}



public String getName() {
    return name;
}

public String getTitle() {
    return title;
}

public Set<Official> getTeamOfficials() {
    return Collections.unmodifiableSet(teamOfficials);
}

public void addTeamOfficial(Official official) {
    teamOfficials.add(official);
    official.manager = this;
}

public void removeTeamOfficial(Official official) {
    teamOfficials.remove(official);
    official.manager = null;
}


public String toString() {
    for(int i = 0; i<8; i++){

        UI.println("-");
    }
    return(" position: " + title);
}

    public void moveOffset(double x){
        if (manager == null) { // this must be the CEO
            offset = x - ROOT_X;
        }
        else {
            offset = x - manager.getX();
        }
    }


    public void setOffset(double off){
        offset = off;
    }

    private double getTop(){
        if (manager == null) { return ROOT_TOP; }   // this must be the top position
        return manager.getTop() + HEIGHT + V_SEP;
    }


    private double getX(){
        if (manager == null) {  // this must be the CEO
            return ROOT_X + offset;
        }
        else {
            return manager.getX() + offset;
        }
    }





    public void draw(){
        draw(false, false);
    }


    public void drawHighlighted(){
        draw(true, false);
    }


    public void draw(boolean highlighted, boolean tempHighlighted){
        double left=getX()-WIDTH/2;
        double top=getTop();
        // Background colour
        UI.setColor(tempHighlighted?TEMP_HIGHLIGHT_COL:(highlighted?HIGHLIGHT_COL:BACKGROUND_COL));
        UI.fillRect(left,top,WIDTH,HEIGHT);
        // Outline colour
        UI.setColor(Color.black);
        UI.drawRect(left,top,WIDTH,HEIGHT);
        UI.drawString((title==null)?"--":title, left+5, top+12);
        if (manager != null) {
            UI.setColor(Color.black);
            // vertical line
            double x1 = manager.getX();
            double y1 = manager.getTop() + HEIGHT;
            double yMid = y1 + V_SEP/2;

            double x2 = x1 + offset;
            double y2 = y1 + V_SEP;

            UI.drawLine(x1, y1, x1, yMid);    // vertical
            UI.drawLine(x1, yMid, x2, yMid);  // horizontal
            UI.drawLine(x2, yMid, x2, y2);    // vertical
        }
    }

}






