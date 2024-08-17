import java.util.Map;
import java.util.TreeMap;
import ecs100.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

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
    
    private List<Song> tempSongs;
    private Map<String, Song> songs = new HashMap<>();
    private Queue<Song> queueSongs = new PriorityQueue<>();
    private Queue<Song> naturalQueue = new ArrayDeque<>();
    
    private String songName;
    
public void setupGUI(){
    
        UI.addTextField("Search for Song" , (String songName) -> {this.songName = songName;});
        UI.addButton("Select Genre" , this::selectGenre);
        UI.addButton("Clear" , this::clearLibrary);
        UI.addButton("Music Library", this::viewAllSongs);
        UI.addButton("Show me playlists", this::showPlaylists);
        UI.addButton("Search" , () -> {searchSong(this.songName);});
        UI.addButton("Play Song" , () -> {playSong(this.songName);}); 
        UI.addButton("Show my playing queue", this::songQueue);
    
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
    
    }
    
public void selectGenre(){


    
    }
    
public void showPlaylists(){
    
    
    }
    
public void searchSong(String songName) {
        UI.clearText();
    for (Song song : songs.values()) {

        if (song.getSongName().equals(songName)) {

            UI.println(song.toString());

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
