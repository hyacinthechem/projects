import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import ecs100.*;

public class STWOfficials {

    private TitleRank theTree;


    public void setupGUI(){
        UI.addButton("Print STW Hierachy", this::printOfficials);


    }

    public void printOfficials(){

        iterativePrinting();

    }

    public void iterativePrinting(){

        TitleRank node = new TitleRank(theTree.getName(), theTree.getPositions());

        while(!node.finalRank()){
            TitleRank current = node;
            current = current.getLeftSubRank();
            UI.println(current.toString());
            current = current.getRightSubRank();
            UI.println(current.toString());


        }

    }

    public void loadTree (String filename) {
        if (!Files.exists(Path.of(filename))){
            UI.println("No such file: "+filename);
            return;
        }
        try{theTree = loadSubTree(new ArrayDeque<String>(Files.readAllLines(Path.of(filename))));}
        catch(IOException e){UI.println("File reading failed: " + e);}
    }


    public TitleRank loadSubTree(Queue<String> lines){
        Scanner line = new Scanner(lines.poll());
        String type = line.next();
        String title = line.next();
        String text = line.nextLine().trim();
        TitleRank node = new TitleRank(title,text);
        //if (type.contains("President:") || type.contains("Governor-General:") || type.contains("Heads-Of-Departments:" )){
        if(type.equals("Question:")){
        TitleRank yesCh = loadSubTree(lines);
            TitleRank noCh = loadSubTree(lines);
            node.setRanks(yesCh, noCh);
        }
        return node;

    }



    public static void main(String[] args){
        STWOfficials stw = new STWOfficials();
        stw.loadTree("Hierachy.txt");
        stw.setupGUI();


    }



}
