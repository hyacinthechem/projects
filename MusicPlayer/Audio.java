
import ecs100.UI;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Audio {

    private String fileName;
    private File filePath;


    public Audio(File filePath){

        this.filePath = filePath;
        playSong(filePath);
        JOptionPane.showMessageDialog(null,"Press OK to stop Playing");
    }

    public void playSong(File file) {

        try {
          //File file = new File(filename);

          if(file.exists() && file.isFile()){

              AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
              Clip clip = AudioSystem.getClip();
              clip.open(audioInputStream);
              clip.start();

          }else{
              UI.println("Song doesn't appear to be in your Library");
          }


        } catch (Exception e) {
            UI.println("File Failure" + e);
        }

    }

}

