import java.util.*;
import ecs100.*;

public class ThreatDetector {

   private Node root;

public void setupGUI(){
    UI.addButton("Print tree", this:: printTree);
    UI.addButton("Run Tree", this::runTree);
}

public void printTree(){

    recursiveHelper(root,0);

}

public void recursiveHelper(Node root, int depth){

    if(root == null){
        return;
    }

    for(int i = 0; i < depth; i++){
        UI.print(" ");
    }

    UI.println(root.toString());
    recursiveHelper(root.getRightDecision(), depth+1);
    recursiveHelper(root.getLeftDecision(), depth+1);


}

public void runTree(){

    runTreeHelper(root,0);

}

public void runTreeHelper(Node root, int depth){
    if(root == null){
        return;
    }

    if(root.isAnswer()){
        UI.println("Finished");
    }



    for(int i =0; i<depth; i++){
        UI.print(" ");
    }

    boolean ask = UI.askBoolean(root.getQuestion());

    if(ask){
        runTreeHelper(root.getLeftDecision(),depth+1);
    }else{
        runTreeHelper(root.getRightDecision(),depth+1);
    }



}

public void loadTree(){

    root = new Node("Is the network not usual IP address");
    Node leftRoot = new Node("Were there more than 10 login attempts");
    Node rightRoot = new Node("Did an Employer Login");

    Node leftRoot1 = new Node("Were they all done in less than five seconds");
    Node rightRoot1 = new Node("System is fine");

    Node leftRoot2 = new Node("System Shut Down");
    Node rightRoot2 = new Node("Alert Administrator");

    Node employleft = new Node("Was it his usual time");
    Node employRight = new Node("was it an IT specialist");

    Node timeLeft = new Node("System is fine");
    Node timeRight = new Node("email employee for confirmation");

    Node itLeft = new Node("Alert IT Specialist");
    Node itRight = new Node("System Shut Down");




    root.setDecisionChildren(leftRoot, rightRoot);
    leftRoot.setDecisionChildren(leftRoot1, rightRoot1);
    leftRoot1.setDecisionChildren(leftRoot2, rightRoot2);
    rightRoot.setDecisionChildren(employleft, employRight);
    employleft.setDecisionChildren(timeLeft, timeRight);
    employRight.setDecisionChildren(itLeft, itRight);




}

public static void main(String[] args){
    ThreatDetector td = new ThreatDetector();
    td.loadTree();
    td.setupGUI();
}

}
