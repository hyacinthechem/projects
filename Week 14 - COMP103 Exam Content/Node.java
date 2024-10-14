public class Node {

  private int value;
  private String question;
  private Node leftDecision;
  private Node rightDecision;

  public Node(String question){
      this.question = question;
  }

  public String getQuestion(){
      return question;
  }

  public void setQuestion(String question){
      this.question = question;
  }

  public Node getLeftDecision(){
      return leftDecision;
  }

  public Node getRightDecision(){
      return rightDecision;
  }

  public void setDecisionChildren(Node leftDecision, Node rightDecision){
      if(leftDecision!=null && rightDecision!=null) {
          this.leftDecision = leftDecision;
          this.rightDecision = rightDecision;
      }
  }

  public void setleftDecision(Node leftDecision){
      this.leftDecision = leftDecision;
  }

  public void setrightDecision(Node rightDecision){
      this.rightDecision = rightDecision;
  }

  public boolean isAnswer(){
      if(leftDecision!=null && rightDecision!=null) {
          return false;
      }else{
          return true;
      }
  }

  @Override
    public String toString(){
      return question;
  }

}
