import java.util.Map;

import ecs100.*;
import java.util.*;
import java.io.IOException;
import java.io.File;
import java.nio.file.*;
import java.util.List;

/**
 * Write a description of class MusicPlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MusicPlayer
{
    private static final double borderX = 100;
    private static final double borderY = 200;

    private double defaultX= 150;
    private double defaultY  = 300;

    //Data Structures
    private List<Song> tempSongs;
    private List<String> yourPlaylistsOrdered  = new ArrayList<>();
    private Set<String> yourPlaylists = new HashSet<>();
    private Map<String, Song> songs = new HashMap<>();
    private Map<String, double[]> drawnStrings = new HashMap<>();
    private Queue<Song> queueSongs = new PriorityQueue<>();
    private Queue<Song> naturalQueue = new ArrayDeque<>();
    private double[][] values;
    
    private String songName;
    private String genreName;
    private boolean type;

    public void setupGUI(){
    
        UI.addTextField("Search for Song" , (String songName) -> {this.songName = songName; type = true;});
        UI.addTextField("Select Genre" , (String genreName) -> {this.genreName = genreName; type = false;});
        UI.addButton("Search" , this::search);
        UI.addButton("Play Song" , () -> {playSong(this.songName);});
        UI.addButton("Music Library", this::viewAllSongs);
        UI.addButton("Create Playlist", this::createPlaylist);
        //UI.addButton("My Playlist", this::showPlaylists);
        UI.addButton("Exit Playlist" , UI::clearPanes);
        UI.addButton("Clear" , this::clearLibrary);
        UI.addButton("Show my playing queue", this::songQueue);
        UI.setMouseListener(this::doMouse);

    }


    public void search(){

        if(type) {
            searchSong(this.songName,this.defaultX,this.defaultY);
        }else {
            viewGenre(this.genreName, this.defaultX, this.defaultY);
        }

    }

    public void drawString(String text, double x, double y){
        UI.drawString(text, x, y);
        drawnStrings.put(text, new double[]{x, y});

    }


    public void doMouse(String action, double x, double y){

        if(action.equals("pressed")){
            for(Map.Entry<String, double[] > entry : drawnStrings.entrySet()){
                double[] pos = entry.getValue();
                if(Math.abs(pos[0] - x) < 50 && Math.abs(pos[1] - y) < 20 ){    //is the click within bounds
                     drawnStrings.remove(entry.getKey());             // o(n) removes the String from the map
                     yourPlaylistsOrdered.add(entry.getKey());       // adds it to yourPlaylist ordered array
                     yourPlaylistsOrdered.add(entry.getKey());              // adds it to yourPlaylist unordered set
                     UI.clearGraphics();                             //clear all the drawn strings

                     for(Map.Entry<String, double[] > redrawEntry : drawnStrings.entrySet()){

                         this.drawString(redrawEntry.getKey(), redrawEntry.getValue()[0], redrawEntry.getValue()[1]);  //redraw all the songs except the songs chosen for playlist


                     }

                       break;  //break since it is event mouse handler

                }


            }


        }


    }

    
public void loadSongData(){
    try{
    
    tempSongs = new ArrayList<>();
    String filename = "data/100_songs_list.txt";
    
    Scanner lineScanner = new Scanner(Path.of(filename));
    
    while(lineScanner.hasNext()){
     
     
     String artistName = lineScanner.next();
     String songName = lineScanner.next();
     String duration = lineScanner.next();
     String albumName = lineScanner.next();
     String genre = lineScanner.next();
     double listens = lineScanner.nextDouble();
     int year = lineScanner.nextInt();
    
     Song s = new Song(songName,duration,albumName,genre,listens,year);
     
     songs.put(artistName, s);
     tempSongs.add(s);
     
    
    }
        
        
    
}catch(IOException e){UI.println("File Failure" + e);}
}




public void clearLibrary(){

UI.clearText();
this.songs.clear();
this.tempSongs.clear();


}




public void viewAllSongs(){
loadSongData();
 for(Map.Entry<String, Song> song : songs.entrySet()){
    
       UI.println(song.toString());
    
    }

}
    
public void playSong(String songName){
    String filePath = "data/Song Audio";


       File file = new File(filePath);

       if(file.exists()){

           File[] specific = file.listFiles();

           for(File f : specific){

           if(f.getName().contains(songName)){

               UI.println("Playing song " + songName);

               Audio a1 = new Audio(f);

               a1.playSong(f);

           }else{

               UI.println("Song couldn't be played as it is not in library");

           }

           }


       }

        //Audio audio = new Audio(fileName);

    }
    
public void viewGenre(String genreName, double x, double y){
        UI.clearText();
        loadSongData();
        for(Map.Entry<String, Song> song : songs.entrySet()){
              x-=30;
              y-=30;
            if(song.getValue().getGenre().equals(genreName)){

                UI.println(song.toString());
                UI.drawString(song.toString(),x, y);

            }
        }

    
    }

    public void createPlaylist() {
        double x = 200;
        double y = 500;

        UI.clearText(); // Clear text once before drawing the playlist

        for (Song song : tempSongs) {
            y -= 30;
            //double[] position = { x, y };
            this.drawString(song.toString(), x, y);
        }
    }





    public void showPlaylists(boolean ordered){

        boolean ordered = UI.askBoolean("Would you like playlist ordered?");

        if(!yourPlaylistsOrdered.isEmpty() && !yourPlaylists.isEmpty()){   //check if playlists have been created

           if(ordered){
               for(String song : yourPlaylistsOrdered){

                    

               }

           }else {


               for (Song song : yourPlaylists) {


               }

           }

        }else{

            UI.println("You have no Playlists");
        }
    
    }



    
public void searchSong(String songName,double x,double y) {
        UI.clearText();
        loadSongData();

        x-=50;
        y-=50;
    for (Song song : songs.values()) {

        if (song.getSongName().equals(songName)) {

            UI.println(song.toString());
            UI.drawString(song.getSongName(), x, y);

        }


    }

}

public void songQueue(){
    
    }
    
public static void main(String[]args){
   MusicPlayer m1 = new MusicPlayer();
   m1.loadSongData();
   m1.setupGUI();

}
}
