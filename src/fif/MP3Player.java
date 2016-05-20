//right click project name, select properties, then library, then add .jar  jl1.0.1.jar

package fif;
import javazoom.jl.player.*;
import java.io.*;

public class MP3Player implements Runnable {

 private static Player player;
 private InputStream is;
 public static String filename;
 public static boolean musicRunning=false;
 public static boolean loop=false;

public void run(){
    
try
{
    do{
        is = new FileInputStream(filename  );
        player = new Player( is );
        musicRunning=true;
        player.play();
        musicRunning=false;
        //if(loop)System.out.println("Looping Music");
    }while(loop);
    stop();
    
}
  catch( Exception e ){ System.out.println("Error " + e);}
 
}

public static void stop(){
    player.close();
    musicRunning=false;
    Thread.currentThread().interrupt();
}
}


