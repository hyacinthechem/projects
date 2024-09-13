import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import ecs100.*;

public class STWOfficials {

    private TitleRank theTree;


    public void setupGUI(){
        UI.addButton("Print STW Hierachy", this::printOfficials);
        UI.addButton("Draw STW Tree", this::drawOfficials);


    }

    public void printOfficials(){

        iterativePrinting();

        for(int i = 0; i<10; i++){
            UI.println("-------------------");
            UI.println("-------------------");
        }

        iterativePrintingPreOrder();

        for(int i = 0; i<10; i++){
            UI.println("-------------------");
            UI.println("-------------------");
        }

        recursivePreOrder(theTree);

    }

    public void iterativePrinting(){

       // TitleRank node = new TitleRank(theTree.getName(), theTree.getPositions());
        TitleRank node = theTree;
        UI.println(node.toString());

        while(!node.finalRank()){  //recurse right side of the tree

            node = node.getLeftSubRank();
            UI.println(node.toString());
            node = node.getRightSubRank();
            UI.println(node.toString());


        }

        node = theTree;

        UI.println(node.toString());

        while(!node.finalRank()){ //recurse left side of the tree
            node = node.getRightSubRank();
            UI.println(node.toString());
            node = node.getLeftSubRank();
            UI.println(node.toString());

        }

    }

    public void iterativePrintingPreOrder(){

        if(theTree==null){
            return;
        }


        TitleRank node = theTree;

        Stack<TitleRank> stack = new Stack<>();

        //root, left, right
        //process root push it then push all
        stack.push(node);
        while(!stack.isEmpty()){
            TitleRank temp = stack.pop();
            UI.println(temp.toString());
            if(!(temp.getRightSubRank() == null)){
                stack.push(temp.getRightSubRank());
            }

            if(!(temp.getLeftSubRank() == null)){
                stack.push(temp.getLeftSubRank());
            }
        }


    }

    public void recursivePreOrder(TitleRank node){


        if(node==null){   //base case: stop recursion when no more items to traverse in the tree
            return;
        }

        UI.println(node.toString());
        recursivePreOrder(node.getLeftSubRank());
        recursivePreOrder(node.getRightSubRank());



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
        TitleRank node = new TitleRank(text,title);
        //if (type.contains("President:") || type.contains("Governor-General:") || type.contains("Heads-Of-Departments:" )){
        if(type.equals("Question:")){
        TitleRank yesCh = loadSubTree(lines);
            TitleRank noCh = loadSubTree(lines);
            node.setRanks(yesCh, noCh);
        }
        return node;

    }

    public void drawOfficials(){

        recursiveDrawer(theTree, 100, 200);

    }

    public void recursiveDrawer(TitleRank node, double x, double y){
        if(node==null){
            return;
        }

        node.draw(x,y);


        recursiveDrawer(node.getLeftSubRank(),x+100,y-50);
        recursiveDrawer(node.getRightSubRank(),x+100,y+50);

    }


    public static void main(String[] args){
        STWOfficials stw = new STWOfficials();
        stw.loadTree("Hierachy.txt");
        stw.setupGUI();


    }



}
