public class TitleRank {

    private TitleRank leftSubRank;
    private TitleRank  rightSubRank;
    private String name;
    private String positions;


   public TitleRank(String name, String positions) {
       this.name = name;
       this.positions = positions;
   }


    public TitleRank(TitleRank leftSubRank, TitleRank rightSubRank, String name,String positions) {

        this.leftSubRank = leftSubRank;
        this.rightSubRank = rightSubRank;
        this.name = name;
        this.positions = positions;


    }

    private TitleRank getLeftSubRank() {
       return leftSubRank;
    }

    private TitleRank getRightSubRank() {
       return rightSubRank;
    }

    public String getName() {
       return name;
    }

    public String getPositions() {
       return positions;
    }

    public void setRanks(TitleRank leftSubRank, TitleRank rightSubRank) {
       if(!(leftSubRank == null && rightSubRank==null)) {
           this.leftSubRank = leftSubRank;
           this.rightSubRank = rightSubRank;
       }



    }

    public String toString(){

       return("name: " + name + " positions: " + positions);

    }



}
