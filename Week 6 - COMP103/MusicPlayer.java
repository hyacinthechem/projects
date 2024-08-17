import java.util.Map;

import ecs100.*;
import java.util.*;
import java.io.IOException;
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
    
    private List<Song> tempSongs;
    private Map<String, Song> songs = new HashMap<>();
    private Queue<Song> queueSongs = new PriorityQueue<>();
    private Queue<Song> naturalQueue = new ArrayDeque<>();
    
    private String songName;
    private String genreName;
    private boolean type;

    public void setupGUI(){
    
        UI.addTextField("Search for Song" , (String songName) -> {this.songName = songName; type = true;});
        UI.addTextField("Select Genre" , (String genreName) -> {this.genreName = genreName; type = false;});
        UI.addButton("Search" , this::search);
        UI.addButton("Play Song" , () -> {playSong(this.songName);});
        UI.addButton("Music Library", this::viewAllSongs);
        UI.addButton("Exit Playlist" , UI::clearPanes);
        UI.addButton("Clear" , this::clearLibrary);
        UI.addButton("Show my playing queue", this::songQueue);
    
    }


    public void  search(){

        if(type) {
            searchSong(this.songName,this.defaultX,this.defaultY);
        }else {
            viewGenre(this.genreName, this.defaultX, this.defaultY);
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
