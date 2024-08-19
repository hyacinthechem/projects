import java.util.Map;

import ecs100.*;
import java.util.*;
import java.io.IOException;
import java.io.File;
import java.nio.file.*;
import java.util.List;
import javax.swing.Timer;

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

    private boolean createdPlaylist;

    private double defaultX= 200;
    private double defaultY  = 500;

    //Data Structures
    private List<Song> tempSongs;
    private List<String> yourPlaylistsOrdered  = new ArrayList<>();

    private Set<String> yourPlaylists = new HashSet<>();
    private Set<String> playingSongs = new HashSet<>();


    private Map<String, Song> songs = new HashMap<>();
    private Map<String, double[]> drawnStrings = new HashMap<>();


    private Queue<Song> queueSongs;


    private String songName;
    private String genreName;
    private boolean type;
    private boolean play;
   private boolean loop;
    private boolean addedSong;

    public void setupGUI(){
    
        UI.addTextField("Search for Song" , (String songName) -> {this.songName = songName; type = true;});
        UI.addTextField("Select Genre" , (String genreName) -> {this.genreName = genreName; type = false;});
        UI.addButton("Search" , this::search);
        UI.addButton("Play Song" , () -> {playSong(this.songName);});
        UI.addButton("Music Library", this::viewAllSongs);
        UI.addButton("Create Playlist", this::createPlaylist);
        UI.addButton("My Playlist", this::showPlaylists);
        UI.addButton("Shuffle Playlist" , this:: shuffleSongs);
        UI.addButton("Exit Playlist" , UI::clearPanes);
        UI.addButton("Clear" , this::clearLibrary);
        UI.addButton("Show my playing queue", this::songQueue);
        UI.addButton("Quit"  , () -> { loop = false; UI.quit(); });
        UI.setMouseListener(this::doMouse);
        //UI.setMouseListener(this::drawString);

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
                String title = entry.getKey();
                if(Math.abs(pos[0] - x) < 50 && Math.abs(pos[1] - y) < 20 ){    //is the click within bounds
                     drawnStrings.remove(entry.getKey());             // o(n) removes the String from the map
                     yourPlaylists.add(entry.getKey());       // adds it to yourPlaylist ordered array
                     yourPlaylistsOrdered.add(entry.getKey());              // adds it to yourPlaylist unordered set
                     UI.clearGraphics();  //clear all the drawn strings

                    if(play){
                        play = false;
                        if(title.contains(songName));

                        playSong(this.songName);

                    }

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
this.queueSongs.clear();
}




public void viewAllSongs() {
    loadSongData();
    for (Map.Entry<String, Song> song : songs.entrySet()) {

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

        UI.clearText();     // Clear text once before drawing the playlist
        if(createdPlaylist){
            UI.clearGraphics();
        }

        for (Song song : tempSongs) {
            y -= 30;
            //double[] position = { x, y };
            this.drawString(song.toString(), x, y);
        }
    }

    public void shuffleSongs(){
        UI.clearGraphics();
        Collections.shuffle(yourPlaylistsOrdered);

        double x = 200;
        double y = 500;

        for(int i = 0; i < yourPlaylistsOrdered.size(); i++){
            y-=-30;
            this.drawString(yourPlaylistsOrdered.get(i), x, y);

        }

    }



    public void showPlaylists(){
          UI.clearGraphics();
        if(!yourPlaylistsOrdered.isEmpty() && !yourPlaylists.isEmpty()){ //check if playlists have been created
            boolean ordered = UI.askBoolean("Would you like playlist ordered?");

           if(ordered){
               for(String song : yourPlaylistsOrdered){    //loop through array and draw the string
                       defaultY -= 30;
                    this.drawString(song, defaultX , defaultY);
               }

           }else {
               for (String song : yourPlaylists) {     //Loop through set and draw the string
                      defaultY -=30;
                      this.drawString(song, defaultX , defaultY);
               }
           }

           createdPlaylist = true;

        }else{
            UI.println("You have no Playlists");
        }
    
    }



    
public void searchSong(String songName,double x,double y) {
        UI.clearGraphics();
        UI.clearText();
        loadSongData();

    for (Song song : songs.values()) {

        if (song.getSongName().equals(songName)) {

            UI.println(song.toString());
            this.drawString(song.getSongName(), 250, 250);
            play = true;

        }


    }

}

public void songQueue() {
     UI.clearGraphics();

     if(addedSong){
         queueSongs = new PriorityQueue<>();
     }else{
         queueSongs = new ArrayDeque<>();
     }

     for(Song song : songs.values()){


         for(String s : yourPlaylists){
             if(s.contains(song.getSongName())){

                 queueSongs.offer(song);

             }


         }

     }

     /*
     for(Song song : queueSongs){
         defaultY-= 30;
         this.drawString(song.getSongName(), defaultX, defaultY);

     }

      */

     if(playingSongs.isEmpty()){


         this.drawString(queueSongs.element().getSongName(), 180, 135);

         playSong(queueSongs.element().getSongName());



         //.element();
         queueSongs.poll();

         for (Song song : queueSongs) {

             defaultY-= 30;
             this.drawString(song.getSongName(), defaultX, defaultY);

         }

     }


}

public void timerSetup(){

    Timer timer = new Timer(16, e -> redraw());

    timer.start();


}

public void redraw(){
   UI.drawString("Playing", 180,114);
   UI.drawString("Queue" , 180, 200);
   UI.drawRect(175,100,400,50);

   if(loop){
       redraw();
   }

    }
    
public static void main(String[]args){
   MusicPlayer m1 = new MusicPlayer();
   m1.timerSetup();
   m1.loadSongData();
   m1.setupGUI();

}
}
