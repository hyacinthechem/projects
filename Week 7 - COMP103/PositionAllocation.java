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

  }

  public void runTree(){
     Person person = new Person();

  }

  public void loadTree(){

      String filePath = "data/questions";
      if(Files.exists(Path.of(filePath))){
          try{
          theTree = loadSubTree(new ArrayDeque<String>(Files.readAllLines(Path.of(filePath))));

       }catch(IOException e){
          UI.println("File Failure" + e);
          }
      }else{
          return;
      }

  }

  public Person loadSubTree(Queue<String> lines){

        

  }



  public static void main(String[] args ){
      PositionAllocation p = new PositionAllocation();
      p.setupGUI();
      p.loadTree();

  }

}
