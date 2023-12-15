package by.RIP.sound;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

@Service
public class RecordPlayer {
    private Player player;
    private FileInputStream fileInputStream;
    private BufferedInputStream bis;
    private long pauseLocation;
    private long songTotalLength;
    private volatile boolean playbackAllowed;
    private String file[]= new String[30];
    public RecordPlayer(){
//        file[0]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\oldMan\\voice.mp3";
//        file[1]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\oldMan\\voice1.mp3";
//        file[2]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\oldMan\\voice (2).mp3";
//        file[3]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\oldMan\\voice (3).mp3";
        file[0]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\track1.mp3";
        file[1]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\track2.mp3";
        file[2]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\step.mp3";
        file[3]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\close_door.mp3";
        file[4]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\start_fishing.mp3";
        file[5]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\catch.mp3";
        file[6]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\key.mp3";
        file[7]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\chest.mp3";
        file[8]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\open_door.mp3";
        file[9]="C:\\Project Java\\RIP\\src\\main\\resources\\sound\\speed.mp3";

    }
    public void setFile(int i){
        try {
            fileInputStream = new FileInputStream(file[i]);
            bis = new BufferedInputStream(fileInputStream);
            songTotalLength = bis.available();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void next() {
        if (player != null) {
            player.close();
            pauseLocation = 0;
            songTotalLength = 0;
        }
    }

    public void stop() {
        playbackAllowed = false;
        if (player != null) {
            player.close();
            pauseLocation = 0;
            songTotalLength = 0;
        }
    }

    public void pause() {
        playbackAllowed = false;
        if (player != null && fileInputStream != null) {
            try {
                pauseLocation = fileInputStream.available();
                player.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//"/sound.track1.mp3"
    public void playList(int i) {
        String f=file[i];
        playbackAllowed = true;
        new Thread(() -> {
                try {
                    fileInputStream = new FileInputStream(f);
                    bis = new BufferedInputStream(fileInputStream);
                    songTotalLength = bis.available();
                    if (pauseLocation > 0) {
                        fileInputStream.skip(songTotalLength - pauseLocation);
                    }
                    player = new Player(bis);
                    player.play();
                } catch (JavaLayerException | IOException e) {
                    e.printStackTrace();
            }
        }).start();
    }
}
