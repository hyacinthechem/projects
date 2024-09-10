import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import ecs100.*;
import javax.swing.text.Position;

public class PositionAllocation {
     public Person theTree;

    public void setupGUI(){
      UI.addButton("Run Tree", this::runTree);
      UI.addButton("Print Survey", this::printSurvey);

  }

  public void printSurvey(){
        //Person person = new Person(theTree.getText(), theTree.getYesAnswer(), theTree.getNoAnswer());
        printSurveyHelper(": ",theTree);
    }

  public void printSurveyHelper(String branch,Person node){

        if(node == null){
           return;

        }

        String text = node.getText();

        if(!node.isAnswer()){
            text += "?";
        }
        UI.println(branch  + text);

        if(!node.isAnswer()){
            printSurveyHelper("Yes: ", node.getYesAnswer());
            theTree = node.getYesAnswer();
            printSurveyHelper("No: ", node.getNoAnswer());
            theTree = node.getNoAnswer();

        }

  }

  public void runTree(){



  }

  public void loadTree() {

      String filePath = "data/questions.txt";
      if (!Files.exists(Path.of(filePath))) {
          return;
      }
      try {
          theTree = loadSubTree(new ArrayDeque<String>(Files.readAllLines(Path.of(filePath))));

      } catch (IOException e) {
          UI.println("File Failure" + e);
      }
  }

  public Person loadSubTree(Queue<String> lines){

        Scanner sc = new Scanner(lines.poll());
        String type = sc.next();
        String text = sc.nextLine().trim();


        Person p = new Person(text);

        if(type.equals("Question:")){
            Person yesAnswer = loadSubTree(lines);
            Person noAnswer = loadSubTree(lines);
            p.setChildren(yesAnswer, noAnswer);
        }

        return p;

  }



  public static void main(String[] args ){
      PositionAllocation p = new PositionAllocation();
      p.setupGUI();
      p.loadTree();

  }

}
