import java.util.*;
import ecs100.*;

public class STWOfficials {

    public void setupGUI(){
        UI.addButton("Print STW Hierachy", this::printOfficials);


    }

    public void printOfficials(){


    }

    public static void main(String[] args){
        STWOfficials stw = new STWOfficials();
        stw.setupGUI();


    }



}
