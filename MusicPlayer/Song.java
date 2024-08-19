
/**
 * Write a description of class Song here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Song
{
    
    private String songName;
    private String duration;
    private String album;
    private String genre;
    private double listens;
    private Audio audio;
    private int year;
    
    public Song(String songName, String duration,String album, String genre, double listens,int year){
         this.songName = songName;
         this.duration = duration;
         this.album = album;
         this.genre = genre;
         this.listens = listens;
         this.year = year;
        
    }
    
    public String getSongName(){
    
    return this.songName;
    }
    
    public String getDuration(){
    
        return this.duration;
    }
    
    public String getAlbum(){
     
        return this.album;
    
    }
    
    public String getGenre(){
    
        return this.genre;
    }
    
    public double getListens(){
    
     return this.listens;
    }
    
    public int getYear(){
    
        return this.year;
    }
    
    @Override
    
    public String toString(){
    
    return(duration + " " + songName + " " + album + " " + genre + " " + listens + "  Streams" + " " + year + " " );
    
    }
}
