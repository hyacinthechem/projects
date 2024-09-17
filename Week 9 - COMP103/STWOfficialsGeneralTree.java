import ecs100.UI;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

  public class STWOfficialsGeneralTree {

Official allOfficials;
Official selectedPosition = null;

public void setupGUI(){
    UI.addButton("Draw Officials", this:: drawOfficials);
    UI.addButton("Print standard Tree", this:: printTree);

}

public void printTree(){

    recursiveProcess(allOfficials);

}

public void drawOfficials(){

   drawTree(this.allOfficials);


}

public void drawTree(Official official){
    if (official==selectedPosition){
        official.drawHighlighted();
    }
    else {
        official.draw();
    }

    Set<Official> officialTeam = new HashSet<>(official.getTeamOfficials());

    for(Official o : officialTeam ){
        drawTree(o);
    }


}

public void recursiveProcess(Official official){
    Set<Official> allOfficialsSet = official.getTeamOfficials();
    if(allOfficialsSet.isEmpty()){
        return;
    }

    for(Official official1 : allOfficialsSet){
        recursiveProcess(official1);
        UI.println(official1.toString());

    }


}

      public void loadOfficials() {
          allOfficials = new Official("President");

          Official governorGeneral = new Official("Governor-General");
          Official governors = new Official("Governor");
          Official chairMan = new Official("Chairman");
          Official deputyChairman = new Official("Deputy Chairman");
          Official ministers = new Official("Ministers");
          Official ambassadorGeneral = new Official("Ambassador-General");
          Official ambassadors = new Official("Ambassadors");
          Official senators = new Official("Senators");
          Official headsOfDepartments = new Official("Heads of Departments");
          Official directors = new Official("Directors");
          Official deputyDirectors = new Official("Deputy Directors");
          Official agents = new Official("Agents");
          Official medicalOfficers = new Official("Medical Officers");
          Official developers = new Official("Developers");
          Official academics = new Official("Academics");
          Official troops = new Official("Troops");
          Official engineers = new Official("Engineers");

          // Spacing adjusted for 125 width, with tighter layout
          allOfficials.addTeamOfficial(headsOfDepartments);
          headsOfDepartments.setOffset(-300); // Reduced spacing
          headsOfDepartments.addTeamOfficial(directors);
          directors.setOffset(-150);  // Reduced further
          directors.addTeamOfficial(deputyDirectors);
          deputyDirectors.setOffset(0);  // Centered under directors
          deputyDirectors.addTeamOfficial(agents);
          agents.setOffset(-120);  // Left of deputy directors
          deputyDirectors.addTeamOfficial(medicalOfficers);
          medicalOfficers.setOffset(-60);  // Slightly left
          deputyDirectors.addTeamOfficial(troops);
          troops.setOffset(60);  // Slightly right
          deputyDirectors.addTeamOfficial(engineers);
          engineers.setOffset(120);  // Far right
          deputyDirectors.addTeamOfficial(academics);
          academics.setOffset(80);  // Between troops and engineers
          deputyDirectors.addTeamOfficial(developers);
          developers.setOffset(-80);  // Between agents and medical officers

          allOfficials.addTeamOfficial(governorGeneral);
          governorGeneral.setOffset(240);  // Reduced spacing
          governorGeneral.addTeamOfficial(governors);
          governors.setOffset(180);  // Under governor-general, slightly left

          allOfficials.addTeamOfficial(chairMan);
          chairMan.setOffset(-240);  // Far left but reduced
          chairMan.addTeamOfficial(ministers);
          ministers.setOffset(-120);  // Under chairman, closer to center
          chairMan.addTeamOfficial(deputyChairman);
          deputyChairman.setOffset(120);  // Slightly right of chairman

          allOfficials.addTeamOfficial(ambassadorGeneral);
          ambassadorGeneral.setOffset(0);  // Centered
          ambassadorGeneral.addTeamOfficial(ambassadors);
          ambassadors.setOffset(-150);  // Left under ambassador-general
          ambassadorGeneral.addTeamOfficial(senators);
          senators.setOffset(150);  // Right under ambassador-general
      }




      private void redraw() {
          UI.clearGraphics();
          drawTree(allOfficials);
          drawNewIcon();
          drawRetireIcon();
      }

      public static final double NEW_LEFT = 10;  // left of the new position Icon
      public static final double NEW_TOP = 10;   // top of the new position Icon

      public static final double ICON_X = 40;    // location and size of the remove icon
      public static final double ICON_Y = 100;
      public static final double ICON_RAD = 20;

      private void drawNewIcon(){
          UI.setColor(Official.BACKGROUND_COL);
          UI.fillRect(NEW_LEFT,NEW_TOP,Official.WIDTH, Official.HEIGHT);
          UI.setColor(Color.black);
          UI.drawRect(NEW_LEFT,NEW_TOP,Official.WIDTH, Official.HEIGHT);
          UI.drawString("NEW", NEW_LEFT+8, NEW_TOP+Official.HEIGHT/2-5);
          UI.drawString("POSN", NEW_LEFT+5, NEW_TOP+Official.HEIGHT/2+10);
      }


      private void drawRetireIcon(){
          UI.setColor(Color.red);
          UI.setLineWidth(5);
          UI.drawOval(ICON_X-ICON_RAD, ICON_Y-ICON_RAD, ICON_RAD*2, ICON_RAD*2);
          double off = ICON_RAD*0.68;
          UI.drawLine((ICON_X - off), (ICON_Y - off), (ICON_X + off), (ICON_Y + off));
          UI.setLineWidth(1);
          UI.setColor(Color.black);
      }


public static void main (String[] args){
    STWOfficialsGeneralTree stw = new STWOfficialsGeneralTree();
    stw.loadOfficials();
    stw.setupGUI();

}
}