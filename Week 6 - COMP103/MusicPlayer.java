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
    private Map<String, Song> songs = new HashMap<>();
    private Map<Song, double[]> songPositions = new HashMap<>();
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
        UI.addButton("Exit Playlist" , UI::clearPanes);
        UI.addButton("Clear" , this::clearLibrary);
        UI.addButton("Show my playing queue", this::songQueue);
    
    }


    public void search(){

        if(type) {
            searchSong(this.songName,this.defaultX,this.defaultY);
        }else {
            viewGenre(this.genreName, this.defaultX, this.defaultY);
        }

    }


    public void doMouse(String action, double x, double y) {
        if (action.equals("released")) {
            for (Map.Entry<Song, double[]> entry : songPositions.entrySet()) {
                Song song = entry.getKey();
                double[] position = entry.getValue();
                double songX = position[0];
                double songY = position[1];

                // Check if the click is within the song's display area
                int width = 200; // Adjust as needed
                int height = 30; // Adjust as needed

                if (x >= songX && x <= songX + width && y >= songY - height && y <= songY) {
                    tempSongs.remove(song.getSongName()); // Assuming Song has a getName() method
                    createPlaylist(); // Refresh playlist after removal
                    break;
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

        songPositions.clear(); // Clear previous positions
        UI.clearText(); // Clear text once before drawing the playlist

        for (Song song : tempSongs) {
            y -= 30;
            double[] position = { x, y };
            songPositions.put(song, position);
            UI.drawString(song.toString(), x, y);
        }
    }


    public void createPlaylist1() {
        double x = 200;
        double y = 500;

        // Assuming songs is a Map<String, Song>
        int songCount = songs.size();
         values = new double[songCount][2]; // 2 columns: one for x, one for y

        int index = 0;
        for (Map.Entry<String, Song> song : songs.entrySet()) {
            UI.clearText();
            loadSongData();

            y -= 30;

            // Store the x and y values in the 2D array
            values[index][0] = x; // x coordinate
            values[index][1] = y; // y coordinate
            index++;


            UI.drawString(song.getValue().toString(), x, y);


        }

        // You can now use the 'values' array as needed
    }


    public void showPlaylists(){
    
    
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
