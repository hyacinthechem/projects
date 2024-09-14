import ecs100.UI;

import ecs100.*;

public class STWOfficialsGeneralTree {

    Official allOfficials;

    public void setupGUI(){
        UI.addButton("Draw Officials", this:: drawOfficials);

    }

    public void drawOfficials(){




    }

    public void loadOfficials(){

        allOfficials = new Official("President");
        Official cabinet = new Official("Cabinet");
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
        Official troops = new Official("Troops");
        Official engineers = new Official("Engineers");


        allOfficials.addTeamOfficial(headsOfDepartments);
        allOfficials.addTeamOfficial(cabinet);
        allOfficials.addTeamOfficial(governorGeneral);
        allOfficials.addTeamOfficial(chairMan);
        allOfficials.addTeamOfficial(ambassadorGeneral);

        headsOfDepartments.addTeamOfficial(directors);





    }


    public static void main (String[] args){
        STWOfficialsGeneralTree stw = new STWOfficialsGeneralTree();
        stw.loadOfficials();
        stw.setupGUI();

    }
}
