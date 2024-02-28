package Login_Form;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Music {
    Clip clip;
    Clip SoundEffect;

     public void PlayMusic (String musicLocation){
        InputStream music;
        try{
            File musicPath = new File(musicLocation);
            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(clip.LOOP_CONTINUOUSLY);

            }
            else {
                System.out.println("Can not find the file Specified");
            }
        }
        catch(Exception eX) {
            eX.printStackTrace();
        }

    }

    public void PlaySoundEffect (String musicLocation){
        InputStream music;
        try{
            File musicPath = new File(musicLocation);
            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                SoundEffect = AudioSystem.getClip();
                SoundEffect.open(audioInput);
                SoundEffect.start();
                //clip.loop(clip.LOOP_CONTINUOUSLY);
            }
            else {
                System.out.println("Can not find the file Specified");
            }
        }
        catch(Exception eX) {
            eX.printStackTrace();

        }
    }

  public void StopMusic (String musicLocation){


      clip.stop();
      clip.close();

  }
}
